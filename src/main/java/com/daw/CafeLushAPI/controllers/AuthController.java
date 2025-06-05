package com.daw.CafeLushAPI.controllers;

import com.daw.CafeLushAPI.dtos.RegistroClienteDTO;
import com.daw.CafeLushAPI.dtos.response.UsuarioResponse;
import lombok.RequiredArgsConstructor;
import com.daw.CafeLushAPI.dtos.request.AuthRequest;
import com.daw.CafeLushAPI.dtos.response.AuthResponse;
import com.daw.CafeLushAPI.services.AuthService;
import jakarta.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@Valid @RequestBody AuthRequest request) {
        return ResponseEntity.ok(authService.login(request));
    }
    @PostMapping("/register")
    public ResponseEntity<UsuarioResponse> register(@Valid @RequestBody RegistroClienteDTO dto) {

        return ResponseEntity.ok(authService.registrarCliente(dto));
    }
}