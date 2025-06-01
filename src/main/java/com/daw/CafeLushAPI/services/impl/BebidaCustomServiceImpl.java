package com.daw.CafeLushAPI.services.impl;

import com.daw.CafeLushAPI.dtos.request.BebidaCustomRequest;
import com.daw.CafeLushAPI.dtos.response.BebidaCustomResponse;
import com.daw.CafeLushAPI.models.entities.*;
import com.daw.CafeLushAPI.repositories.*;
import com.daw.CafeLushAPI.services.BebidaCustomService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BebidaCustomServiceImpl implements BebidaCustomService {

    private final BebidaCustomRepository bebidaCustomRepository;
    private final BebidaBaseRepository bebidaBaseRepository;
    private final TamanoBebidaRepository tamanoBebidaRepository;
    private final EdulcoranteRepository edulcoranteRepository;
    private final TipoLecheRepository tipoLecheRepository;
    private final EspeciaRepository especiaRepository;
    private final CremaEspumaRepository cremaEspumaRepository;

    @Override
    public BebidaCustomResponse crear(BebidaCustomRequest request) {
        BebidaBase bebidaBase = bebidaBaseRepository.findById(request.getBebidaBaseId())
                .orElseThrow(() -> new EntityNotFoundException("Bebida base no encontrada"));

        TamanoBebida tamano = tamanoBebidaRepository.findById(request.getTamanoBebidaId())
                .orElseThrow(() -> new EntityNotFoundException("Tamaño de bebida no encontrado"));

        Edulcorante edulcorante = getOptionalById(edulcoranteRepository, request.getEdulcoranteId());
        TipoLeche tipoLeche = getOptionalById(tipoLecheRepository, request.getTipoLecheId());
        Especia especia = getOptionalById(especiaRepository, request.getEspeciaId());
        CremaEspuma cremaEspuma = getOptionalById(cremaEspumaRepository, request.getCremaEspumaId());

        Optional<BebidaCustom> bebidaExistente = bebidaCustomRepository
                .findByBebidaBaseAndTamanoBebidaAndEdulcoranteAndTipoLecheAndEspeciaAndCremaEspuma(
                        bebidaBase, tamano, edulcorante, tipoLeche, especia, cremaEspuma);

        if (bebidaExistente.isPresent()) {
            return BebidaCustomResponse.fromEntity(bebidaExistente.get());
        }

        // Si no existe, calcular precio y crear
        BigDecimal precioFinal = bebidaBase.getPrecio().multiply(tamano.getFactorPrecio());

        BebidaCustom bebidaNueva = BebidaCustom.builder()
                .bebidaBase(bebidaBase)
                .tamanoBebida(tamano)
                .edulcorante(edulcorante)
                .tipoLeche(tipoLeche)
                .especia(especia)
                .cremaEspuma(cremaEspuma)
                .precio(precioFinal)
                .build();

        bebidaNueva = bebidaCustomRepository.save(bebidaNueva);

        return BebidaCustomResponse.fromEntity(bebidaNueva);
    }


    @Override
    public List<BebidaCustomResponse> obtenerTodos() {
        return bebidaCustomRepository.findAll().stream()
                .map(BebidaCustomResponse::fromEntity)
                .collect(Collectors.toList());
    }

    private <T> T getOptionalById(JpaRepository<T, Integer> repository, Integer id) {
        return id == null ? null : repository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("Elemento con id " + id + " no encontrado"));
    }

}
