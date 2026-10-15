package com.booknet.ms_reportes.Controller;

import com.booknet.ms_reportes.DTO.ReporteMultasDTO;
import com.booknet.ms_reportes.DTO.ReportePrestamosDTO;
import com.booknet.ms_reportes.DTO.ResumenGeneralDTO;
import com.booknet.ms_reportes.Service.ReporteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/reportes")
public class ReporteController {

    private final ReporteService reporteService;

    public ReporteController(ReporteService reporteService) {
        this.reporteService = reporteService;
    }

    // GET: /api/reportes/resumen
    @GetMapping("/resumen")
    public ResponseEntity<ResumenGeneralDTO> obtenerResumenGeneral() {
        return ResponseEntity.ok(reporteService.obtenerResumenGeneral());
    }

    // GET: /api/reportes/prestamos
    @GetMapping("/prestamos")
    public ResponseEntity<ReportePrestamosDTO> obtenerReportePrestamos() {
        return ResponseEntity.ok(reporteService.obtenerReportePrestamos());
    }

    // GET: /api/reportes/multas
    @GetMapping("/multas")
    public ResponseEntity<ReporteMultasDTO> obtenerReporteMultas() {
        return ResponseEntity.ok(reporteService.obtenerReporteMultas());
    }
}