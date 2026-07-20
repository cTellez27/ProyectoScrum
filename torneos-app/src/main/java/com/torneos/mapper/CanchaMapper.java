package com.torneos.mapper;

import com.torneos.dto.CanchaDTO;
import com.torneos.modelo.Cancha;
import org.springframework.stereotype.Component;

@Component
public class CanchaMapper {

    /**
     * Convierte de Entidad (Cancha) -> DTO (CanchaDTO)
     */
    public CanchaDTO toDTO(Cancha cancha) {
        if (cancha == null) {
            return null;
        }
        
        CanchaDTO dto = new CanchaDTO();
        dto.setId(cancha.getId());
        dto.setNombre(cancha.getNombre());
        dto.setUbicacion(cancha.getUbicacion());
        dto.setCapacidad(cancha.getCapacidad());
        
        return dto;
    }

    /**
     * Convierte de DTO (CanchaDTO) -> Entidad (Cancha)
     */
    public Cancha toEntity(CanchaDTO dto) {
        if (dto == null) {
            return null;
        }

        Cancha cancha = new Cancha();
        cancha.setId(dto.getId());
        cancha.setNombre(dto.getNombre());
        cancha.setUbicacion(dto.getUbicacion());
        cancha.setCapacidad(dto.getCapacidad());
        
        return cancha;
    }
}