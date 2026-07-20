package com.torneos.mapper;

import com.torneos.dto.TorneoDTO;
import com.torneos.modelo.Torneo;
import org.springframework.stereotype.Component;

@Component 
public class TorneoMapper {

    /**
     * Convierte de Entidad (Modelo) -> DTO (para mostrar en la lista)
     */
    public TorneoDTO toDTO(Torneo torneo) {
        if (torneo == null) {
            return null;
        }

        TorneoDTO dto = new TorneoDTO();
        dto.setId(torneo.getId());
        dto.setNombre(torneo.getNombre());
        
        // --- ¡LÍNEAS CLAVE QUE FALTABAN! ---
        dto.setFechaInicio(torneo.getFechaInicio());
        dto.setFechaFin(torneo.getFechaFin());     // <-- Esta línea es nueva
        dto.setCiudad(torneo.getCiudad());         // <-- Esta línea es nueva

        // Mapea la relación
        if (torneo.getCreadoPor() != null) {
            dto.setCreadoPorNombre(torneo.getCreadoPor().getNombreUsuario()); // <-- Esta línea es nueva
        }

        return dto;
    }

    /**
     * Convierte de DTO (Formulario) -> Entidad (para guardar)
     */
    public Torneo toEntity(TorneoDTO dto) {
        if (dto == null) {
            return null;
        }

        Torneo torneo = new Torneo();
        torneo.setId(dto.getId()); 
        torneo.setNombre(dto.getNombre());
        torneo.setFechaInicio(dto.getFechaInicio());
        torneo.setFechaFin(dto.getFechaFin());
        torneo.setCiudad(dto.getCiudad()); // <-- Esta línea es nueva
        
        // El 'creadoPor' se asigna en el Servicio, no aquí.

        return torneo;
    }
}