package com.daw.CafeLushAPI.services;

import com.daw.CafeLushAPI.dtos.request.OrdenRequest;
import com.daw.CafeLushAPI.dtos.response.OrdenResponse;

import java.util.List;

public interface OrdenService {
    OrdenResponse crearOrden(OrdenRequest request);
    List<OrdenResponse> obtenerTodas();
    OrdenResponse obtenerPorId(Integer id);
    // En la interface
    List<OrdenResponse> obtenerPorCliente(Integer clienteId);

}
