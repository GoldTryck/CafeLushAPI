package com.daw.CafeLushAPI.services;

import com.daw.CafeLushAPI.dtos.request.DireccionRequest;
import com.daw.CafeLushAPI.dtos.response.DireccionResponse;

import java.util.List;

public interface DireccionService {
    DireccionResponse crearDireccion(DireccionRequest request);

    List<DireccionResponse> obtenerTodos();

    DireccionResponse obtenerPorId(Integer id);

    DireccionResponse actualizarDireccion(Integer id, DireccionRequest request);

    void eliminarDireccion(Integer id);
}
