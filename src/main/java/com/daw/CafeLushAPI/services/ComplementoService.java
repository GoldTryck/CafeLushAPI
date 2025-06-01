package com.daw.CafeLushAPI.services;

import com.daw.CafeLushAPI.dtos.ComplementoDTO;
import com.daw.CafeLushAPI.models.Complemento;

import java.util.List;

public interface ComplementoService <T extends Complemento> {
    ComplementoDTO crear(ComplementoDTO dto);
    List<ComplementoDTO> obtenerTodos();
}
