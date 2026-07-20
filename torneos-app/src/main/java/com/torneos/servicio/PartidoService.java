package com.torneos.servicio;

import com.torneos.dto.PartidoDTO;
import java.util.List;
import java.util.Optional;

public interface PartidoService {

    List<PartidoDTO> listarPartidosPorTorneo(Long idTorneo);
    
    Optional<PartidoDTO> buscarPorId(Long idPartido);

    PartidoDTO guardarPartido(Long idTorneo, PartidoDTO dto);
    
    void eliminarPartido(Long idPartido);
    
    List<PartidoDTO> listarPartidosPendientes();

    List<PartidoDTO> listarPartidosJugados();
    PartidoDTO registrarResultado(Long idPartido, String resultado);
}