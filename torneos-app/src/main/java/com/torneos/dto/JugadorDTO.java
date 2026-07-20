package com.torneos.dto;

import java.time.LocalDate;

public class JugadorDTO {

    private Long id;
    private String nombre;
    private String posicion;
    private LocalDate fechaNacimiento;

    // No necesitamos el 'idEquipo' aquí, porque el controlador
    // ya sabrá en qué equipo estamos por la URL.

    // Constructor vacío
    public JugadorDTO() {
    }

    // --- Getters y Setters ---
    // (Genera todos los getters y setters)

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
}