package com.daw.CafeLushAPI.services;

import com.daw.CafeLushAPI.dtos.RegistroClienteDTO;
import com.daw.CafeLushAPI.dtos.request.AuthRequest;
import com.daw.CafeLushAPI.dtos.response.AuthResponse;
import com.daw.CafeLushAPI.dtos.response.ClienteResponse;
import com.daw.CafeLushAPI.dtos.response.UsuarioResponse;

public interface AuthService {
    AuthResponse login(AuthRequest request);

    UsuarioResponse registrarCliente(RegistroClienteDTO request);
}