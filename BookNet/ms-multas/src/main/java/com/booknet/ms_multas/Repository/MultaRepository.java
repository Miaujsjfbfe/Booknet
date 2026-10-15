package com.booknet.ms_multas.Repository;

import com.booknet.ms_multas.Model.EstadoMulta;
import com.booknet.ms_multas.Model.Multa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MultaRepository extends JpaRepository<Multa, Long> {

    // Buscar todas las multas de un usuario
    List<Multa> findByUsuarioId(Long usuarioId);

    // Buscar multas asociadas a un préstamo específico
    List<Multa> findByPrestamoId(Long prestamoId);

    // Filtrar por estado (PENDIENTE, PAGADA, ANULADA)
    List<Multa> findByEstado(EstadoMulta estado);

    // Buscar multas pendientes de un usuario
    List<Multa> findByUsuarioIdAndEstado(Long usuarioId, EstadoMulta estado);
}