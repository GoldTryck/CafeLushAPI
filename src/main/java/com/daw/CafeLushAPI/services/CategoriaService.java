package com.daw.CafeLushAPI.services;

import com.daw.CafeLushAPI.dtos.request.CategoriaRequest;
import com.daw.CafeLushAPI.dtos.response.CategoriaResponse;

import java.util.List;

public interface CategoriaService {
    CategoriaResponse crearCategoria(CategoriaRequest request);
    List<CategoriaResponse> obtenerTodas();
    CategoriaResponse obtenerPorId(Integer id);
    CategoriaResponse actualizarCategoria(Integer id, CategoriaRequest request);
    void eliminarCategoria(Integer id);
}
