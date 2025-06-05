package com.daw.CafeLushAPI.controllers;

import com.daw.CafeLushAPI.dtos.request.OrdenRequest;
import com.daw.CafeLushAPI.dtos.response.OrdenResponse;
import com.daw.CafeLushAPI.services.OrdenService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ordenes")
@RequiredArgsConstructor
public class OrdenController {

    private final OrdenService ordenService;

    // Crear una orden
    @PostMapping
    public ResponseEntity<OrdenResponse> crearOrden(@Valid @RequestBody OrdenRequest request) {
        OrdenResponse ordenCreada = ordenService.crearOrden(request);
        return ResponseEntity.ok(ordenCreada);
    }

    // Obtener todas las ordenes
    @GetMapping
    public ResponseEntity<List<OrdenResponse>> obtenerTodas() {
        List<OrdenResponse> ordenes = ordenService.obtenerTodas();
        return ResponseEntity.ok(ordenes);
    }

    // Obtener orden por id
    @GetMapping("/{id}")
    public ResponseEntity<OrdenResponse> obtenerPorId(@PathVariable Integer id) {
        OrdenResponse orden = ordenService.obtenerPorId(id);
        return ResponseEntity.ok(orden);
    }

    // Obtener ordenes por cliente
    @GetMapping("/cliente/{clienteId}")
    public ResponseEntity<List<OrdenResponse>> obtenerPorCliente(@PathVariable Integer clienteId) {
        List<OrdenResponse> ordenes = ordenService.obtenerPorCliente(clienteId);
        return ResponseEntity.ok(ordenes);
    }

}
