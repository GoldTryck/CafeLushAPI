package com.daw.CafeLushAPI.services.impl;

import com.daw.CafeLushAPI.dtos.request.CategoriaRequest;
import com.daw.CafeLushAPI.dtos.response.CategoriaResponse;
import com.daw.CafeLushAPI.models.entities.Categoria;
import com.daw.CafeLushAPI.repositories.CategoriaRepository;
import com.daw.CafeLushAPI.services.CategoriaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class CategoriaServiceImpl implements CategoriaService {

    private final CategoriaRepository categoriaRepository;

    @Override
    public CategoriaResponse crearCategoria(CategoriaRequest request) {
        Categoria categoria = Categoria.builder()
                .nombre(request.getNombre())
                .build();

        Categoria saved = categoriaRepository.save(categoria);

        return mapToResponse(saved);
    }

    @Override
    public List<CategoriaResponse> obtenerTodas() {
        List<Categoria> categorias = categoriaRepository.findAll();
        return categorias.stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public CategoriaResponse obtenerPorId(Integer id) {
        Categoria categoria = categoriaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Categoría no encontrada con id: " + id));
        return mapToResponse(categoria);
    }

    @Override
    public CategoriaResponse actualizarCategoria(Integer id, CategoriaRequest request) {
        Categoria categoria = categoriaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Categoría no encontrada con id: " + id));

        categoria.setNombre(request.getNombre());

        Categoria updated = categoriaRepository.save(categoria);
        return mapToResponse(updated);
    }

    @Override
    public void eliminarCategoria(Integer id) {
        Categoria categoria = categoriaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Categoría no encontrada con id: " + id));
        categoriaRepository.delete(categoria);
    }

    private CategoriaResponse mapToResponse(Categoria categoria) {
        return CategoriaResponse.builder()
                .id(categoria.getId())
                .nombre(categoria.getNombre())
                .build();
    }
}
