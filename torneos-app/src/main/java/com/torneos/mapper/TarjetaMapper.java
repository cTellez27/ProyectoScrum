package com.torneos.mapper;

import com.torneos.dto.TarjetaDTO;
import com.torneos.modelo.Jugador;
import com.torneos.modelo.Partido;
import com.torneos.modelo.Tarjeta;
import org.springframework.stereotype.Component;

@Component
public class TarjetaMapper {

    public TarjetaDTO toDTO(Tarjeta tarjeta) {
        if (tarjeta == null) return null;
        TarjetaDTO dto = new TarjetaDTO();
        dto.setId(tarjeta.getId());
        dto.setTipoTarjeta(tarjeta.getTipoTarjeta());
        dto.setMinutoJuego(tarjeta.getMinutoJuego());
        
        if (tarjeta.getPartido() != null) {
            dto.setIdPartido(tarjeta.getPartido().getId());
        }
        if (tarjeta.getJugador() != null) {
            dto.setIdJugador(tarjeta.getJugador().getId());
            dto.setNombreJugador(tarjeta.getJugador().getNombre());
        }
        return dto;
    }

    public Tarjeta toEntity(TarjetaDTO dto, Partido partido, Jugador jugador) {
        if (dto == null) return null;
        Tarjeta tarjeta = new Tarjeta();
        tarjeta.setId(dto.getId());
        tarjeta.setTipoTarjeta(dto.getTipoTarjeta());
        tarjeta.setMinutoJuego(dto.getMinutoJuego());
        tarjeta.setPartido(partido); // Asigna la entidad completa
        tarjeta.setJugador(jugador); // Asigna la entidad completa
        return tarjeta;
    }
}