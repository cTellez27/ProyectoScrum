package com.torneos.mapper;

import com.torneos.dto.UsuarioDTO;
import com.torneos.modelo.Usuario;
import org.springframework.stereotype.Component;

@Component
public class UsuarioMapper {

    public UsuarioDTO toDTO(Usuario usuario) {
        if (usuario == null) return null;
        UsuarioDTO dto = new UsuarioDTO();
        dto.setId(usuario.getId());
        dto.setNombreUsuario(usuario.getNombreUsuario());
        dto.setCorreoUsuario(usuario.getCorreoUsuario());
        dto.setRol(usuario.getRol());
        return dto;
    }

    public Usuario toEntity(UsuarioDTO dto, Usuario usuario) {
        if (dto == null) return null;
        // No mapeamos el ID, se maneja en el servicio
        usuario.setNombreUsuario(dto.getNombreUsuario());
        usuario.setCorreoUsuario(dto.getCorreoUsuario());
        usuario.setRol(dto.getRol());
        return usuario;
    }
}