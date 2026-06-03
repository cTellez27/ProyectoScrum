package com.torneos.modelo;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "jugadores")
public class Jugador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idJugador") // <-- Correcto
    private Long id;

    @Column(name = "nombre", nullable = false)
    private String nombre;

    @Column(name = "posicion")
    private String posicion;

    @Column(name = "fecha_nacimiento")
    private LocalDate fechaNacimiento;

    // --- ¡CORRECCIÓN AQUÍ! ---
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
        name = "idEquipo", // Columna FK en esta tabla
        nullable = false,
        referencedColumnName = "idEquipo" // Columna PK en la tabla 'equipos'
    )
    private Equipo equipo;

    
    public Jugador() {
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


	public String getPosicion() {
		return posicion;
	}


	public void setPosicion(String posicion) {
		this.posicion = posicion;
	}


	public LocalDate getFechaNacimiento() {
		return fechaNacimiento;
	}


	public void setFechaNacimiento(LocalDate fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}


	public Equipo getEquipo() {
		return equipo;
	}


	public void setEquipo(Equipo equipo) {
		this.equipo = equipo;
	}
    
    
}