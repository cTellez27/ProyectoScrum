package com.torneos.modelo;

import jakarta.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "torneos")
public class Torneo {

    // ... (id, nombre, fechaInicio, fechaFin, creadoPor, creadoEn, partidos, equiposInscritos) ...
    // (Asegúrate de que todos tus campos anteriores están aquí)
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idTorneo")
    private Long id;

    @Column(name = "nombre", nullable = false)
    private String nombre;

    @Column(name = "fecha_inicio", nullable = false)
    private LocalDate fechaInicio; 

    @Column(name = "fecha_fin", nullable = false)
    private LocalDate fechaFin;

    // --- ¡CAMPO NUEVO! ---
    @Column(name = "ciudad_torneo")
    private String ciudad;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "creado_por", nullable = false, referencedColumnName = "idUsuarios")
    private Usuario creadoPor;
    
    @Column(name = "creado_en")
    private Timestamp creadoEn;

    @OneToMany(mappedBy = "torneo", fetch = FetchType.LAZY)
    private List<Partido> partidos;


    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "torneo_equipos", 
        joinColumns = @JoinColumn(name = "idTorneo", referencedColumnName = "idTorneo"), 
        inverseJoinColumns = @JoinColumn(name = "idEquipo", referencedColumnName = "idEquipo")
    )
    private Set<Equipo> equiposInscritos;

    public Torneo() {
    }
   

	public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
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

	public LocalDate getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(LocalDate fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public LocalDate getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(LocalDate fechaFin) {
		this.fechaFin = fechaFin;
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

	public List<Partido> getPartidos() {
		return partidos;
	}

	public void setPartidos(List<Partido> partidos) {
		this.partidos = partidos;
	}

	public Set<Equipo> getEquiposInscritos() {
		return equiposInscritos;
	}

	public void setEquiposInscritos(Set<Equipo> equiposInscritos) {
		this.equiposInscritos = equiposInscritos;
	}
    
    
}