package com.daw.CafeLushAPI.services.impl;

import com.daw.CafeLushAPI.dtos.request.MetodoPagoRequest;
import com.daw.CafeLushAPI.dtos.response.MetodoPagoResponse;
import com.daw.CafeLushAPI.models.entities.Cliente;
import com.daw.CafeLushAPI.models.entities.MetodoPago;
import com.daw.CafeLushAPI.repositories.ClienteRepository;
import com.daw.CafeLushAPI.repositories.MetodoPagoRepository;
import com.daw.CafeLushAPI.services.MetodoPagoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MetodoPagoServiceImpl implements MetodoPagoService {

    private final MetodoPagoRepository metodoPagoRepository;
    private final ClienteRepository clienteRepository;

    @Override
    public MetodoPagoResponse crearMetodoPago(MetodoPagoRequest request) {
        Cliente cliente = clienteRepository.findById(request.getClienteId())
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));

        MetodoPago metodoPago = MetodoPago.builder()
                .cliente(cliente)
                .numero(request.getNumero())
                .mesExpiracion(request.getMesExpiracion())
                .anioExpiracion(request.getAnioExpiracion())
                .cvv(request.getCvv())
                .build();

        metodoPagoRepository.save(metodoPago);
        return mapToResponse(metodoPago);
    }

    @Override
    public List<MetodoPagoResponse> obtenerTodos() {
        return metodoPagoRepository.findAll().stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public MetodoPagoResponse obtenerPorId(Integer id) {
        MetodoPago metodo = metodoPagoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Método de pago no encontrado"));
        return mapToResponse(metodo);
    }

    @Override
    public MetodoPagoResponse actualizarMetodoPago(Integer id, MetodoPagoRequest request) {
        MetodoPago metodo = metodoPagoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Método de pago no encontrado"));

        metodo.setNumero(request.getNumero());
        metodo.setMesExpiracion(request.getMesExpiracion());
        metodo.setAnioExpiracion(request.getAnioExpiracion());
        metodo.setCvv(request.getCvv());
        metodoPagoRepository.save(metodo);

        return mapToResponse(metodo);
    }

    @Override
    public void eliminarMetodoPago(Integer id) {
        MetodoPago metodo = metodoPagoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Método de pago no encontrado"));
        metodoPagoRepository.delete(metodo);
    }

    private MetodoPagoResponse mapToResponse(MetodoPago metodo) {
        return MetodoPagoResponse.builder()
                .id(metodo.getId())
                .clienteId(metodo.getCliente().getId())
                .numero(metodo.getNumero())
                .mesExpiracion(metodo.getMesExpiracion())
                .anioExpiracion(metodo.getAnioExpiracion())
                .cvv(metodo.getCvv())
                .build();
    }
}