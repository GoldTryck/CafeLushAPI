package com.daw.CafeLushAPI.services;

import com.daw.CafeLushAPI.dtos.request.MetodoPagoRequest;
import com.daw.CafeLushAPI.dtos.response.MetodoPagoResponse;

import java.util.List;

public interface MetodoPagoService {
    MetodoPagoResponse crearMetodoPago(MetodoPagoRequest request);

    List<MetodoPagoResponse> obtenerTodos();

    MetodoPagoResponse obtenerPorId(Integer id);

    MetodoPagoResponse actualizarMetodoPago(Integer id, MetodoPagoRequest request);

    void eliminarMetodoPago(Integer id);
}