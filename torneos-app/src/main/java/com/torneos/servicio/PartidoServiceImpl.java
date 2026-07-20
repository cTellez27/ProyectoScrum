package com.torneos.servicio;

import com.torneos.dto.PartidoDTO;
import com.torneos.mapper.PartidoMapper;
import com.torneos.modelo.Cancha;
import com.torneos.modelo.Equipo;
import com.torneos.modelo.Partido;
import com.torneos.modelo.Torneo;
import com.torneos.repositorio.CanchaRepository;
import com.torneos.repositorio.EquipoRepository;
import com.torneos.repositorio.PartidoRepository;
import com.torneos.repositorio.TorneoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PartidoServiceImpl implements PartidoService {

    @Autowired
    private PartidoRepository partidoRepository;
    @Autowired
    private PartidoMapper partidoMapper;
    @Autowired
    private TorneoRepository torneoRepository;
    @Autowired
    private EquipoRepository equipoRepository;
    @Autowired
    private CanchaRepository canchaRepository;

    @Override
    @Transactional(readOnly = true)
    public List<PartidoDTO> listarPartidosPorTorneo(Long idTorneo) {
        return partidoRepository.findByTorneo_Id(idTorneo)
                .stream()
                .map(partidoMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<PartidoDTO> buscarPorId(Long idPartido) {
        return partidoRepository.findById(idPartido)
                .map(partidoMapper::toDTO);
    }

    @Override
    @Transactional
    public PartidoDTO guardarPartido(Long idTorneo, PartidoDTO dto) {
        
        Partido partido = partidoMapper.toEntity(dto);
        
        Torneo torneo = torneoRepository.findById(idTorneo)
                .orElseThrow(() -> new RuntimeException("Torneo no encontrado"));
        Equipo equipoLocal = equipoRepository.findById(dto.getIdEquipoLocal())
                .orElseThrow(() -> new RuntimeException("Equipo local no encontrado"));
        Equipo equipoVisitante = equipoRepository.findById(dto.getIdEquipoVisitante())
                .orElseThrow(() -> new RuntimeException("Equipo visitante no encontrado"));
        Cancha cancha = canchaRepository.findById(dto.getIdCancha())
                .orElseThrow(() -> new RuntimeException("Cancha no encontrada"));

        partido.setTorneo(torneo);
        partido.setEquipoLocal(equipoLocal);
        partido.setEquipoVisitante(equipoVisitante);
        partido.setCancha(cancha);
        partido.setCreadoEn(new Timestamp(System.currentTimeMillis()));
        partido.setResultado("P.J."); // Pendiente por Jugar

        Partido guardado = partidoRepository.save(partido);
        return partidoMapper.toDTO(guardado);
    }

    @Override
    @Transactional
    public void eliminarPartido(Long idPartido) {
        partidoRepository.deleteById(idPartido);
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<PartidoDTO> listarPartidosPendientes() {
        // Busca los que dicen "P.J."
        return partidoRepository.findByResultado("P.J.")
                .stream()
                .map(partidoMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<PartidoDTO> listarPartidosJugados() {
        // Busca los que NO dicen "P.J."
        return partidoRepository.findByResultadoNot("P.J.")
                .stream()
                .map(partidoMapper::toDTO)
                .collect(Collectors.toList());
    }

    // --- ¡IMPLEMENTACIÓN DEL MÉTODO QUE FALTABA! ---
    @Override
    @Transactional
    public PartidoDTO registrarResultado(Long idPartido, String resultado) {
        Partido partido = partidoRepository.findById(idPartido)
                .orElseThrow(() -> new RuntimeException("Partido no encontrado con ID: " + idPartido));
        
        partido.setResultado(resultado);
        Partido guardado = partidoRepository.save(partido);
        
        return partidoMapper.toDTO(guardado);
    }
}