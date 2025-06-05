package com.daw.CafeLushAPI.dtos.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CategoriaRequest {

    @NotBlank(message = "El nombre es requerido")
    @Size(max = 45, message = "El nombre debe tener máximo 45 caracteres")
    private String nombre;
}
