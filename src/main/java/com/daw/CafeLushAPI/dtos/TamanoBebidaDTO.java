package com.daw.CafeLushAPI.dtos;

import jakarta.validation.constraints.*;
import lombok.*;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TamanoBebidaDTO {
    private Integer id;

    @NotBlank(message = "El nombre es requerido")
    @Size(max = 45, message = "El nombre debe tener máximo 45 caracteres")
    private String nombre;

    @NotNull(message = "El factor de precio es requerido")
    private BigDecimal factorPrecio;
}