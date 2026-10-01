package com.booknet.ms_libro.Repository;


import com.booknet.ms_libro.Model.Categoria;
import com.booknet.ms_libro.Model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LibroRepository extends JpaRepository<Libro, Long> {

    // Buscar por ISBN
    Optional<Libro> findByIsbn(String isbn);

    // Existe por ISBN
    Optional<Libro> existsByIsbn(String isbn);


    // Buscar libros por categoría
    List<Libro> findByCategoria(Categoria categoria);

    // Buscar libros por título (coincidencia parcial, ignorando mayúsculas/minúsculas)
    List<Libro> findByTituloContainingIgnoreCase(String titulo);

    // Buscar libros por autor
    List<Libro> findByAutorContainingIgnoreCase(String autor);

    // Obtener todos los libros que se encuentran disponibles para préstamo/compra
    List<Libro> findByEstaDisponibleTrue();

}
