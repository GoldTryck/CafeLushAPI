package com.daw.CafeLushAPI.services.impl;


import com.daw.CafeLushAPI.dtos.RegistroClienteDTO;
import com.daw.CafeLushAPI.dtos.request.AuthRequest;
import com.daw.CafeLushAPI.dtos.response.AuthResponse;
import com.daw.CafeLushAPI.dtos.response.UsuarioResponse;
import com.daw.CafeLushAPI.exceptions.AuthenticationException;
import com.daw.CafeLushAPI.models.entities.Cliente;
import com.daw.CafeLushAPI.models.entities.Usuario;
import com.daw.CafeLushAPI.repositories.UsuarioRepository;
import com.daw.CafeLushAPI.repositories.ClienteRepository;
import com.daw.CafeLushAPI.services.AuthService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final ClienteRepository clienteRepository;
    private final ModelMapper modelMapper;

    @Override
    public AuthResponse login(AuthRequest request) {
        Usuario usuario = usuarioRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new AuthenticationException("Usuario no encontrado"));

        if (!passwordEncoder.matches(request.getPassword(), usuario.getPassword())) {
            throw new AuthenticationException("Contraseña incorrecta");
        }

        return new AuthResponse(usuario.getId(), usuario.getNombre(), usuario.getPrimerApellido());
    }

    @Override
    public UsuarioResponse registrarCliente(RegistroClienteDTO request) {
        if (usuarioRepository.existsByEmail(request.getEmail())) {
            throw new AuthenticationException("El email ya está registrado");
        }

        Usuario usuario = Usuario.builder()
                .nombre(request.getNombre())
                .primerApellido(request.getPrimerApellido())
                .segundoApellido(request.getSegundoApellido())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .fechaRegistro(LocalDateTime.now())
                .build();

        Usuario usuarioGuardado = usuarioRepository.save(usuario);

        Cliente cliente = Cliente.builder()
                .usuario(usuarioGuardado)
                .telefono(request.getTelefono())
                .build();

        Cliente clienteGuardado = clienteRepository.save(cliente);

        return modelMapper.map(usuarioGuardado, UsuarioResponse.class);
    }

}