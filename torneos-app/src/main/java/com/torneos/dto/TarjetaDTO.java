package com.torneos.dto;

public class TarjetaDTO {

    private Long id;
    private Long idPartido;
    private Long idJugador;
    private String tipoTarjeta;
    private Integer minutoJuego;
    
    // Campos extra para mostrar en listas
    private String nombreJugador;

    // Constructor vacío
    public TarjetaDTO() {
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getIdPartido() {
		return idPartido;
	}

	public void setIdPartido(Long idPartido) {
		this.idPartido = idPartido;
	}

	public Long getIdJugador() {
		return idJugador;
	}

	public void setIdJugador(Long idJugador) {
		this.idJugador = idJugador;
	}

	public String getTipoTarjeta() {
		return tipoTarjeta;
	}

	public void setTipoTarjeta(String tipoTarjeta) {
		this.tipoTarjeta = tipoTarjeta;
	}

	public Integer getMinutoJuego() {
		return minutoJuego;
	}

	public void setMinutoJuego(Integer minutoJuego) {
		this.minutoJuego = minutoJuego;
	}

	public String getNombreJugador() {
		return nombreJugador;
	}

	public void setNombreJugador(String nombreJugador) {
		this.nombreJugador = nombreJugador;
	}

    // Getters y Setters
    // (Genera todos los getters y setters)
}