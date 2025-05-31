package com.daw.CafeLushAPI.services.impl;

import com.daw.CafeLushAPI.dtos.request.ClienteRequest;
import com.daw.CafeLushAPI.dtos.response.ClienteResponse;
import com.daw.CafeLushAPI.models.entities.*;
import com.daw.CafeLushAPI.repositories.ClienteRepository;
import com.daw.CafeLushAPI.repositories.UsuarioRepository;
import com.daw.CafeLushAPI.services.ClienteService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.time.LocalDateTime;


@Service
@RequiredArgsConstructor
public class ClienteServiceImpl implements ClienteService {

    private final ClienteRepository clienteRepository;
    private final UsuarioRepository usuarioRepository;
    private final ModelMapper modelMapper;

    @Override
    public ClienteResponse crearCliente(ClienteRequest request) {
        if (usuarioRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("El email ya está en uso");
        }

        Usuario usuario = Usuario.builder()
                .nombre(request.getNombre())
                .primerApellido(request.getPrimerApellido())
                .segundoApellido(request.getSegundoApellido())
                .email(request.getEmail())
                .password(request.getPassword())
                .fechaRegistro(LocalDateTime.now())
                .build();

        Usuario usuarioGuardado = usuarioRepository.save(usuario);

        Cliente cliente = Cliente.builder()
                .usuario(usuarioGuardado)
                .telefono(request.getTelefono())
                .build();

        Cliente clienteGuardado = clienteRepository.save(cliente);

        return modelMapper.map(clienteGuardado, ClienteResponse.class);
    }

    @Override
    public List<ClienteResponse> obtenerTodos() {
        List<Cliente> clientes = clienteRepository.findAll();

        return clientes.stream().map(cliente -> {
            Usuario usuario = cliente.getUsuario();

            List<Integer> direccionesIds = cliente.getDirecciones() != null
                    ? cliente.getDirecciones().stream()
                    .map(Direccion::getId)
                    .collect(Collectors.toList())
                    : new ArrayList<>();

            List<Integer> metodosPagoIds = cliente.getMetodosPago() != null
                    ? cliente.getMetodosPago().stream()
                    .map(MetodoPago::getId)
                    .collect(Collectors.toList())
                    : new ArrayList<>();

            List<Integer> ordenesIds = cliente.getOrdenes() != null
                    ? cliente.getOrdenes().stream()
                    .map(Orden::getId)
                    .collect(Collectors.toList())
                    : new ArrayList<>();

            return ClienteResponse.builder()
                    .id(cliente.getId())
                    .nombre(usuario.getNombre())
                    .primerApellido(usuario.getPrimerApellido())
                    .segundoApellido(usuario.getSegundoApellido())
                    .email(usuario.getEmail())
                    .telefono(cliente.getTelefono())
                    .fechaRegistro(usuario.getFechaRegistro())
                    .direccionesIds(direccionesIds)
                    .metodosPagoIds(metodosPagoIds)
                    .ordenesIds(ordenesIds)
                    .build();
        }).collect(Collectors.toList());
    }

    @Override
    public ClienteResponse obtenerPorId(Integer id) {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Cliente no encontrado"));
        return buildResponse(cliente);
    }

    @Override
    public ClienteResponse actualizarCliente(Integer id, ClienteRequest request) {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Cliente no encontrado"));

        Usuario usuario = cliente.getUsuario();

        usuario.setNombre(request.getNombre());
        usuario.setPrimerApellido(request.getPrimerApellido());
        usuario.setSegundoApellido(request.getSegundoApellido());
        usuario.setEmail(request.getEmail());
        usuario.setPassword(request.getPassword());

        cliente.setTelefono(request.getTelefono());

        usuarioRepository.save(usuario);
        clienteRepository.save(cliente);

        return buildResponse(cliente);
    }

    @Override
    public void eliminarCliente(Integer id) {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Cliente no encontrado"));

        usuarioRepository.delete(cliente.getUsuario());
    }

    private ClienteResponse buildResponse(Cliente cliente) {
        return ClienteResponse.builder()
                .id(cliente.getId())
                .nombre(cliente.getUsuario().getNombre())
                .primerApellido(cliente.getUsuario().getPrimerApellido())
                .segundoApellido(cliente.getUsuario().getSegundoApellido())
                .email(cliente.getUsuario().getEmail())
                .telefono(cliente.getTelefono())
                .fechaRegistro(cliente.getUsuario().getFechaRegistro())
                .direccionesIds(cliente.getDirecciones() != null
                        ? cliente.getDirecciones().stream()
                        .map(Direccion::getId)
                        .collect(Collectors.toList())
                        : new ArrayList<>())
                .metodosPagoIds(cliente.getMetodosPago() != null
                        ? cliente.getMetodosPago().stream()
                        .map(MetodoPago::getId)
                        .collect(Collectors.toList())
                        : new ArrayList<>())
                .ordenesIds(cliente.getOrdenes() != null
                        ? cliente.getOrdenes().stream()
                        .map(Orden::getId)
                        .collect(Collectors.toList())
                        : new ArrayList<>())
                .build();
    }
}
