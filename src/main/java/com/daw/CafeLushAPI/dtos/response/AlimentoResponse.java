package com.daw.CafeLushAPI.dtos.response;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class AlimentoResponse {
    private Integer id;
    private String nombre;
    private String descripcion;
    private String imagen;
    private String tiempoPreparacion;
    private BigDecimal precio;
    private Integer subcategoriaId;
}
