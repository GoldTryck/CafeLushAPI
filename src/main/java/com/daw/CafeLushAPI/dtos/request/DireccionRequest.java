package com.daw.CafeLushAPI.dtos.request;

import jakarta.validation.constraints.*;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DireccionRequest {
    @NotNull(message = "El id del cliente es requerido")
    private Integer clienteId;

    @NotNull(message = "El campo esPrincipal es requerido")
    private Boolean esPrincipal;

    @NotBlank(message = "El número es requerido")
    @Size(max = 45, message = "El número debe tener máximo 45 caracteres")
    private String numero;

    @Size(max = 45, message = "El número interior debe tener máximo 45 caracteres")
    private String interior;

    @NotBlank(message = "La colonia es requerida")
    @Size(max = 45, message = "La colonia debe tener máximo 45 caracteres")
    private String colonia;

    @Size(max = 45, message = "El código postal debe tener máximo 45 caracteres")
    private String codigoPostal;
}
