package com.daw.CafeLushAPI.services.impl;

import com.daw.CafeLushAPI.dtos.request.UsuarioRequest;
import com.daw.CafeLushAPI.dtos.response.UsuarioResponse;
import com.daw.CafeLushAPI.models.entities.Usuario;
import com.daw.CafeLushAPI.repositories.UsuarioRepository;
import com.daw.CafeLushAPI.services.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final ModelMapper modelMapper;

    @Override
    public UsuarioResponse crearUsuario(UsuarioRequest request) {
        Usuario usuario = modelMapper.map(request, Usuario.class);
        usuario.setFechaRegistro(LocalDateTime.now());
        Usuario guardado = usuarioRepository.save(usuario);
        return modelMapper.map(guardado, UsuarioResponse.class);
    }

    @Override
    public List<UsuarioResponse> obtenerTodos() {
        return usuarioRepository.findAll().stream()
                .map(usuario -> modelMapper.map(usuario, UsuarioResponse.class))
                .collect(Collectors.toList());
    }

    @Override
    public UsuarioResponse obtenerPorId(Integer id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        return modelMapper.map(usuario, UsuarioResponse.class);
    }

    @Override
    public UsuarioResponse actualizarUsuario(Integer id, UsuarioRequest request) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        modelMapper.map(request, usuario);
        Usuario actualizado = usuarioRepository.save(usuario);
        return modelMapper.map(actualizado, UsuarioResponse.class);
    }

    @Override
    public void eliminarUsuario(Integer id) {
        usuarioRepository.deleteById(id);
    }
}
