package com.torneos.dto;

import java.time.LocalDateTime;
import org.springframework.format.annotation.DateTimeFormat;

public class PartidoDTO {

    private Long id;
    
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime fecha;
    
    private String resultado; 

    // --- Campos para MOSTRAR ---
    private String torneoNombre;
    private String equipoLocalNombre;
    private String equipoVisitanteNombre;
    private String canchaNombre;
    
    // --- ¡CAMPO NUEVO! ---
    // Lo necesitamos para la navegación de vuelta
    private Long idTorneo; 
    
    // --- Campos para RECIBIR (Formulario) ---
    private Long idEquipoLocal;
    private Long idEquipoVisitante;
    private Long idCancha;

    // Constructor vacío
    public PartidoDTO() {
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

	public String getTorneoNombre() {
		return torneoNombre;
	}

	public void setTorneoNombre(String torneoNombre) {
		this.torneoNombre = torneoNombre;
	}

	public String getEquipoLocalNombre() {
		return equipoLocalNombre;
	}

	public void setEquipoLocalNombre(String equipoLocalNombre) {
		this.equipoLocalNombre = equipoLocalNombre;
	}

	public String getEquipoVisitanteNombre() {
		return equipoVisitanteNombre;
	}

	public void setEquipoVisitanteNombre(String equipoVisitanteNombre) {
		this.equipoVisitanteNombre = equipoVisitanteNombre;
	}

	public String getCanchaNombre() {
		return canchaNombre;
	}

	public void setCanchaNombre(String canchaNombre) {
		this.canchaNombre = canchaNombre;
	}

	public Long getIdTorneo() {
		return idTorneo;
	}

	public void setIdTorneo(Long idTorneo) {
		this.idTorneo = idTorneo;
	}

	public Long getIdEquipoLocal() {
		return idEquipoLocal;
	}

	public void setIdEquipoLocal(Long idEquipoLocal) {
		this.idEquipoLocal = idEquipoLocal;
	}

	public Long getIdEquipoVisitante() {
		return idEquipoVisitante;
	}

	public void setIdEquipoVisitante(Long idEquipoVisitante) {
		this.idEquipoVisitante = idEquipoVisitante;
	}

	public Long getIdCancha() {
		return idCancha;
	}

	public void setIdCancha(Long idCancha) {
		this.idCancha = idCancha;
	}

    
}