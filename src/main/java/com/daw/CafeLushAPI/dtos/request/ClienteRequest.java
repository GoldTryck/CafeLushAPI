package com.daw.CafeLushAPI.dtos.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClienteRequest {
    @NotBlank
    private String nombre;

    @NotBlank
    private String primerApellido;

    private String segundoApellido;

    @NotBlank
    @Email
    private String email;

    private String password;

    @NotBlank
    private String telefono;
}
