package com.daw.CafeLushAPI.dtos.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MetodoPagoRequest {
    @NotNull
    private Integer clienteId;

    @NotBlank
    private String numero;

    @NotBlank
    private String mesExpiracion;

    @NotBlank
    private String anioExpiracion;

    @NotBlank
    private String cvv;
}