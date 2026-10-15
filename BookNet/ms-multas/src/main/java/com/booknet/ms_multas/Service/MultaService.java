package com.booknet.ms_multas.Service;

import com.booknet.ms_multas.Model.EstadoMulta;
import com.booknet.ms_multas.Model.Multa;
import com.booknet.ms_multas.Repository.MultaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class MultaService {

    private final MultaRepository multaRepository;
    private static final Logger log = LoggerFactory.getLogger(MultaService.class);

    public MultaService(MultaRepository multaRepository) {
        this.multaRepository = multaRepository;
    }

    // LISTAR TODAS
    public List<Multa> listarTodas() {
        return multaRepository.findAll();
    }

    // BUSCAR POR ID
    public Multa buscarPorId(Long id) {
        return multaRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("La multa con ID {} no existe.", id);
                    return new RuntimeException("La multa no existe.");
                });
    }

    // LISTAR POR USUARIO
    public List<Multa> listarPorUsuario(Long usuarioId) {
        return multaRepository.findByUsuarioId(usuarioId);
    }

    // LISTAR MULTAS PENDIENTES DE UN USUARIO
    public List<Multa> listarPendientesPorUsuario(Long usuarioId) {
        return multaRepository.findByUsuarioIdAndEstado(usuarioId, EstadoMulta.PENDIENTE);
    }

    // CREAR O GENERAR MULTA
    public Multa generarMulta(Multa multa) {
        log.info("Generando multa por valor de {} para el usuario ID {} por préstamo ID {}",
                multa.getMonto(), multa.getUsuarioId(), multa.getPrestamoId());

        return multaRepository.save(multa);
    }

    // REGISTRAR PAGO DE MULTA
    public Multa pagarMulta(Long id) {
        Multa multa = buscarPorId(id);

        if (multa.getEstado() == EstadoMulta.PAGADA) {
            log.warn("La multa ID {} ya se encuentra pagada.", id);
            throw new RuntimeException("La multa ya fue pagada anteriormente.");
        }

        if (multa.getEstado() == EstadoMulta.ANULADA) {
            log.warn("Intento de pagar una multa anulada ID {}.", id);
            throw new RuntimeException("No se puede pagar una multa anulada.");
        }

        multa.setEstado(EstadoMulta.PAGADA);
        multa.setFechaPago(LocalDateTime.now());

        log.info("Multa ID {} pagada exitosamente.", id);
        return multaRepository.save(multa);
    }

    // ANULAR MULTA
    public Multa anularMulta(Long id) {
        Multa multa = buscarPorId(id);

        multa.setEstado(EstadoMulta.ANULADA);
        log.info("Multa ID {} anulada correctamente.", id);

        return multaRepository.save(multa);
    }

    // ELIMINAR MULTA
    public void eliminar(Long id) {
        buscarPorId(id);
        log.info("Eliminando multa con ID: {}", id);
        multaRepository.deleteById(id);
    }
}