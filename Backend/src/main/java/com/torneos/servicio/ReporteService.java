package com.torneos.servicio;

import com.torneos.dto.ReportePartidoDTO;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ReporteService {

    public byte[] generarReportePdf(List<ReportePartidoDTO> partidos, String titulo) throws JRException, FileNotFoundException {
        // 1. Cargar el archivo y compilarlo
        File file = ResourceUtils.getFile("classpath:reportes/reporte_partidos.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());

        // 2. Fuente de datos (Nuestra lista de partidos)
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(partidos);

        // 3. Parámetros (El título dinámico)
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("tituloReporte", titulo);

        // 4. Llenar el reporte
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);

        // 5. Exportar a PDF (bytes)
        return JasperExportManager.exportReportToPdf(jasperPrint);
    }
}