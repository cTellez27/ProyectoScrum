package com.torneos.modelo;

import jakarta.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Entity
@Table(name = "partidos")
public class Partido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idPartido") // <-- Correcto
    private Long id;

    @Column(name = "fecha", nullable = false)
    private LocalDateTime fecha;

    @Column(name = "resultado")
    private String resultado;

    @Column(name = "creado_en")
    private Timestamp creadoEn;

    // --- ¡TODAS ESTAS CORREGIDAS! ---
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
        name = "idTorneo", 
        nullable = false, 
        referencedColumnName = "idTorneo" // Apunta a PK de 'torneos'
    )
    private Torneo torneo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
        name = "equipo_local", 
        nullable = false,
        referencedColumnName = "idEquipo" // Apunta a PK de 'equipos'
    )
    private Equipo equipoLocal;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
        name = "equipo_visitante", 
        nullable = false,
        referencedColumnName = "idEquipo" // Apunta a PK de 'equipos'
    )
    private Equipo equipoVisitante;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
        name = "idCancha", 
        nullable = false,
        referencedColumnName = "idCancha" // Apunta a PK de 'canchas'
    )
    private Cancha cancha;

    
    public Partido() {
    }


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public LocalDateTime getFecha() {
		return fecha;
	}


	public void setFecha(LocalDateTime fecha) {
		this.fecha = fecha;
	}


	public String getResultado() {
		return resultado;
	}


	public void setResultado(String resultado) {
		this.resultado = resultado;
	}


	public Timestamp getCreadoEn() {
		return creadoEn;
	}


	public void setCreadoEn(Timestamp creadoEn) {
		this.creadoEn = creadoEn;
	}


	public Torneo getTorneo() {
		return torneo;
	}


	public void setTorneo(Torneo torneo) {
		this.torneo = torneo;
	}


	public Equipo getEquipoLocal() {
		return equipoLocal;
	}


	public void setEquipoLocal(Equipo equipoLocal) {
		this.equipoLocal = equipoLocal;
	}


	public Equipo getEquipoVisitante() {
		return equipoVisitante;
	}


	public void setEquipoVisitante(Equipo equipoVisitante) {
		this.equipoVisitante = equipoVisitante;
	}


	public Cancha getCancha() {
		return cancha;
	}


	public void setCancha(Cancha cancha) {
		this.cancha = cancha;
	}
    
    
}