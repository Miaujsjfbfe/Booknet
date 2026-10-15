package com.booknet.ms_reportes.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReportePrestamosDTO {
    private long totalPrestamosHistoricos;
    private long prestamosActivos;
    private long prestamosDevueltos;
    private long prestamosAtrasados;
}