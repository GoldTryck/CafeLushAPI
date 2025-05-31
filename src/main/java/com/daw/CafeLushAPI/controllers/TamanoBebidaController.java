package com.daw.CafeLushAPI.controllers;

import com.daw.CafeLushAPI.dtos.TamanoBebidaDTO;
import com.daw.CafeLushAPI.services.TamanoBebidaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tamano-bebidas")
@RequiredArgsConstructor
public class TamanoBebidaController {

    private final TamanoBebidaService tamanoBebidaService;

    @PostMapping
    public ResponseEntity<TamanoBebidaDTO> create(@Valid @RequestBody TamanoBebidaDTO dto) {
        TamanoBebidaDTO created = tamanoBebidaService.createTamanoBebida(dto);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<TamanoBebidaDTO>> getAll() {
        return ResponseEntity.ok(tamanoBebidaService.getAllTamanoBebidas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TamanoBebidaDTO> getById(@PathVariable Integer id) {
        TamanoBebidaDTO dto = tamanoBebidaService.getTamanoBebidaById(id);
        return ResponseEntity.ok(dto);
    }
}
