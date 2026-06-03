package com.torneos.modelo;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.Collections;
import java.util.Collection;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "usuarios")
public class Usuario implements UserDetails{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idUsuarios") // <-- Correcto
    private Long id;

    @Column(name = "Nombre_Usuario", nullable = false)
    private String nombreUsuario;

    @Column(name = "Correo_Usuario", nullable = false, unique = true)
    private String correoUsuario;

    @Column(name = "Contraseña_Usuario", nullable = false)
    private String contrasenaUsuario;

    @Column(name = "Rol", nullable = false)
    private String rol;
    
    @Column(name = "Activo", nullable = false)
    private boolean activo;

    @Column(name = "creado_en")
    private Timestamp creadoEn;

    // Relaciones inversas
    @OneToMany(mappedBy = "creadoPor", fetch = FetchType.LAZY)
    private List<Equipo> equiposCreados;

    @OneToMany(mappedBy = "creadoPor", fetch = FetchType.LAZY)
    private List<Torneo> torneosCreados;
   
    public Usuario() {
    }

    
	public boolean isActivo() {
		return activo;
	}


	public void setActivo(boolean activo) {
		this.activo = activo;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getNombreUsuario() {
		return nombreUsuario;
	}


	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}


	public String getCorreoUsuario() {
		return correoUsuario;
	}


	public void setCorreoUsuario(String correoUsuario) {
		this.correoUsuario = correoUsuario;
	}


	public String getContrasenaUsuario() {
		return contrasenaUsuario;
	}


	public void setContrasenaUsuario(String contrasenaUsuario) {
		this.contrasenaUsuario = contrasenaUsuario;
	}


	public String getRol() {
		return rol;
	}


	public void setRol(String rol) {
		this.rol = rol;
	}

	public Timestamp getCreadoEn() {
		return creadoEn;
	}


	public void setCreadoEn(Timestamp creadoEn) {
		this.creadoEn = creadoEn;
	}


	public List<Equipo> getEquiposCreados() {
		return equiposCreados;
	}


	public void setEquiposCreados(List<Equipo> equiposCreados) {
		this.equiposCreados = equiposCreados;
	}


	public List<Torneo> getTorneosCreados() {
		return torneosCreados;
	}


	public void setTorneosCreados(List<Torneo> torneosCreados) {
		this.torneosCreados = torneosCreados;
	}
	
	@Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // Convierte el texto de tu BD ("ADMINISTRADOR") en un permiso
        return Collections.singletonList(new SimpleGrantedAuthority(this.rol));
    }

    @Override
    public String getPassword() {
        return this.contrasenaUsuario; // Mapeamos tu campo
    }

    @Override
    public String getUsername() {
        return this.correoUsuario; // Usamos el CORREO para el login
    }

    @Override
    public boolean isAccountNonExpired() { return true; }

    @Override
    public boolean isAccountNonLocked() { return true; }

    @Override
    public boolean isCredentialsNonExpired() { return true; }

    
}