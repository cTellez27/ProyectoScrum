package com.torneos.modelo;

import jakarta.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name = "canchas")
public class Cancha {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idCancha") // <-- Correcto
    private Long id;

    @Column(name = "nombre", nullable = false)
    private String nombre;

    @Column(name = "ubicacion")
    private String ubicacion;

    @Column(name = "capacidad")
    private Integer capacidad;

    @Column(name = "creado_en")
    private Timestamp creadoEn;

    @OneToMany(mappedBy = "cancha", fetch = FetchType.LAZY)
    private List<Partido> partidos;

    
    public Cancha() {
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


	public String getUbicacion() {
		return ubicacion;
	}


	public void setUbicacion(String ubicacion) {
		this.ubicacion = ubicacion;
	}


	public Integer getCapacidad() {
		return capacidad;
	}


	public void setCapacidad(Integer capacidad) {
		this.capacidad = capacidad;
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
    
    
}