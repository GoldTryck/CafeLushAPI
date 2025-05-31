package com.daw.CafeLushAPI.services;

import com.daw.CafeLushAPI.dtos.TamanoBebidaDTO;

import java.util.List;

public interface TamanoBebidaService {
    TamanoBebidaDTO createTamanoBebida(TamanoBebidaDTO tamanoBebidaDTO);
    List<TamanoBebidaDTO> getAllTamanoBebidas();
    TamanoBebidaDTO getTamanoBebidaById(Integer id);
}
