package com.daw.CafeLushAPI.controllers;

import com.daw.CafeLushAPI.dtos.request.DireccionRequest;
import com.daw.CafeLushAPI.dtos.response.DireccionResponse;
import com.daw.CafeLushAPI.services.DireccionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("api/direcciones")
@RequiredArgsConstructor
public class DireccionController {

    private final DireccionService direccionService;

    @PostMapping
    public ResponseEntity<DireccionResponse> crearDireccion(@Valid @RequestBody DireccionRequest request) {
        DireccionResponse response = direccionService.crearDireccion(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<DireccionResponse>> obtenerTodas() {
        return ResponseEntity.ok(direccionService.obtenerTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DireccionResponse> obtenerPorId(@PathVariable Integer id) {
        DireccionResponse response = direccionService.obtenerPorId(id);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DireccionResponse> actualizarDireccion(@PathVariable Integer id,
                                                                 @Valid @RequestBody DireccionRequest request) {
        DireccionResponse response = direccionService.actualizarDireccion(id, request);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarDireccion(@PathVariable Integer id) {
        direccionService.eliminarDireccion(id);
        return ResponseEntity.noContent().build();
    }
}
