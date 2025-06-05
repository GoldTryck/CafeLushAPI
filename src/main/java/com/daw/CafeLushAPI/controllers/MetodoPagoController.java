package com.daw.CafeLushAPI.controllers;

import com.daw.CafeLushAPI.dtos.request.MetodoPagoRequest;
import com.daw.CafeLushAPI.dtos.response.MetodoPagoResponse;
import com.daw.CafeLushAPI.services.MetodoPagoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/metodos-pago")
@RequiredArgsConstructor
public class MetodoPagoController {

    private final MetodoPagoService metodoPagoService;

    @PostMapping
    public ResponseEntity<MetodoPagoResponse> crear(@RequestBody @Valid MetodoPagoRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(metodoPagoService.crearMetodoPago(request));
    }

    @GetMapping
    public ResponseEntity<List<MetodoPagoResponse>> obtenerTodos() {
        return ResponseEntity.ok(metodoPagoService.obtenerTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<MetodoPagoResponse> obtenerPorId(@PathVariable Integer id) {
        return ResponseEntity.ok(metodoPagoService.obtenerPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<MetodoPagoResponse> actualizar(@PathVariable Integer id,
                                                         @RequestBody @Valid MetodoPagoRequest request) {
        return ResponseEntity.ok(metodoPagoService.actualizarMetodoPago(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        metodoPagoService.eliminarMetodoPago(id);
        return ResponseEntity.noContent().build();
    }
}