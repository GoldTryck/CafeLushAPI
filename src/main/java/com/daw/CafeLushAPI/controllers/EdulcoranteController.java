package com.daw.CafeLushAPI.controllers;

import com.daw.CafeLushAPI.dtos.EdulcoranteDTO;
import com.daw.CafeLushAPI.services.EdulcoranteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/edulcorantes")
@RequiredArgsConstructor
public class EdulcoranteController {

    private final EdulcoranteService edulcoranteService;

    @PostMapping
    public ResponseEntity<EdulcoranteDTO> crear(@Valid @RequestBody EdulcoranteDTO dto) {
        return new ResponseEntity<>(edulcoranteService.crearEdulcorante(dto), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<EdulcoranteDTO>> obtenerTodos() {
        return ResponseEntity.ok(edulcoranteService.obtenerTodos());
    }
}
