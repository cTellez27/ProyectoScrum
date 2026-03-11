package com.torneos.servicio;

import com.torneos.dto.UsuarioDTO;
import java.util.List;
import java.util.Optional;

public interface UsuarioService {

    List<UsuarioDTO> listarUsuarios();
    Optional<UsuarioDTO> buscarPorId(Long id);
    UsuarioDTO registrarUsuario(UsuarioDTO dto);
    void eliminarUsuario(Long id);
}