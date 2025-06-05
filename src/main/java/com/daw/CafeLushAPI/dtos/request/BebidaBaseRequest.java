package com.daw.CafeLushAPI.dtos.request;

import com.daw.CafeLushAPI.models.entities.BebidaBase;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class BebidaBaseRequest {
    @NotBlank(message = "El nombre es requerido")
    @Size(max = 45, message = "El nombre debe tener máximo 45 caracteres")
    private String nombre;

    @NotNull(message = "El tipo de bebida es requerido")
    private BebidaBase.TipoBebida tipo;

    @NotNull(message = "El precio es requerido")
    @Digits(integer = 6, fraction = 2, message = "Formato de precio inválido")
    private BigDecimal precio;
}
