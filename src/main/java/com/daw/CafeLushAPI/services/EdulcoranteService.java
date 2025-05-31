package com.daw.CafeLushAPI.services;

import com.daw.CafeLushAPI.dtos.EdulcoranteDTO;

import java.util.List;

public interface EdulcoranteService {
    EdulcoranteDTO crearEdulcorante(EdulcoranteDTO dto);
    List<EdulcoranteDTO> obtenerTodos();
}
