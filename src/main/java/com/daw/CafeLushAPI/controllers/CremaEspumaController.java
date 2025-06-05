package com.daw.CafeLushAPI.controllers;

import com.daw.CafeLushAPI.dtos.ComplementoDTO;
import com.daw.CafeLushAPI.models.entities.CremaEspuma;
import com.daw.CafeLushAPI.services.ComplementoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/crema-espuma")
@RequiredArgsConstructor
public class CremaEspumaController {

    private final ComplementoService<CremaEspuma> cremaEapumaService;

    @PostMapping
    public ResponseEntity<ComplementoDTO> crear(@RequestBody @Valid ComplementoDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(cremaEapumaService.crear(dto));
    }

    @GetMapping
    public List<ComplementoDTO> obtenerTodos() {
        return cremaEapumaService.obtenerTodos();
    }
}
