package com.torneos.modelo;

import jakarta.persistence.*;

@Entity
@Table(name = "tarjetas")
public class Tarjeta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tarjeta") // Coincide con tu SQL
    private Long id;

    @Column(name = "tipo_tarjeta", nullable = false)
    private String tipoTarjeta; // AMARILLA o ROJA

    @Column(name = "minuto_juego")
    private Integer minutoJuego;

    // --- Relaciones ---

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_partido", nullable = false, referencedColumnName = "idPartido")
    private Partido partido;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_jugador", nullable = false, referencedColumnName = "idJugador")
    private Jugador jugador;

    // Constructor vacío
    public Tarjeta() {
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public Partido getPartido() {
		return partido;
	}

	public void setPartido(Partido partido) {
		this.partido = partido;
	}

	public Jugador getJugador() {
		return jugador;
	}

	public void setJugador(Jugador jugador) {
		this.jugador = jugador;
	}

    
}