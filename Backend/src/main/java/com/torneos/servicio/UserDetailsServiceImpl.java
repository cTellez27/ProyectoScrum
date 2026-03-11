package com.torneos.servicio;

import com.torneos.modelo.Usuario;
import com.torneos.repositorio.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String correo) throws UsernameNotFoundException {
        // Buscamos por EMAIL, aunque el método se llame 'loadUserByUsername'
        Usuario usuario = usuarioRepository.findByCorreoUsuario(correo)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado con email: " + correo));

        return new User(
                usuario.getCorreoUsuario(),
                usuario.getPassword(), // Debe estar encriptada en BD
                usuario.isActivo(),    // enabled (true=espectador, false=organizador pendiente)
                true, // accountNonExpired
                true, // credentialsNonExpired
                true, // accountNonLocked
                Collections.singletonList(new SimpleGrantedAuthority(usuario.getRol()))
        );
    }
}