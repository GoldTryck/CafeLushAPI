package com.daw.CafeLushAPI.controllers;

import com.daw.CafeLushAPI.dtos.request.AlimentoRequest;
import com.daw.CafeLushAPI.dtos.response.AlimentoResponse;
import com.daw.CafeLushAPI.services.AlimentoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/alimentos")
@RequiredArgsConstructor
public class AlimentoController {

    private final AlimentoService alimentoService;

    @PostMapping
    public ResponseEntity<AlimentoResponse> crear(@RequestBody @Valid AlimentoRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(alimentoService.crearAlimento(request));
    }

    @GetMapping
    public List<AlimentoResponse> obtenerTodos() {
        return alimentoService.obtenerTodos();
    }

    @GetMapping("/{id}")
    public AlimentoResponse obtenerPorId(@PathVariable Integer id) {
        return alimentoService.obtenerPorId(id);
    }

    @PutMapping("/{id}")
    public AlimentoResponse actualizar(@PathVariable Integer id, @RequestBody @Valid AlimentoRequest request) {
        return alimentoService.actualizarAlimento(id, request);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        alimentoService.eliminarAlimento(id);
        return ResponseEntity.noContent().build();
    }
}