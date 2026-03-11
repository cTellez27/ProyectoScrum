package com.torneos.repositorio;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import com.torneos.modelo.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
	Optional<Usuario> findByCorreoUsuario(String correoUsuario);
	List<Usuario> findByRolAndActivo(String rol, boolean activo);
    List<Usuario> findByRol(String rol);
}