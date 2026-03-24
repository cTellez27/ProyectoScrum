package com.torneos.servicio;

import com.torneos.dto.EquipoDTO;
import com.torneos.dto.TorneoDTO;
import java.util.List;
import java.util.Optional;
import java.util.Set; // Importa Set

public interface TorneoService {

    List<TorneoDTO> listarTorneos();
    Optional<TorneoDTO> buscarPorId(Long id);
    TorneoDTO guardarTorneo(TorneoDTO dto);
    void eliminarTorneo(Long id);

    // --- ¡MÉTODO NUEVO! ---
    // Obtiene los equipos que ya están inscritos en un torneo
    Set<EquipoDTO> obtenerEquiposInscritos(Long idTorneo);
    
    // --- ¡MÉTODO NUEVO! ---
    // Inscribe un equipo en un torneo
    void inscribirEquipoEnTorneo(Long idTorneo, Long idEquipo);

    // --- ¡MÉTODO NUEVO! ---
    // Desinscribe un equipo de un torneo
    void desinscribirEquipoDeTorneo(Long idTorneo, Long idEquipo);
}