package com.booknet.ms_prestamos.Service;

import com.booknet.ms_prestamos.Model.EstadoPrestamo;
import com.booknet.ms_prestamos.Model.Prestamo;
import com.booknet.ms_prestamos.Repository.PrestamoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class PrestamoService {

    private final PrestamoRepository prestamoRepository;
    private static final Logger log = LoggerFactory.getLogger(PrestamoService.class);

    public PrestamoService(PrestamoRepository prestamoRepository) {
        this.prestamoRepository = prestamoRepository;
    }

    // LISTAR TODOS
    public List<Prestamo> listarTodos() {
        return prestamoRepository.findAll();
    }

    // BUSCAR POR ID
    public Prestamo buscarPorId(Long id) {
        return prestamoRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("El préstamo con ID {} no existe.", id);
                    return new RuntimeException("El préstamo no existe.");
                });
    }

    // LISTAR POR USUARIO
    public List<Prestamo> listarPorUsuario(Long usuarioId) {
        return prestamoRepository.findByUsuarioId(usuarioId);
    }

    // LISTAR POR LIBRO
    public List<Prestamo> listarPorLibro(Long libroId) {
        return prestamoRepository.findByLibroId(libroId);
    }

    // LISTAR POR ESTADO
    public List<Prestamo> listarPorEstado(EstadoPrestamo estado) {
        return prestamoRepository.findByEstado(estado);
    }

    // CREAR PRÉSTAMO
    public Prestamo crear(Prestamo prestamo) {
        log.info("Registrando nuevo préstamo para el usuario ID {} y libro ID {}",
                prestamo.getUsuarioId(), prestamo.getLibroId());

        return prestamoRepository.save(prestamo);
    }

    // REGISTRAR DEVOLUCIÓN DE LIBRO
    public Prestamo registrarDevolucion(Long id) {
        Prestamo prestamo = buscarPorId(id);

        if (prestamo.getEstado() == EstadoPrestamo.DEVUELTO) {
            log.warn("El préstamo ID {} ya fue devuelto anteriormente.", id);
            throw new RuntimeException("El préstamo ya se encuentra devuelto.");
        }

        prestamo.setFechaDevolucionReal(LocalDate.now());
        prestamo.setEstado(EstadoPrestamo.DEVUELTO);

        log.info("Libro devuelto con éxito para el préstamo ID {}", id);
        return prestamoRepository.save(prestamo);
    }

    // ELIMINAR PRÉSTAMO
    public void eliminar(Long id) {
        buscarPorId(id);
        log.info("Eliminando préstamo con ID: {}", id);
        prestamoRepository.deleteById(id);
    }
}