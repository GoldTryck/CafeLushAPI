package com.daw.CafeLushAPI.dtos.request;

import lombok.*;
import jakarta.validation.constraints.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioRequest {

    @NotBlank(message = "El nombre es requerido")
    @Size(max = 45, message = "El nombre debe tener máximo 45 caracteres")
    private String nombre;

    @NotBlank(message = "El primer apellido es requerido")
    @Size(max = 45, message = "El primer apellido debe tener máximo 45 caracteres")
    private String primerApellido;

    @Size(max = 45, message = "El segundo apellido debe tener máximo 45 caracteres")
    private String segundoApellido;

    @NotBlank(message = "El email es requerido")
    @Email(message = "Debe ser un email válido")
    @Size(max = 45, message = "El email debe tener máximo 45 caracteres")
    private String email;

    @Size(max = 255, message = "La contraseña debe tener máximo 255 caracteres")
    private String password;
}
