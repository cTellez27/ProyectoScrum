package com.torneos.mapper;

import com.torneos.dto.PartidoDTO;
import com.torneos.modelo.Partido;
import org.springframework.stereotype.Component;

@Component
public class PartidoMapper {

    /**
     * Convierte de Entidad (con objetos) a DTO (con nombres).
     */
    public PartidoDTO toDTO(Partido partido) {
        if (partido == null) {
            return null;
        }
        PartidoDTO dto = new PartidoDTO();
        dto.setId(partido.getId());
        dto.setFecha(partido.getFecha());
        dto.setResultado(partido.getResultado());

        // --- ¡LÍNEA NUEVA/CORREGIDA! ---
        if (partido.getTorneo() != null) {
            dto.setTorneoNombre(partido.getTorneo().getNombre());
            dto.setIdTorneo(partido.getTorneo().getId()); // <-- Esta línea es la que faltaba
        }
        
        if (partido.getEquipoLocal() != null) {
            dto.setEquipoLocalNombre(partido.getEquipoLocal().getNombre());
            dto.setIdEquipoLocal(partido.getEquipoLocal().getId());
        }
        if (partido.getEquipoVisitante() != null) {
            dto.setEquipoVisitanteNombre(partido.getEquipoVisitante().getNombre());
            dto.setIdEquipoVisitante(partido.getEquipoVisitante().getId());
        }
        if (partido.getCancha() != null) {
            dto.setCanchaNombre(partido.getCancha().getNombre());
            dto.setIdCancha(partido.getCancha().getId());
        }
        
        return dto;
    }

    /**
     * Convierte de DTO a Entidad.
     */
    public Partido toEntity(PartidoDTO dto) {
        if (dto == null) {
            return null;
        }
        Partido partido = new Partido();
        partido.setId(dto.getId()); 
        partido.setFecha(dto.getFecha());
        partido.setResultado(dto.getResultado());
        
        return partido;
    }
}