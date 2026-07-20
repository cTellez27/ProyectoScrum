package com.torneos.servicio;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.torneos.dto.CanchaDTO;
import com.torneos.mapper.CanchaMapper;
import com.torneos.modelo.Cancha;
import com.torneos.repositorio.CanchaRepository;
import com.torneos.repositorio.PartidoRepository;

import jakarta.transaction.Transactional;

@Service
public class CanchaServiceImpl implements CanchaService {

    @Autowired
    private CanchaRepository canchaRepository;

    @Autowired
    private CanchaMapper canchaMapper;
    
    @Autowired
    private PartidoRepository partidoRepository;

    @Override
    public List<CanchaDTO> listarCanchas() {
        return canchaRepository.findAll()
                .stream()
                .map(canchaMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<CanchaDTO> buscarPorId(Long id) {
        return canchaRepository.findById(id).map(canchaMapper::toDTO);
    }

    @Override
    public CanchaDTO guardarCancha(CanchaDTO dto) {
        Cancha cancha;
        if (dto.getId() != null) {
            // Actualización
            cancha = canchaRepository.findById(dto.getId())
                    .orElseThrow(() -> new RuntimeException("Cancha no encontrada"));
            
            // Mapeamos los campos del DTO a la entidad existente
            cancha.setNombre(dto.getNombre());
            cancha.setUbicacion(dto.getUbicacion());
            cancha.setCapacidad(dto.getCapacidad());
            
        } else {
            // Creación
            cancha = canchaMapper.toEntity(dto);
            // Asignamos el 'creado_en' que está en tu SQL
            cancha.setCreadoEn(new Timestamp(System.currentTimeMillis()));
        }
        
        Cancha guardada = canchaRepository.save(cancha);
        return canchaMapper.toDTO(guardada);
    }

    @Override
    @Transactional
    public void eliminarCancha(Long id) {
        // --- ¡LÓGICA NUEVA! ---
        // 1. Verificamos si la cancha está siendo usada en algún partido.
        if (partidoRepository.existsByCancha_Id(id)) {
            // 2. Si está en uso, lanzamos una excepción con un mensaje claro.
            // El controlador atrapará esto.
            throw new DataIntegrityViolationException("¡No se puede eliminar! La cancha está asignada a uno o más partidos.");
        }
        
        // 3. Si no está en uso, se elimina.
        canchaRepository.deleteById(id);
    }
}