package com.torneos.controller;

import com.torneos.dto.PartidoDTO;
import com.torneos.dto.ReportePartidoDTO; // El DTO sencillo para Jasper
import com.torneos.servicio.PartidoService;
import com.torneos.servicio.ReporteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/reportes")
public class ReporteController {

    @Autowired private ReporteService reporteService;
    @Autowired private PartidoService partidoService;

    // Para convertir LocalDateTime a String bonito
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");

    @GetMapping("/jugados")
    public ResponseEntity<byte[]> descargarJugados() {
        try {
            // 1. Traemos los DTOs del servicio (ya filtrados por NO "P.J.")
            List<PartidoDTO> listaPartidos = partidoService.listarPartidosJugados();

            // 2. Convertimos PartidoDTO -> ReportePartidoDTO (Exclusivo para Jasper)
            List<ReportePartidoDTO> dataJasper = new ArrayList<>();
            
            for (PartidoDTO dto : listaPartidos) {
                String fechaStr = (dto.getFecha() != null) ? dto.getFecha().format(formatter) : "S/F";
                
                dataJasper.add(new ReportePartidoDTO(
                    dto.getEquipoLocalNombre(),     // <--- Usamos tus campos de visualización
                    dto.getEquipoVisitanteNombre(), 
                    fechaStr,
                    dto.getResultado()
                ));
            }

            // 3. Generamos el PDF
            byte[] pdfBytes = reporteService.generarReportePdf(dataJasper, "Reporte de Partidos Jugados");

            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=jugados.pdf")
                    .contentType(MediaType.APPLICATION_PDF)
                    .body(pdfBytes);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/pendientes")
    public ResponseEntity<byte[]> descargarPendientes() {
        try {
            // 1. Traemos los pendientes ("P.J.")
            List<PartidoDTO> listaPartidos = partidoService.listarPartidosPendientes();

            List<ReportePartidoDTO> dataJasper = new ArrayList<>();
            for (PartidoDTO dto : listaPartidos) {
                String fechaStr = (dto.getFecha() != null) ? dto.getFecha().format(formatter) : "S/F";

                dataJasper.add(new ReportePartidoDTO(
                    dto.getEquipoLocalNombre(),
                    dto.getEquipoVisitanteNombre(),
                    fechaStr,
                    "POR JUGAR" // O dto.getResultado() que dirá "P.J."
                ));
            }

            byte[] pdfBytes = reporteService.generarReportePdf(dataJasper, "Próximos Encuentros");

            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=pendientes.pdf")
                    .contentType(MediaType.APPLICATION_PDF)
                    .body(pdfBytes);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }
}