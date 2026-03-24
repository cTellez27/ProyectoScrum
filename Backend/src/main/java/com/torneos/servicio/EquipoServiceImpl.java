package com.torneos.servicio;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.torneos.dto.EquipoDTO;
import com.torneos.mapper.EquipoMapper;
import com.torneos.modelo.Equipo;
import com.torneos.modelo.Torneo;
import com.torneos.modelo.Usuario;
import com.torneos.repositorio.EquipoRepository;
import com.torneos.repositorio.TorneoRepository;
import com.torneos.repositorio.UsuarioRepository;

@Service
public class EquipoServiceImpl implements EquipoService {

    @Autowired
    private EquipoRepository equipoRepository;

    @Autowired
    private EquipoMapper equipoMapper;

    @Autowired
    private UsuarioRepository usuarioRepository; // Para el 'creadoPor'

    @Autowired
    private TorneoRepository torneoRepository; 

    // ======================================================
    // MÉTODOS CRUD BÁSICOS DE EQUIPO
    // ======================================================

    @Override
    @Transactional(readOnly = true)
    public List<EquipoDTO> listarEquipos() {
        return equipoRepository.findAll()
                .stream()
                .map(equipoMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public EquipoDTO guardarEquipo(EquipoDTO dto) {
        Equipo equipo;
        if (dto.getId() != null) {
            // Actualización
            equipo = equipoRepository.findById(dto.getId())
                    .orElseThrow(() -> new RuntimeException("Equipo no encontrado"));
            
            equipo.setNombre(dto.getNombre());
        } else {
            // Creación
            equipo = equipoMapper.toEntity(dto);
            
            // ¡ASIGNACIÓN DEL CREADOR!
            // Asegúrate de que el usuario con ID 1 exista en tu BD
            Usuario admin = usuarioRepository.findById(1L) 
                    .orElseThrow(() -> new RuntimeException("Usuario Admin (ID 1) no encontrado"));
            equipo.setCreadoPor(admin);
            equipo.setCreadoEn(new Timestamp(System.currentTimeMillis()));
        }
        
        Equipo guardado = equipoRepository.save(equipo);
        return equipoMapper.toDTO(guardado);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<EquipoDTO> buscarEquipoPorId(Long id) {
        return equipoRepository.findById(id).map(equipoMapper::toDTO);
    }

    @Override
    @Transactional
    public void eliminarEquipo(Long id) {
        // 1. Verificamos que existe
        if (!equipoRepository.existsById(id)) {
            throw new RuntimeException("Equipo no encontrado con ID: " + id);
        }
        
        // 2. Intentamos borrar.
        // Si el equipo está inscrito en un torneo, tiene jugadores
        // o está en un partido, la base de datos lanzará
        // una DataIntegrityViolationException.
        // ¡Esto es bueno! El Controlador la atrapará.
        equipoRepository.deleteById(id);
    }

    // ======================================================
    // MÉTODO PARA LÓGICA DE INSCRIPCIÓN
    // ======================================================

    @Override
    @Transactional(readOnly = true)
    public List<EquipoDTO> obtenerEquiposNoInscritos(Long idTorneo) {
        List<Equipo> todosLosEquipos = equipoRepository.findAll();
        Torneo torneo = torneoRepository.findById(idTorneo)
                .orElseThrow(() -> new RuntimeException("Torneo no encontrado"));
        Set<Equipo> equiposInscritos = torneo.getEquiposInscritos();

        return todosLosEquipos.stream()
                .filter(equipo -> !equiposInscritos.contains(equipo))
                .map(equipoMapper::toDTO)
                .collect(Collectors.toList());
    }
}