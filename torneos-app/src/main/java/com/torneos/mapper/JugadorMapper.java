package com.torneos.mapper;

import com.torneos.dto.JugadorDTO;
import com.torneos.modelo.Jugador;
import org.springframework.stereotype.Component;

@Component
public class JugadorMapper {

    public JugadorDTO toDTO(Jugador jugador) {
        if (jugador == null) {
            return null;
        }
        JugadorDTO dto = new JugadorDTO();
        dto.setId(jugador.getId());
        dto.setNombre(jugador.getNombre());
        dto.setPosicion(jugador.getPosicion());
        dto.setFechaNacimiento(jugador.getFechaNacimiento());
        return dto;
    }

    public Jugador toEntity(JugadorDTO dto) {
        if (dto == null) {
            return null;
        }
        Jugador jugador = new Jugador();
        jugador.setId(dto.getId());
        jugador.setNombre(dto.getNombre());
        jugador.setPosicion(dto.getPosicion());
        jugador.setFechaNacimiento(dto.getFechaNacimiento());
        
        // La relación 'equipo' NO se asigna aquí.
        // Se asigna en el Servicio.
        
        return jugador;
    }
}