package com.booknet.ms_reportes.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResumenGeneralDTO {
    private long totalLibros;
    private long totalUsuariosActivos;
    private long totalPrestamosActivos;
    private long totalMultasPendientes;
}
