package com.booknet.ms_libro.Service;

import com.booknet.ms_libro.Model.Categoria;
import com.booknet.ms_libro.Model.Libro;
import com.booknet.ms_libro.Repository.LibroRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LibroService {

    private final LibroRepository libroRepository;
    private static final Logger log = LoggerFactory.getLogger(LibroService.class);

    public LibroService(LibroRepository libroRepository) {
        this.libroRepository = libroRepository;
    }

    // LISTAR
    public List<Libro> listarLibros() {
        return libroRepository.findAll();
    }

    // BUSCAR POR ID
    public Libro buscarPorId(Long id) {
        return libroRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("El libro con ID {} no existe.", id);
                    return new RuntimeException("El libro no existe.");
                });
    }

    // BUSCAR POR CATEGORIA
    public List<Libro> buscarPorCategoria(Categoria categoria) {
        return libroRepository.findByCategoria(categoria);
    }

    // CREAR
    public Libro crear(Libro libro) {
        log.info("Creando libro: {}", libro.getTitulo());

        // Validacion para duplicados por ISBN
        if (libroRepository.existsByIsbn(libro.getIsbn())) {
            log.warn("Intento de crear libro con ISBN duplicado: {}", libro.getIsbn());
            throw new RuntimeException("Ya existe un libro registrado con ese ISBN.");
        }

        return libroRepository.save(libro);
    }

    // ACTUALIZAR
    public Libro actualizar(Long id, Libro datos) {
        Libro libro = buscarPorId(id);

        // Validacion que impide duplicados en ISBN al actualizar
        if (libroRepository.existsByIsbn(datos.getIsbn()) && !libro.getIsbn().equals(datos.getIsbn())) {
            log.warn("Intento de actualizar libro ID {} con un ISBN ya existente: {}", id, datos.getIsbn());
            throw new RuntimeException("El ISBN ingresado ya pertenece a otro libro.");
        }

        libro.setTitulo(datos.getTitulo());
        libro.setAutor(datos.getAutor());
        libro.setIsbn(datos.getIsbn());
        libro.setEditorial(datos.getEditorial());
        libro.setFechaPublicacion(datos.getFechaPublicacion());
        libro.setCategoria(datos.getCategoria());
        libro.setTotalStock(datos.getTotalStock());
        libro.setStockDisponible(datos.getStockDisponible());

        return libroRepository.save(libro);
    }

    // ELIMINAR
    public void eliminar(Long id) {
        buscarPorId(id);
        log.info("Eliminando libro con ID: {}", id);
        libroRepository.deleteById(id);
    }
}