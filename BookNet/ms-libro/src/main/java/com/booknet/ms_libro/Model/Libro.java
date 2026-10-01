package com.booknet.ms_libro.Model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "libros")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Libro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 200)
    private String titulo;

    @Column(nullable = false, length = 150)
    private String autor;

    @Column(nullable = false, unique = true, length = 20)
    private String isbn;

    @Column(length = 100)
    private String editorial;

    private LocalDate fechaPublicacion;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "categoria_id", nullable = false)
    private Categoria categoria;

    @Column(nullable = false)
    private Integer totalStock;

    @Column(nullable = false)
    private Integer stockDisponible;

    @Column(nullable = false)
    private Boolean estaDisponible;


    @PrePersist
    @PreUpdate
    public void updateAvailability() {
        // Garantizar que stockDisponible no sea nulo antes de evaluar
        int stock = (this.stockDisponible != null) ? this.stockDisponible : 0;

        // Evaluar la disponibilidad basada exclusivamente en el stock disponible
        this.estaDisponible = stock > 0;
    }
}