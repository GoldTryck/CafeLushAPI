package com.daw.CafeLushAPI.dtos.request;

import jakarta.validation.constraints.*;
import lombok.Data;
import java.math.BigDecimal;

@Data
public class AlimentoRequest {
    @NotBlank
    private String nombre;

    @NotBlank
    private String descripcion;

    private String imagen;

    @NotBlank
    private String tiempoPreparacion;

    @NotNull
    @DecimalMin(value = "0.0", inclusive = false)
    private BigDecimal precio;

    @NotNull
    private Integer subcategoriaId;
}