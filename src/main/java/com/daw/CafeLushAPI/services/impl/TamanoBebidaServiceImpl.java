package com.daw.CafeLushAPI.services.impl;

import com.daw.CafeLushAPI.dtos.TamanoBebidaDTO;
import com.daw.CafeLushAPI.models.entities.TamanoBebida;
import com.daw.CafeLushAPI.repositories.TamanoBebidaRepository;
import com.daw.CafeLushAPI.services.TamanoBebidaService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TamanoBebidaServiceImpl implements TamanoBebidaService {

    private final TamanoBebidaRepository tamanoBebidaRepository;

    private TamanoBebidaDTO entityToDTO(TamanoBebida entity) {
        return TamanoBebidaDTO.builder()
                .id(entity.getId())
                .nombre(entity.getNombre())
                .factorPrecio(entity.getFactorPrecio())
                .build();
    }

    private TamanoBebida dtoToEntity(TamanoBebidaDTO dto) {
        return TamanoBebida.builder()
                .nombre(dto.getNombre())
                .factorPrecio(dto.getFactorPrecio())
                .build();
    }

    @Override
    public TamanoBebidaDTO createTamanoBebida(TamanoBebidaDTO dto) {
        if (tamanoBebidaRepository.existsByNombre(dto.getNombre())) {
            throw new IllegalArgumentException("El nombre ya está registrado");
        }
        TamanoBebida entity = dtoToEntity(dto);
        TamanoBebida saved = tamanoBebidaRepository.save(entity);
        return entityToDTO(saved);
    }

    @Override
    public List<TamanoBebidaDTO> getAllTamanoBebidas() {
        return tamanoBebidaRepository.findAll()
                .stream()
                .map(this::entityToDTO)
                .toList();
    }

    @Override
    public TamanoBebidaDTO getTamanoBebidaById(Integer id) {
        TamanoBebida entity = tamanoBebidaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("TamanoBebida no encontrada con id: " + id));
        return entityToDTO(entity);
    }
}
