package com.torneos.servicio;

import com.torneos.dto.TarjetaDTO;
import com.torneos.mapper.TarjetaMapper;
import com.torneos.modelo.Jugador;
import com.torneos.modelo.Partido;
import com.torneos.modelo.Tarjeta;
import com.torneos.repositorio.JugadorRepository;
import com.torneos.repositorio.PartidoRepository;
import com.torneos.repositorio.TarjetaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional; // ¡Importa Optional!
import java.util.stream.Collectors;

@Service
public class TarjetaServiceImpl implements TarjetaService {

    @Autowired
    private TarjetaRepository tarjetaRepository;
    @Autowired
    private PartidoRepository partidoRepository;
    @Autowired
    private JugadorRepository jugadorRepository;
    @Autowired
    private TarjetaMapper tarjetaMapper;

    @Override
    @Transactional
    public TarjetaDTO registrarTarjeta(TarjetaDTO dto) {
        Partido partido = partidoRepository.findById(dto.getIdPartido())
                .orElseThrow(() -> new RuntimeException("Partido no encontrado"));
        Jugador jugador = jugadorRepository.findById(dto.getIdJugador())
                .orElseThrow(() -> new RuntimeException("Jugador no encontrado"));

        Tarjeta tarjeta = tarjetaMapper.toEntity(dto, partido, jugador);
        Tarjeta guardada = tarjetaRepository.save(tarjeta);
        
        return tarjetaMapper.toDTO(guardada);
    }

    @Override
    @Transactional(readOnly = true)
    public List<TarjetaDTO> listarTarjetasPorPartido(Long idPartido) {
        return tarjetaRepository.findByPartido_Id(idPartido).stream()
                .map(tarjetaMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void eliminarTarjeta(Long idTarjeta) {
        tarjetaRepository.deleteById(idTarjeta);
    }
    
    // --- ¡IMPLEMENTACIÓN DEL MÉTODO QUE FALTABA! ---
    @Override
    @Transactional(readOnly = true)
    public Optional<TarjetaDTO> buscarPorId(Long idTarjeta) {
        // Busca en el repositorio y mapea el resultado a un DTO
        return tarjetaRepository.findById(idTarjeta)
                .map(tarjetaMapper::toDTO);
    }
}