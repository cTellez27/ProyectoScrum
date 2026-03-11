package com.torneos.dto;

public class EquipoDTO {

    private Long id;
    private String nombre;
    private String directorTecnico; // Nuevo
    private String ciudadOrigen; // Nuevo
    private String creadoPorNombre; 

    public EquipoDTO() {
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

	public String getCreadoPorNombre() {
		return creadoPorNombre;
	}

	public void setCreadoPorNombre(String creadoPorNombre) {
		this.creadoPorNombre = creadoPorNombre;
	}
    
    
}