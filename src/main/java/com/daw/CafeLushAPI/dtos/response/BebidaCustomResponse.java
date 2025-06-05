package com.daw.CafeLushAPI.dtos.response;

import com.daw.CafeLushAPI.models.entities.BebidaCustom;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class BebidaCustomResponse {
    private Integer id;
    private String bebidaBaseNombre;
    private String edulcorante;
    private String tipoLeche;
    private String especia;
    private String cremaEspuma;
    private BigDecimal precio;

    public static BebidaCustomResponse fromEntity(BebidaCustom bebidaCustom) {
        return BebidaCustomResponse.builder()
                .id(bebidaCustom.getId())
                .bebidaBaseNombre(bebidaCustom.getBebidaBase().getNombre())
                .edulcorante(bebidaCustom.getEdulcorante() != null ? bebidaCustom.getEdulcorante().getTipo() : null)
                .tipoLeche(bebidaCustom.getTipoLeche() != null ? bebidaCustom.getTipoLeche().getTipo() : null)
                .especia(bebidaCustom.getEspecia() != null ? bebidaCustom.getEspecia().getTipo() : null)
                .cremaEspuma(bebidaCustom.getCremaEspuma() != null ? bebidaCustom.getCremaEspuma().getTipo() : null)
                .precio(bebidaCustom.getPrecio())
                .build();
    }
}
