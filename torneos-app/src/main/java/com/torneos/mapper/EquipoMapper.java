package com.torneos.mapper;

import com.torneos.dto.EquipoDTO;
import com.torneos.modelo.Equipo;
import org.springframework.stereotype.Component;

@Component 
public class EquipoMapper {

    public EquipoDTO toDTO(Equipo equipo) {
        if (equipo == null) { return null; }

        EquipoDTO dto = new EquipoDTO();
        dto.setId(equipo.getId());
        dto.setNombre(equipo.getNombre());
        dto.setDirectorTecnico(equipo.getDirectorTecnico()); // Nuevo
        dto.setCiudadOrigen(equipo.getCiudadOrigen()); // Nuevo
        
        if (equipo.getCreadoPor() != null) {
            dto.setCreadoPorNombre(equipo.getCreadoPor().getNombreUsuario());
        }
        return dto;
    }

    public Equipo toEntity(EquipoDTO dto) {
        if (dto == null) { return null; }

        Equipo equipo = new Equipo();
        equipo.setId(dto.getId()); 
        equipo.setNombre(dto.getNombre());
        equipo.setDirectorTecnico(dto.getDirectorTecnico()); // Nuevo
        equipo.setCiudadOrigen(dto.getCiudadOrigen()); // Nuevo

        return equipo;
    }
}