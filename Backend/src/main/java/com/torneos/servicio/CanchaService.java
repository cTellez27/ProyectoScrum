package com.torneos.servicio;

import com.torneos.dto.CanchaDTO;
import java.util.List;
import java.util.Optional;

public interface CanchaService {

    // ¡Este es el método que tu PartidoController necesita!
    List<CanchaDTO> listarCanchas();
    
    Optional<CanchaDTO> buscarPorId(Long id);
    
    CanchaDTO guardarCancha(CanchaDTO dto);
    
    void eliminarCancha(Long id);
}