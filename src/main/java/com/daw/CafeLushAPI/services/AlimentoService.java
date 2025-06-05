package com.daw.CafeLushAPI.services;

import com.daw.CafeLushAPI.dtos.request.AlimentoRequest;
import com.daw.CafeLushAPI.dtos.response.AlimentoResponse;

import java.util.List;

public interface AlimentoService {
    AlimentoResponse crearAlimento(AlimentoRequest request);
    List<AlimentoResponse> obtenerTodos();
    AlimentoResponse obtenerPorId(Integer id);
    AlimentoResponse actualizarAlimento(Integer id, AlimentoRequest request);
    void eliminarAlimento(Integer id);
}