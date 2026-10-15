package com.booknet.ms_reportes.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReporteMultasDTO {
    private long totalMultasGeneradas;
    private long totalMultasPagadas;
    private long totalMultasPendientes;
    private BigDecimal montoTotalRecaudado;
    private BigDecimal montoTotalPendiente;
}