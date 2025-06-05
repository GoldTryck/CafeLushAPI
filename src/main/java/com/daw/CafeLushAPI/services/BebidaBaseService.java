package com.daw.CafeLushAPI.services;

import com.daw.CafeLushAPI.dtos.request.BebidaBaseRequest;
import com.daw.CafeLushAPI.dtos.response.BebidaBaseResponse;

import java.util.List;

public interface BebidaBaseService {
    BebidaBaseResponse crear(BebidaBaseRequest request);
    List<BebidaBaseResponse> obtenerTodos();
}
