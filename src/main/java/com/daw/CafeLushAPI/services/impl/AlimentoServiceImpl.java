package com.daw.CafeLushAPI.services.impl;

import com.daw.CafeLushAPI.dtos.request.AlimentoRequest;
import com.daw.CafeLushAPI.dtos.response.AlimentoResponse;
import com.daw.CafeLushAPI.models.entities.Alimento;
import com.daw.CafeLushAPI.models.entities.Subcategoria;
import com.daw.CafeLushAPI.repositories.AlimentoRepository;
import com.daw.CafeLushAPI.repositories.SubcategoriaRepository;
import com.daw.CafeLushAPI.services.AlimentoService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AlimentoServiceImpl implements AlimentoService {

    private final AlimentoRepository alimentoRepository;
    private final SubcategoriaRepository subcategoriaRepository;

    @Override
    public AlimentoResponse crearAlimento(AlimentoRequest request) {
        Subcategoria subcategoria = subcategoriaRepository.findById(request.getSubcategoriaId())
                .orElseThrow(() -> new EntityNotFoundException("Subcategoría no encontrada"));

        Alimento alimento = Alimento.builder()
                .nombre(request.getNombre())
                .descripcion(request.getDescripcion())
                .imagen(request.getImagen())
                .tiempoPreparacion(request.getTiempoPreparacion())
                .precio(request.getPrecio())
                .subcategoria(subcategoria)
                .build();

        alimento = alimentoRepository.save(alimento);
        return mapToResponse(alimento);
    }

    @Override
    public List<AlimentoResponse> obtenerTodos() {
        return alimentoRepository.findAll().stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public AlimentoResponse obtenerPorId(Integer id) {
        return mapToResponse(alimentoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Alimento no encontrado")));
    }

    @Override
    public AlimentoResponse actualizarAlimento(Integer id, AlimentoRequest request) {
        Alimento alimento = alimentoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Alimento no encontrado"));

        Subcategoria subcategoria = subcategoriaRepository.findById(request.getSubcategoriaId())
                .orElseThrow(() -> new EntityNotFoundException("Subcategoría no encontrada"));

        alimento.setNombre(request.getNombre());
        alimento.setDescripcion(request.getDescripcion());
        alimento.setImagen(request.getImagen());
        alimento.setTiempoPreparacion(request.getTiempoPreparacion());
        alimento.setPrecio(request.getPrecio());
        alimento.setSubcategoria(subcategoria);

        return mapToResponse(alimentoRepository.save(alimento));
    }

    @Override
    public void eliminarAlimento(Integer id) {
        alimentoRepository.deleteById(id);
    }

    private AlimentoResponse mapToResponse(Alimento a) {
        return AlimentoResponse.builder()
                .id(a.getId())
                .nombre(a.getNombre())
                .descripcion(a.getDescripcion())
                .imagen(a.getImagen())
                .tiempoPreparacion(a.getTiempoPreparacion())
                .precio(a.getPrecio())
                .subcategoriaId(a.getSubcategoria().getId())
                .build();
    }
}
