package com.daw.CafeLushAPI.controllers;

import com.daw.CafeLushAPI.dtos.request.SubcategoriaRequest;
import com.daw.CafeLushAPI.dtos.response.SubcategoriaResponse;
import com.daw.CafeLushAPI.services.SubcategoriaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/subcategorias")
@RequiredArgsConstructor
public class SubcategoriaController {

    private final SubcategoriaService subcategoriaService;

    @PostMapping
    public ResponseEntity<SubcategoriaResponse> crear(@RequestBody @Valid SubcategoriaRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(subcategoriaService.crear(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<SubcategoriaResponse> actualizar(@PathVariable Integer id, @RequestBody SubcategoriaRequest request) {
        return ResponseEntity.ok(subcategoriaService.actualizar(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        subcategoriaService.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<SubcategoriaResponse>> obtenerTodas() {
        return ResponseEntity.ok(subcategoriaService.obtenerTodas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SubcategoriaResponse> obtenerPorId(@PathVariable Integer id) {
        return ResponseEntity.ok(subcategoriaService.obtenerPorId(id));
    }
}