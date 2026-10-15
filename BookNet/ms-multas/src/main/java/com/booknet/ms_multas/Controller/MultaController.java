package com.booknet.ms_multas.Controller;

import com.booknet.ms_multas.Model.EstadoMulta;
import com.booknet.ms_multas.Model.Multa;
import com.booknet.ms_multas.Service.MultaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/multas")
public class MultaController {

    private final MultaService multaService;

    public MultaController(MultaService multaService) {
        this.multaService = multaService;
    }

    // LISTAR TODAS
    @GetMapping
    public ResponseEntity<List<Multa>> listarTodas() {
        return ResponseEntity.ok(multaService.listarTodas());
    }

    // BUSCAR POR ID
    @GetMapping("/{id}")
    public ResponseEntity<Multa> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(multaService.buscarPorId(id));
    }

    // LISTAR POR USUARIO
    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<Multa>> listarPorUsuario(@PathVariable Long usuarioId) {
        return ResponseEntity.ok(multaService.listarPorUsuario(usuarioId));
    }

    // LISTAR PENDIENTES DE UN USUARIO
    @GetMapping("/usuario/{usuarioId}/pendientes")
    public ResponseEntity<List<Multa>> listarPendientesPorUsuario(@PathVariable Long usuarioId) {
        return ResponseEntity.ok(multaService.listarPendientesPorUsuario(usuarioId));
    }

    // GENERAR MULTA
    @PostMapping
    public ResponseEntity<Multa> generarMulta(@RequestBody Multa multa) {
        return ResponseEntity.status(HttpStatus.CREATED).body(multaService.generarMulta(multa));
    }

    // REGISTRAR PAGO DE MULTA
    @PatchMapping("/{id}/pagar")
    public ResponseEntity<Multa> pagarMulta(@PathVariable Long id) {
        return ResponseEntity.ok(multaService.pagarMulta(id));
    }

    // ANULAR MULTA
    @PatchMapping("/{id}/anular")
    public ResponseEntity<Multa> anularMulta(@PathVariable Long id) {
        return ResponseEntity.ok(multaService.anularMulta(id));
    }

    // ELIMINAR MULTA
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        multaService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}