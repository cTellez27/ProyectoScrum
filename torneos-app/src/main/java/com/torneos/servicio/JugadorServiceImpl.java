package com.torneos.servicio;

import com.torneos.dto.JugadorDTO;
import com.torneos.mapper.JugadorMapper;
import com.torneos.modelo.Equipo;
import com.torneos.modelo.Jugador;
import com.torneos.repositorio.EquipoRepository;
import com.torneos.repositorio.JugadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class JugadorServiceImpl implements JugadorService {

    @Autowired
    private JugadorRepository jugadorRepository;

    @Autowired
    private EquipoRepository equipoRepository; // Para asignar el equipo

    @Autowired
    private JugadorMapper jugadorMapper;

    @Override
    @Transactional(readOnly = true)
    public List<JugadorDTO> listarJugadoresPorEquipo(Long idEquipo) {
        // Usamos el método que creamos en el repositorio
        return jugadorRepository.findByEquipo_Id(idEquipo)
                .stream()
                .map(jugadorMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<JugadorDTO> buscarPorId(Long idJugador) {
        return jugadorRepository.findById(idJugador)
                .map(jugadorMapper::toDTO);
    }

    @Override
    @Transactional
    public JugadorDTO guardarJugador(JugadorDTO dto, Long idEquipo) {
        // 1. Buscamos la entidad "Padre" (el Equipo)
        Equipo equipo = equipoRepository.findById(idEquipo)
                .orElseThrow(() -> new RuntimeException("Equipo no encontrado"));
        
        // 2. Convertimos el DTO a Entidad
        Jugador jugador = jugadorMapper.toEntity(dto);
        
        // 3. ¡Asignamos la relación!
        jugador.setEquipo(equipo);

        // 4. Guardamos el nuevo jugador
        Jugador guardado = jugadorRepository.save(jugador);
        
        return jugadorMapper.toDTO(guardado);
    }

    @Override
    @Transactional
    public JugadorDTO actualizarJugador(Long idJugador, JugadorDTO dto) {
        Jugador jugador = jugadorRepository.findById(idJugador)
                .orElseThrow(() -> new RuntimeException("Jugador no encontrado"));
        
        // Actualizamos los campos
        jugador.setNombre(dto.getNombre());
        jugador.setPosicion(dto.getPosicion());
        jugador.setFechaNacimiento(dto.getFechaNacimiento());
        
        Jugador guardado = jugadorRepository.save(jugador);
        return jugadorMapper.toDTO(guardado);
    }

    @Override
    @Transactional
    public void eliminarJugador(Long idJugador) {
        jugadorRepository.deleteById(idJugador);
    }
}