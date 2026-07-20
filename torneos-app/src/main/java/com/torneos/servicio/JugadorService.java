package com.torneos.servicio;

import com.torneos.dto.JugadorDTO;
import java.util.List;
import java.util.Optional;

public interface JugadorService {

    /**
     * Obtiene solo los jugadores de un equipo específico.
     */
    List<JugadorDTO> listarJugadoresPorEquipo(Long idEquipo);

    /**
     * Busca un jugador por su ID.
     */
    Optional<JugadorDTO> buscarPorId(Long idJugador);

    /**
     * Guarda un nuevo jugador y lo ASOCIA a un equipo.
     */
    JugadorDTO guardarJugador(JugadorDTO dto, Long idEquipo);

    /**
     * Actualiza un jugador existente.
     */
    JugadorDTO actualizarJugador(Long idJugador, JugadorDTO dto);

    /**
     * Elimina un jugador.
     */
    void eliminarJugador(Long idJugador);
}