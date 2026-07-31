package com.torneos.servicio;

import com.torneos.repositorio.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String correo) throws UsernameNotFoundException {
        // Buscamos por EMAIL, aunque el método se llame 'loadUserByUsername'
        return usuarioRepository.findByCorreoUsuario(correo)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado con email: " + correo));
    }
}
