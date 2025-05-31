package com.daw.CafeLushAPI.services.impl;

import com.daw.CafeLushAPI.dtos.request.SubcategoriaRequest;
import com.daw.CafeLushAPI.dtos.response.SubcategoriaResponse;
import com.daw.CafeLushAPI.models.entities.Categoria;
import com.daw.CafeLushAPI.models.entities.Subcategoria;
import com.daw.CafeLushAPI.repositories.CategoriaRepository;
import com.daw.CafeLushAPI.repositories.SubcategoriaRepository;
import com.daw.CafeLushAPI.services.SubcategoriaService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SubcategoriaServiceImpl implements SubcategoriaService {

    private final SubcategoriaRepository subcategoriaRepository;
    private final CategoriaRepository categoriaRepository;

    @Override
    public SubcategoriaResponse crear(SubcategoriaRequest request) {
        Categoria categoria = categoriaRepository.findById(request.getCategoriaId())
                .orElseThrow(() -> new EntityNotFoundException("Categoría no encontrada"));

        Subcategoria subcategoria = Subcategoria.builder()
                .nombre(request.getNombre())
                .categoria(categoria)
                .build();

        Subcategoria guardada = subcategoriaRepository.save(subcategoria);
        return toResponse(guardada);
    }

    @Override
    public SubcategoriaResponse actualizar(Integer id, SubcategoriaRequest request) {
        Subcategoria existente = subcategoriaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Subcategoría no encontrada"));

        if (request.getNombre() != null) {
            existente.setNombre(request.getNombre());
        }

        if (request.getCategoriaId() != null) {
            Categoria categoria = categoriaRepository.findById(request.getCategoriaId())
                    .orElseThrow(() -> new EntityNotFoundException("Categoría no encontrada"));
            existente.setCategoria(categoria);
        }

        Subcategoria actualizada = subcategoriaRepository.save(existente);
        return toResponse(actualizada);
    }

    @Override
    public void eliminar(Integer id) {
        subcategoriaRepository.deleteById(id);
    }

    @Override
    public List<SubcategoriaResponse> obtenerTodas() {
        return subcategoriaRepository.findAll()
                .stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public SubcategoriaResponse obtenerPorId(Integer id) {
        Subcategoria subcategoria = subcategoriaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Subcategoría no encontrada"));
        return toResponse(subcategoria);
    }

    private SubcategoriaResponse toResponse(Subcategoria s) {
        return SubcategoriaResponse.builder()
                .id(s.getId())
                .nombre(s.getNombre())
                .categoriaId(s.getCategoria().getId())
                .categoriaNombre(s.getCategoria().getNombre())
                .build();
    }
}
