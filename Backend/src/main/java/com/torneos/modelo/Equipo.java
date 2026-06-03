package com.torneos.modelo;

import jakarta.persistence.*;
import java.sql.Timestamp;
import java.util.List;
import java.util.Set; 

@Entity
@Table(name = "equipos")
public class Equipo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idEquipo")
    private Long id;
    
    @Column(name = "nombre", nullable = false)
    private String nombre;

    // --- ¡NUEVOS CAMPOS! ---
    @Column(name = "director_tecnico") // (Asegúrate de añadir esta columna a tu SQL)
    private String directorTecnico;

    @Column(name = "ciudad_origen") // (Asegúrate de añadir esta columna a tu SQL)
    private String ciudadOrigen;
    
    // --- (Campos de auditoría) ---
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "creado_por", nullable = false, referencedColumnName = "idUsuarios")
    private Usuario creadoPor;
    
    @Column(name = "creado_en")
    private Timestamp creadoEn;

    // --- (Relaciones inversas que ya teníamos) ---
    @OneToMany(mappedBy = "equipoLocal", fetch = FetchType.LAZY)
    private List<Partido> partidosComoLocal;

    @OneToMany(mappedBy = "equipoVisitante", fetch = FetchType.LAZY)
    private List<Partido> partidosComoVisitante;

    @OneToMany(mappedBy = "equipo", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Jugador> jugadores;

    // --- ¡CORRECCIÓN PRINCIPAL! ---
    // 1. Quitamos @JoinTable.
    // 2. Añadimos 'mappedBy = "equiposInscritos"'
    // (Debe coincidir con el nombre del campo en Torneo.java)
    @ManyToMany(mappedBy = "equiposInscritos", fetch = FetchType.LAZY)
    private Set<Torneo> torneos;

    
    public Equipo() {
    }


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public String getDirectorTecnico() {
		return directorTecnico;
	}


	public void setDirectorTecnico(String directorTecnico) {
		this.directorTecnico = directorTecnico;
	}


	public String getCiudadOrigen() {
		return ciudadOrigen;
	}


	public void setCiudadOrigen(String ciudadOrigen) {
		this.ciudadOrigen = ciudadOrigen;
	}


	public Usuario getCreadoPor() {
		return creadoPor;
	}


	public void setCreadoPor(Usuario creadoPor) {
		this.creadoPor = creadoPor;
	}


	public Timestamp getCreadoEn() {
		return creadoEn;
	}


	public void setCreadoEn(Timestamp creadoEn) {
		this.creadoEn = creadoEn;
	}


	public List<Partido> getPartidosComoLocal() {
		return partidosComoLocal;
	}


	public void setPartidosComoLocal(List<Partido> partidosComoLocal) {
		this.partidosComoLocal = partidosComoLocal;
	}


	public List<Partido> getPartidosComoVisitante() {
		return partidosComoVisitante;
	}


	public void setPartidosComoVisitante(List<Partido> partidosComoVisitante) {
		this.partidosComoVisitante = partidosComoVisitante;
	}


	public List<Jugador> getJugadores() {
		return jugadores;
	}


	public void setJugadores(List<Jugador> jugadores) {
		this.jugadores = jugadores;
	}


	public Set<Torneo> getTorneos() {
		return torneos;
	}


	public void setTorneos(Set<Torneo> torneos) {
		this.torneos = torneos;
	}
    
   
}