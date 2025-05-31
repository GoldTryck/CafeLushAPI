package com.daw.CafeLushAPI.services.impl;

import com.daw.CafeLushAPI.dtos.request.DireccionRequest;
import com.daw.CafeLushAPI.dtos.response.DireccionResponse;
import com.daw.CafeLushAPI.repositories.ClienteRepository;
import com.daw.CafeLushAPI.repositories.DireccionRepository;
import com.daw.CafeLushAPI.services.DireccionService;
import com.daw.CafeLushAPI.models.entities.Cliente;
import com.daw.CafeLushAPI.models.entities.Direccion;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import jakarta.persistence.EntityNotFoundException;

import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class DireccionServiceImpl implements DireccionService {

    private final DireccionRepository direccionRepository;
    private final ClienteRepository clienteRepository;

    @Override
    public DireccionResponse crearDireccion(DireccionRequest request) {
        Cliente cliente = clienteRepository.findById(request.getClienteId())
                .orElseThrow(() -> new EntityNotFoundException("Cliente no encontrado con id: " + request.getClienteId()));

        Direccion direccion = Direccion.builder()
                .cliente(cliente)
                .esPrincipal(request.getEsPrincipal())
                .numero(request.getNumero())
                .interior(request.getInterior())
                .colonia(request.getColonia())
                .codigoPostal(request.getCodigoPostal())
                .build();

        Direccion direccionGuardada = direccionRepository.save(direccion);
        return mapToResponse(direccionGuardada);
    }

    @Override
    public List<DireccionResponse> obtenerTodos() {
        List<Direccion> direcciones = direccionRepository.findAll();

        return direcciones.stream().map(this::mapToResponse).collect(Collectors.toList());
    }

    @Override
    public DireccionResponse obtenerPorId(Integer id) {
        Direccion direccion = direccionRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Dirección no encontrada con id: " + id));
        return mapToResponse(direccion);
    }

    @Override
    public DireccionResponse actualizarDireccion(Integer id, DireccionRequest request) {
        Direccion direccion = direccionRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Dirección no encontrada con id: " + id));

        Cliente cliente = clienteRepository.findById(request.getClienteId())
                .orElseThrow(() -> new EntityNotFoundException("Cliente no encontrado con id: " + request.getClienteId()));

        direccion.setCliente(cliente);
        direccion.setEsPrincipal(request.getEsPrincipal());
        direccion.setNumero(request.getNumero());
        direccion.setInterior(request.getInterior());
        direccion.setColonia(request.getColonia());
        direccion.setCodigoPostal(request.getCodigoPostal());

        Direccion direccionActualizada = direccionRepository.save(direccion);
        return mapToResponse(direccionActualizada);
    }

    @Override
    public void eliminarDireccion(Integer id) {
        if (!direccionRepository.existsById(id)) {
            throw new EntityNotFoundException("Dirección no encontrada con id: " + id);
        }
        direccionRepository.deleteById(id);
    }

    private DireccionResponse mapToResponse(Direccion direccion) {

        return DireccionResponse.builder()
                .id(direccion.getId())
                .clienteId(direccion.getCliente().getId())
                .esPrincipal(direccion.getEsPrincipal())
                .numero(direccion.getNumero())
                .interior(direccion.getInterior())
                .colonia(direccion.getColonia())
                .codigoPostal(direccion.getCodigoPostal())
                .build();
    }
}
