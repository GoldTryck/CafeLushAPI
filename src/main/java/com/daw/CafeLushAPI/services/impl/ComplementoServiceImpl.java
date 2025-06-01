package com.daw.CafeLushAPI.services.impl;

import com.daw.CafeLushAPI.dtos.ComplementoDTO;
import com.daw.CafeLushAPI.models.Complemento;
import com.daw.CafeLushAPI.services.ComplementoService;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class ComplementoServiceImpl<T extends Complemento> implements ComplementoService<T> {

    private final JpaRepository<T, Integer> repository;
    private final Supplier<T> entitySupplier;

    public ComplementoServiceImpl(JpaRepository<T, Integer> repository, Supplier<T> entitySupplier) {
        this.repository = repository;
        this.entitySupplier = entitySupplier;
    }

    @Override
    public ComplementoDTO crear(ComplementoDTO dto) {
        T entity = entitySupplier.get();
        entity.setTipo(dto.getTipo());
        T saved = repository.save(entity);
        return new ComplementoDTO(saved.getId(), saved.getTipo());
    }

    @Override
    public List<ComplementoDTO> obtenerTodos() {
        return repository.findAll().stream()
                .map(e -> new ComplementoDTO(e.getId(), e.getTipo()))
                .collect(Collectors.toList());
    }
}
