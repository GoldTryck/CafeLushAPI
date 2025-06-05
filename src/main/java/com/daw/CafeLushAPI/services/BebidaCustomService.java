package com.daw.CafeLushAPI.services;

import com.daw.CafeLushAPI.dtos.request.BebidaCustomRequest;
import com.daw.CafeLushAPI.dtos.response.BebidaCustomResponse;

import java.util.List;

public interface BebidaCustomService {
    BebidaCustomResponse crear(BebidaCustomRequest request);
    List<BebidaCustomResponse> obtenerTodos();
}
