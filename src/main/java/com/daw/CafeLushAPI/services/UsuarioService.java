package com.daw.CafeLushAPI.services;

import com.daw.CafeLushAPI.dtos.request.UsuarioRequest;
import com.daw.CafeLushAPI.dtos.response.UsuarioResponse;

import java.util.List;

public interface UsuarioService {

    UsuarioResponse crearUsuario(UsuarioRequest request);

    List<UsuarioResponse> obtenerTodos();

    UsuarioResponse obtenerPorId(Integer id);

    UsuarioResponse actualizarUsuario(Integer id, UsuarioRequest request);

    void eliminarUsuario(Integer id);
}
