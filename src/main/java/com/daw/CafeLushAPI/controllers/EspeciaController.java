package com.daw.CafeLushAPI.controllers;

import com.daw.CafeLushAPI.dtos.ComplementoDTO;
import com.daw.CafeLushAPI.models.entities.Especia;
import com.daw.CafeLushAPI.services.ComplementoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/especias")
@RequiredArgsConstructor
public class EspeciaController {

    private final ComplementoService<Especia> especiaService;

    @PostMapping
    public ResponseEntity<ComplementoDTO> crear(@RequestBody @Valid ComplementoDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(especiaService.crear(dto));
    }

    @GetMapping
    public List<ComplementoDTO> obtenerTodos() {
        return especiaService.obtenerTodos();
    }
}
