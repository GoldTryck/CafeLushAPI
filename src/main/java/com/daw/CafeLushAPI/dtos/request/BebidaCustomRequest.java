package com.daw.CafeLushAPI.dtos.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class BebidaCustomRequest {
    @NotNull
    private Integer bebidaBaseId;

    @NotNull
    private Integer tamanoBebidaId;

    private Integer edulcoranteId;
    private Integer tipoLecheId;
    private Integer especiaId;
    private Integer cremaEspumaId;

}
