package com.daw.CafeLushAPI.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MetodoPagoResponse {
    private Integer id;
    private Integer clienteId;
    private String numero;
    private String mesExpiracion;
    private String anioExpiracion;
    private String cvv;
}
