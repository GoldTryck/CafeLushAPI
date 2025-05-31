package com.daw.CafeLushAPI.services.impl;

import com.daw.CafeLushAPI.dtos.EdulcoranteDTO;
import com.daw.CafeLushAPI.models.entities.Edulcorante;
import com.daw.CafeLushAPI.repositories.EdulcoranteRepository;
import com.daw.CafeLushAPI.services.EdulcoranteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EdulcoranteServiceImpl implements EdulcoranteService {

    private final EdulcoranteRepository edulcoranteRepository;

    @Override
    public EdulcoranteDTO crearEdulcorante(EdulcoranteDTO dto) {
        if (edulcoranteRepository.existsByTipo(dto.getTipo())) {
            throw new IllegalArgumentException("Ya existe un edulcorante con ese tipo");
        }

        Edulcorante nuevo = Edulcorante.builder()
                .tipo(dto.getTipo())
                .build();

        Edulcorante guardado = edulcoranteRepository.save(nuevo);

        return EdulcoranteDTO.builder()
                .id(guardado.getId())
                .tipo(guardado.getTipo())
                .build();
    }

    @Override
    public List<EdulcoranteDTO> obtenerTodos() {
        return edulcoranteRepository.findAll().stream()
                .map(e -> EdulcoranteDTO.builder()
                        .id(e.getId())
                        .tipo(e.getTipo())
                        .build())
                .collect(Collectors.toList());
    }
}
