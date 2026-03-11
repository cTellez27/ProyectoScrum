package com.torneos.servicio;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder; // Asegúrate de importar esto
import org.springframework.stereotype.Service;

import com.torneos.dto.UsuarioDTO;
import com.torneos.mapper.UsuarioMapper;
import com.torneos.modelo.Usuario;
import com.torneos.repositorio.UsuarioRepository;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private UsuarioMapper usuarioMapper;

    @Autowired
    private PasswordEncoder passwordEncoder; 

    @Override
    public List<UsuarioDTO> listarUsuarios() {
        return usuarioRepository.findAll().stream()
                .map(usuarioMapper::toDTO) 
                .collect(Collectors.toList());
    }

    @Override
    public Optional<UsuarioDTO> buscarPorId(Long id) {
        return usuarioRepository.findById(id).map(usuarioMapper::toDTO);
    }

    // He simplificado el método para recibir solo el DTO.
    // Asumimos que agregaste 'contrasenaUsuario' al DTO como te indiqué antes.
    @Override
    public UsuarioDTO registrarUsuario(UsuarioDTO dto) {
        Usuario usuario;

        // --- VALIDACIÓN: CREACIÓN ---
        // Si el ID es nulo, es un usuario nuevo
        if (dto.getId() == null) {
            usuario = new Usuario();
            usuario.setCreadoEn(new Timestamp(System.currentTimeMillis()));
            
            // Validar contraseña obligatoria
            if (dto.getContrasenaUsuario() == null || dto.getContrasenaUsuario().isEmpty()) {
                throw new RuntimeException("La contraseña es obligatoria");
            }

            // Lógica de Roles y Activación (LO NUEVO)
            if ("ORGANIZADOR".equals(dto.getRol())) {
                usuario.setRol("ORGANIZADOR");
                usuario.setActivo(false); // <--- ¡Aprobación requerida!
            } else {
                usuario.setRol("ESPECTADOR");
                usuario.setActivo(true);  // <--- Pasa directo
            }

            // Encriptamos la contraseña
            usuario.setContrasenaUsuario(passwordEncoder.encode(dto.getContrasenaUsuario()));

        } else {
            // --- ACTUALIZACIÓN (Edición de perfil) ---
            usuario = usuarioRepository.findById(dto.getId())
                    .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
            
            // Si viene contraseña nueva, la actualizamos encriptada
            if (dto.getContrasenaUsuario() != null && !dto.getContrasenaUsuario().isEmpty()) {
                usuario.setContrasenaUsuario(passwordEncoder.encode(dto.getContrasenaUsuario()));
            }
            // Mantenemos el rol y estado que ya tenía (o actualizamos si es admin)
        }

        // Mapeo de campos comunes
        usuario.setNombreUsuario(dto.getNombreUsuario());
        usuario.setCorreoUsuario(dto.getCorreoUsuario());
        // Nota: Asegúrate que tu entidad Usuario tenga setNombre/setEmail o setNombreUsuario/setCorreoUsuario
        // según como la definiste.

        Usuario guardado = usuarioRepository.save(usuario);
        return usuarioMapper.toDTO(guardado);
    }

    @Override
    public void eliminarUsuario(Long id) {
        usuarioRepository.deleteById(id);
    }
}