package com.booknet.ms_reportes.Service;

import com.booknet.ms_reportes.DTO.ReporteMultasDTO;
import com.booknet.ms_reportes.DTO.ReportePrestamosDTO;
import com.booknet.ms_reportes.DTO.ResumenGeneralDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class ReporteService {

    private static final Logger log = LoggerFactory.getLogger(ReporteService.class);

    // OBTENER RESUMEN GENERAL DEL SISTEMA
    public ResumenGeneralDTO obtenerResumenGeneral() {
        log.info("Generando reporte de resumen general del sistema...");

        return ResumenGeneralDTO.builder()
                .totalLibros(150)
                .totalUsuariosActivos(45)
                .totalPrestamosActivos(12)
                .totalMultasPendientes(3)
                .build();
    }

    // OBTENER ESTADÍSTICAS DE PRÉSTAMOS
    public ReportePrestamosDTO obtenerReportePrestamos() {
        log.info("Generando reporte estadístico de préstamos...");

        return ReportePrestamosDTO.builder()
                .totalPrestamosHistoricos(120)
                .prestamosActivos(12)
                .prestamosDevueltos(100)
                .prestamosAtrasados(8)
                .build();
    }

    // OBTENER ESTADÍSTICAS DE MULTAS
    public ReporteMultasDTO obtenerReporteMultas() {
        log.info("Generando reporte estadístico de multas...");

        return ReporteMultasDTO.builder()
                .totalMultasGeneradas(20)
                .totalMultasPagadas(15)
                .totalMultasPendientes(5)
                .montoTotalRecaudado(new BigDecimal("75000.00"))
                .montoTotalPendiente(new BigDecimal("25000.00"))
                .build();
    }
}