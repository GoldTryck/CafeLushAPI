package com.daw.CafeLushAPI.dtos.response;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DireccionResponse {
    private Integer id;

    private Integer clienteId;

    private Boolean esPrincipal;

    private String numero;

    private String interior;

    private String colonia;

    private String codigoPostal;
}
