package com.daw.CafeLushAPI.controllers;

import com.daw.CafeLushAPI.dtos.ComplementoDTO;
import com.daw.CafeLushAPI.models.entities.Edulcorante;
import com.daw.CafeLushAPI.services.ComplementoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/edulcorantes")
@RequiredArgsConstructor
public class EdulcoranteController {

    private final ComplementoService<Edulcorante> tipoEdulcoranteService;

    @PostMapping
    public ResponseEntity<ComplementoDTO> crear(@RequestBody @Valid ComplementoDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(tipoEdulcoranteService.crear(dto));
    }

    @GetMapping
    public List<ComplementoDTO> obtenerTodos() {
        return tipoEdulcoranteService.obtenerTodos();
    }
}
