package com.daw.CafeLushAPI.controllers;

import com.daw.CafeLushAPI.dtos.request.BebidaBaseRequest;
import com.daw.CafeLushAPI.dtos.response.BebidaBaseResponse;
import com.daw.CafeLushAPI.services.BebidaBaseService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bebidas-base")
@RequiredArgsConstructor
public class BebidaBaseController {

    private final BebidaBaseService bebidaBaseService;

    @PostMapping
    public ResponseEntity<BebidaBaseResponse> crear(@RequestBody @Valid BebidaBaseRequest request) {
        BebidaBaseResponse response = bebidaBaseService.crear(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<BebidaBaseResponse>> obtenerTodos() {
        List<BebidaBaseResponse> lista = bebidaBaseService.obtenerTodos();
        return ResponseEntity.ok(lista);
    }
}

