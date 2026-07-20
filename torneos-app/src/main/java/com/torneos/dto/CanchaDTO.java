package com.torneos.dto;

public class CanchaDTO {

    private Long id;
    private String nombre;
    private String ubicacion;
    private Integer capacidad;

    // Constructor vacío
    public CanchaDTO() {
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
}