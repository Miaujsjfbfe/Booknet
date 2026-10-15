package com.booknet.ms_prestamos.Controller;

import com.booknet.ms_prestamos.Model.EstadoPrestamo;
import com.booknet.ms_prestamos.Model.Prestamo;
import com.booknet.ms_prestamos.Service.PrestamoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/prestamos")
public class PrestamoController {

    private final PrestamoService prestamoService;

    public PrestamoController(PrestamoService prestamoService) {
        this.prestamoService = prestamoService;
    }

    // LISTAR TODOS
    @GetMapping
    public ResponseEntity<List<Prestamo>> listarTodos() {
        return ResponseEntity.ok(prestamoService.listarTodos());
    }

    // BUSCAR POR ID
    @GetMapping("/{id}")
    public ResponseEntity<Prestamo> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(prestamoService.buscarPorId(id));
    }

    // LISTAR POR USUARIO
    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<Prestamo>> listarPorUsuario(@PathVariable Long usuarioId) {
        return ResponseEntity.ok(prestamoService.listarPorUsuario(usuarioId));
    }

    // LISTAR POR LIBRO
    @GetMapping("/libro/{libroId}")
    public ResponseEntity<List<Prestamo>> listarPorLibro(@PathVariable Long libroId) {
        return ResponseEntity.ok(prestamoService.listarPorLibro(libroId));
    }

    // LISTAR POR ESTADO (PRESTADO, DEVUELTO, ATRASADO)
    @GetMapping("/estado/{estado}")
    public ResponseEntity<List<Prestamo>> listarPorEstado(@PathVariable EstadoPrestamo estado) {
        return ResponseEntity.ok(prestamoService.listarPorEstado(estado));
    }

    // CREAR PRÉSTAMO
    @PostMapping
    public ResponseEntity<Prestamo> crear(@RequestBody Prestamo prestamo) {
        return ResponseEntity.status(HttpStatus.CREATED).body(prestamoService.crear(prestamo));
    }

    // REGISTRAR DEVOLUCIÓN DE LIBRO
    @PatchMapping("/{id}/devolver")
    public ResponseEntity<Prestamo> registrarDevolucion(@PathVariable Long id) {
        return ResponseEntity.ok(prestamoService.registrarDevolucion(id));
    }

    // ELIMINAR PRÉSTAMO
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        prestamoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}