package com.torneos.servicio;

import com.torneos.dto.EquipoDTO;
import java.util.List;
import java.util.Optional;

public interface EquipoService {
    
    List<EquipoDTO> listarEquipos();
    EquipoDTO guardarEquipo(EquipoDTO equipoDTO);
    Optional<EquipoDTO> buscarEquipoPorId(Long id);
    void eliminarEquipo(Long id);

    // --- ¡MÉTODO NUEVO! ---
    // Obtiene equipos que NO están inscritos en un torneo específico
    List<EquipoDTO> obtenerEquiposNoInscritos(Long idTorneo);
}