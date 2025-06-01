package com.daw.CafeLushAPI.dtos.response;

import com.daw.CafeLushAPI.models.entities.BebidaBase;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class BebidaBaseResponse {
    private Integer id;
    private String nombre;
    private BebidaBase.TipoBebida tipo;
    private BigDecimal precio;
}
