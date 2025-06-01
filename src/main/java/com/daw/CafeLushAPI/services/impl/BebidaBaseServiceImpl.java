package com.daw.CafeLushAPI.services.impl;

import com.daw.CafeLushAPI.dtos.request.BebidaBaseRequest;
import com.daw.CafeLushAPI.dtos.response.BebidaBaseResponse;
import com.daw.CafeLushAPI.models.entities.BebidaBase;
import com.daw.CafeLushAPI.repositories.BebidaBaseRepository;
import com.daw.CafeLushAPI.services.BebidaBaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BebidaBaseServiceImpl implements BebidaBaseService {

    private final BebidaBaseRepository bebidaBaseRepository;

    @Override
    public BebidaBaseResponse crear(BebidaBaseRequest request) {
        if (bebidaBaseRepository.existsByNombre(request.getNombre())) {
            throw new IllegalArgumentException("Ya existe una bebida con ese nombre.");
        }

        BebidaBase bebida = BebidaBase.builder()
                .nombre(request.getNombre())
                .tipo(request.getTipo())
                .precio(request.getPrecio())
                .build();

        BebidaBase guardada = bebidaBaseRepository.save(bebida);

        return BebidaBaseResponse.builder()
                .id(guardada.getId())
                .nombre(guardada.getNombre())
                .tipo(guardada.getTipo())
                .precio(guardada.getPrecio())
                .build();
    }

    @Override
    public List<BebidaBaseResponse> obtenerTodos() {
        return bebidaBaseRepository.findAll()
                .stream()
                .map(b -> BebidaBaseResponse.builder()
                        .id(b.getId())
                        .nombre(b.getNombre())
                        .tipo(b.getTipo())
                        .precio(b.getPrecio())
                        .build())
                .collect(Collectors.toList());
    }
}
