package com.daw.CafeLushAPI.dtos.response;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
public class OrdenResponse {
    private Integer id;
    private String clienteNombre;
    private String metodoPago;
    private String direccion;
    private LocalDateTime fecha;
    private String estado;
    private BigDecimal total;
    private String nota;

    private List<String> bebidas;
    private List<String> alimentos;
}
