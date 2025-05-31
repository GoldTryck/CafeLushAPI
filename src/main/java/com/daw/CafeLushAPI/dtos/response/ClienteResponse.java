package com.daw.CafeLushAPI.dtos.response;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClienteResponse {
    private Integer id;
    private String nombre;
    private String primerApellido;
    private String segundoApellido;
    private String email;
    private LocalDateTime fechaRegistro;
    private String telefono;

    private List<Integer> direccionesIds;
    private List<Integer> metodosPagoIds;
    private List<Integer> ordenesIds;
}

