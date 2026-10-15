package com.booknet.ms_prestamos.Model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "prestamos")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Prestamo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long usuarioId;

    @Column(nullable = false)
    private Long libroId;

    @Column(nullable = false)
    private LocalDate fechaPrestamo;

    @Column(nullable = false)
    private LocalDate fechaDevolucionEsperada;

    private LocalDate fechaDevolucionReal;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private EstadoPrestamo estado;

    @PrePersist
    public void prePersist() {
        if (this.fechaPrestamo == null) {
            this.fechaPrestamo = LocalDate.now();
        }
        if (this.fechaDevolucionEsperada == null) {
            // Por defecto damos 14 días para devolver
            this.fechaDevolucionEsperada = this.fechaPrestamo.plusDays(14);
        }
        if (this.estado == null) {
            this.estado = EstadoPrestamo.PRESTADO;
        }
    }
}