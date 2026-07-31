package com.torneos.servicio;

import com.torneos.dto.EquipoDTO;
import com.torneos.dto.TorneoDTO;
import com.torneos.mapper.EquipoMapper;
import com.torneos.mapper.TorneoMapper;
import com.torneos.modelo.Equipo;
import com.torneos.modelo.Torneo;
import com.torneos.modelo.Usuario;
import com.torneos.repositorio.EquipoRepository;
import com.torneos.repositorio.TorneoRepository;
import com.torneos.repositorio.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class TorneoServiceImpl implements TorneoService {

    @Autowired
    private TorneoRepository torneoRepository;

    @Autowired
    private TorneoMapper torneoMapper;

    @Autowired
    private UsuarioRepository usuarioRepository; // Para el 'creadoPor'

    // Dependencias para la lógica de inscripción
    @Autowired
    private EquipoRepository equipoRepository;

    @Autowired
    private EquipoMapper equipoMapper;

    // ======================================================
    // MÉTODOS CRUD BÁSICOS DE TORNEO
    // ======================================================

    /**
     * Obtiene una lista de todos los torneos.
     */
    @Override
    @Transactional(readOnly = true)
    public List<TorneoDTO> listarTorneos() {
        return torneoRepository.findAll()
                .stream()
                .map(torneoMapper::toDTO) // Usa el mapper para convertir a DTO
                .collect(Collectors.toList());
    }

    /**
     * Busca un torneo por su ID.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<TorneoDTO> buscarPorId(Long id) {
        return torneoRepository.findById(id).map(torneoMapper::toDTO);
    }

    /**
     * Guarda o actualiza un torneo.
     * Incluye la lógica para asignar 'creadoPor' y los nuevos campos.
     */
    @Override
    @Transactional
    public TorneoDTO guardarTorneo(TorneoDTO dto) {
        Torneo torneo;
        if (dto.getId() != null) {
            // --- ACTUALIZACIÓN ---
            torneo = torneoRepository.findById(dto.getId())
                    .orElseThrow(() -> new RuntimeException("Torneo no encontrado"));

            // Mapea los campos del DTO a la entidad existente
            torneo.setNombre(dto.getNombre());
            torneo.setFechaInicio(dto.getFechaInicio());
            torneo.setFechaFin(dto.getFechaFin());
            torneo.setCiudad(dto.getCiudad()); // Actualiza el campo ciudad
        } else {
            // --- CREACIÓN ---
            // Convierte el DTO a Entidad (esto ya incluye la ciudad)
            torneo = torneoMapper.toEntity(dto);

            // Obtiene el usuario autenticado dinámicamente
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            if (auth == null || !auth.isAuthenticated()) {
                throw new RuntimeException("No hay un usuario autenticado para realizar esta operación");
            }
            String correo = auth.getName();
            Usuario creador = usuarioRepository.findByCorreoUsuario(correo)
                    .orElseThrow(() -> new RuntimeException("Usuario creador no encontrado con el correo: " + correo));

            torneo.setCreadoPor(creador);
            torneo.setCreadoEn(new Timestamp(System.currentTimeMillis()));
        }

        // Guarda la entidad en la BD
        Torneo guardado = torneoRepository.save(torneo);

        // Devuelve el DTO de la entidad guardada
        return torneoMapper.toDTO(guardado);
    }

    /**
     * Elimina un torneo por su ID.
     */
    @Override
    @Transactional
    public void eliminarTorneo(Long id) {
        torneoRepository.deleteById(id);
    }

    // ======================================================
    // MÉTODOS PARA GESTIÓN DE EQUIPOS EN TORNEO (@ManyToMany)
    // ======================================================

    /**
     * Obtiene solo los equipos que están inscritos en un torneo.
     */
    @Override
    @Transactional(readOnly = true)
    public Set<EquipoDTO> obtenerEquiposInscritos(Long idTorneo) {
        Torneo torneo = torneoRepository.findById(idTorneo)
                .orElseThrow(() -> new RuntimeException("Torneo no encontrado con ID: " + idTorneo));

        // Usa el getter de la entidad para la lista de equipos
        return torneo.getEquiposInscritos().stream()
                .map(equipoMapper::toDTO)
                .collect(Collectors.toSet());
    }

    /**
     * Inscribe un equipo existente en un torneo.
     */
    @Override
    @Transactional
    public void inscribirEquipoEnTorneo(Long idTorneo, Long idEquipo) {
        Torneo torneo = torneoRepository.findById(idTorneo)
                .orElseThrow(() -> new RuntimeException("Torneo no encontrado con ID: " + idTorneo));
        Equipo equipo = equipoRepository.findById(idEquipo)
                .orElseThrow(() -> new RuntimeException("Equipo no encontrado con ID: " + idEquipo));

        // Añade el equipo a la lista de inscritos del torneo
        torneo.getEquiposInscritos().add(equipo);

        // Guarda el torneo (dueño de la relación)
        torneoRepository.save(torneo);
    }

    /**
     * Desinscribe un equipo de un torneo.
     */
    @Override
    @Transactional
    public void desinscribirEquipoDeTorneo(Long idTorneo, Long idEquipo) {
        Torneo torneo = torneoRepository.findById(idTorneo)
                .orElseThrow(() -> new RuntimeException("Torneo no encontrado con ID: " + idTorneo));
        Equipo equipo = equipoRepository.findById(idEquipo)
                .orElseThrow(() -> new RuntimeException("Equipo no encontrado con ID: " + idEquipo));

        // Remueve el equipo de la lista de inscritos
        torneo.getEquiposInscritos().remove(equipo);

        torneoRepository.save(torneo);
    }

    @Override
    @Transactional
    public void asignarOrganizador(Long idTorneo, Long idUsuario) {
        Torneo torneo = torneoRepository.findById(idTorneo)
                .orElseThrow(() -> new RuntimeException("Torneo no encontrado con ID: " + idTorneo));
        Usuario usuario = usuarioRepository.findById(idUsuario)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con ID: " + idUsuario));
        torneo.setCreadoPor(usuario);
        torneoRepository.save(torneo);
    }
}