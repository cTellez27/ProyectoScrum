package com.torneos.servicio;

import com.torneos.dto.TarjetaDTO;
import java.util.List;
import java.util.Optional; // ¡Importa Optional!

public interface TarjetaService {

    /**
     * Registra una nueva tarjeta (amarilla o roja) para un jugador en un partido.
     */
    TarjetaDTO registrarTarjeta(TarjetaDTO dto);

    /**
     * Obtiene todas las tarjetas de un partido específico.
     */
    List<TarjetaDTO> listarTarjetasPorPartido(Long idPartido);
    
    /**
     * Elimina un registro de tarjeta (ej. si se cometió un error).
     */
    void eliminarTarjeta(Long idTarjeta);
    
    // --- ¡MÉTODO QUE FALTABA! ---
    /**
     * Busca una tarjeta por su ID (necesario para la redirección al eliminar).
     */
    Optional<TarjetaDTO> buscarPorId(Long idTarjeta);
}