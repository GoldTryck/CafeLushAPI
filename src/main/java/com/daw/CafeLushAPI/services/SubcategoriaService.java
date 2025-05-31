package com.daw.CafeLushAPI.services;

import com.daw.CafeLushAPI.dtos.request.SubcategoriaRequest;
import com.daw.CafeLushAPI.dtos.response.SubcategoriaResponse;

import java.util.List;

public interface SubcategoriaService {
    SubcategoriaResponse crear(SubcategoriaRequest request);
    SubcategoriaResponse actualizar(Integer id, SubcategoriaRequest request);
    void eliminar(Integer id);
    List<SubcategoriaResponse> obtenerTodas();
    SubcategoriaResponse obtenerPorId(Integer id);
}
