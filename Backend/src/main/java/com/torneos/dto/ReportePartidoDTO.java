package com.torneos.dto;

public class ReportePartidoDTO {
    private String equipoLocal;
    private String equipoVisitante;
    private String fecha;
    private String resultado;

    public ReportePartidoDTO(String local, String visitante, String fecha, String resultado) {
        this.equipoLocal = local;
        this.equipoVisitante = visitante;
        this.fecha = fecha;
        this.resultado = resultado;
    }
    // Genera los Getters y Setters obligatoriamente
    public String getEquipoLocal() { return equipoLocal; }
    public String getEquipoVisitante() { return equipoVisitante; }
    public String getFecha() { return fecha; }
    public String getResultado() { return resultado; }
}