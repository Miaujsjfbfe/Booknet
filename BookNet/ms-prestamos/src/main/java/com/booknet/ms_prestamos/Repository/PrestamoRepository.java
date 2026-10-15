package com.booknet.ms_prestamos.Repository;

import com.booknet.ms_prestamos.Model.EstadoPrestamo;
import com.booknet.ms_prestamos.Model.Prestamo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PrestamoRepository extends JpaRepository<Prestamo, Long> {

    // Buscar todos los préstamos de un usuario específico
    List<Prestamo> findByUsuarioId(Long usuarioId);

    // Buscar todos los préstamos asociados a un libro
    List<Prestamo> findByLibroId(Long libroId);

    // Filtrar por estado (PRESTADO, DEVUELTO, ATRASADO)
    List<Prestamo> findByEstado(EstadoPrestamo estado);

    // Obtener los préstamos activos de un usuario
    List<Prestamo> findByUsuarioIdAndEstado(Long usuarioId, EstadoPrestamo estado);
}