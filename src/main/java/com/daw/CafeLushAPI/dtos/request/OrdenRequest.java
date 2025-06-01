package com.daw.CafeLushAPI.dtos.request;

import lombok.Data;

import java.util.List;

@Data
public class OrdenRequest {
    private Integer clienteId;
    private Integer metodoPagoId;
    private Integer direccionId;
    private String nota;
    private List<AlimentoOrdenRequest> alimentos;
    private List<BebidaOrdenRequest> bebidas;

    @Data
    public static class AlimentoOrdenRequest {
        private Integer alimentoId;
        private Integer cantidad;
    }

    @Data
    public static class BebidaOrdenRequest {
        private Integer bebidaCustomId;
        private Integer cantidad;
    }
}
