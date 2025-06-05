package com.daw.CafeLushAPI.controllers;

import com.daw.CafeLushAPI.dtos.request.BebidaCustomRequest;
import com.daw.CafeLushAPI.dtos.response.BebidaCustomResponse;
import com.daw.CafeLushAPI.services.BebidaCustomService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bebidas-custom")
@RequiredArgsConstructor
public class BebidaCustomController {

    private final BebidaCustomService bebidaCustomService;

    @PostMapping
    public ResponseEntity<BebidaCustomResponse> crear(@RequestBody BebidaCustomRequest request) {
        BebidaCustomResponse response = bebidaCustomService.crear(request);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<BebidaCustomResponse>> obtenerTodos() {
        List<BebidaCustomResponse> bebidas = bebidaCustomService.obtenerTodos();
        return ResponseEntity.ok(bebidas);
    }
}
