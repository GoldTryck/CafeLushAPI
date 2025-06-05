package com.daw.CafeLushAPI.services;

import com.daw.CafeLushAPI.dtos.request.ClienteRequest;
import com.daw.CafeLushAPI.dtos.response.ClienteResponse;

import java.util.List;

public interface ClienteService {

    ClienteResponse crearCliente(ClienteRequest request);

    List<ClienteResponse> obtenerTodos();

    ClienteResponse obtenerPorId(Integer id);

    ClienteResponse actualizarCliente(Integer id, ClienteRequest request);

    void eliminarCliente(Integer id);
}
