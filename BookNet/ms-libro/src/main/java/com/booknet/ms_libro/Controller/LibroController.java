package com.booknet.ms_libro.Controller;

import com.booknet.ms_libro.Model.Categoria;
import com.booknet.ms_libro.Model.Libro;
import com.booknet.ms_libro.Service.LibroService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/libros")
public class LibroController {

    private final LibroService libroService;
    private static final Logger log = LoggerFactory.getLogger(LibroController.class);

    public LibroController(LibroService libroService) {
        this.libroService = libroService;
    }

    // LISTAR TODOS
    @GetMapping
    public ResponseEntity<List<Libro>> listarLibros() {
        List<Libro> libros = libroService.listarLibros();

        return ResponseEntity.ok(libros);
    }

    // BUSCAR POR ID
    @GetMapping("/{id}")
    public ResponseEntity<Libro> buscarPorId(@PathVariable Long id) {

        Libro libro = libroService.buscarPorId(id);

        return ResponseEntity.ok(libro);
    }

    // BUSCAR POR CATEGORIA
    @GetMapping("/categoria/{categoria}")
    public ResponseEntity<List<Libro>> buscarPorCategoria(@PathVariable Categoria categoria) {
        List<Libro> libros = libroService.buscarPorCategoria(categoria);

        return ResponseEntity.ok(libros);
    }

    // CREAR
    @PostMapping
    public ResponseEntity<Libro> crear(@RequestBody Libro libro) {
        Libro nuevoLibro = libroService.crear(libro);

        return new ResponseEntity<>(nuevoLibro, HttpStatus.CREATED);
    }

    // ACTUALIZAR
    @PutMapping("/{id}")
    public ResponseEntity<Libro> actualizar(@PathVariable Long id, @RequestBody Libro datos) {
        Libro libroActualizado = libroService.actualizar(id, datos);

        return ResponseEntity.ok(libroActualizado);
    }

    // ELIMINAR
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        libroService.eliminar(id);

        return ResponseEntity.noContent().build();
    }
}