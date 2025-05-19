package com.daw.CafeLushAPI.models.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "BebidaCustom", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"bec_beb_id", "bec_tam_id", "bec_edl_id", "bec_lec_id", "bec_esp_id", "bec_crme_id"})
})
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BebidaCustom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bec_id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "bec_beb_id", nullable = false)
    private BebidaBase bebidaBase;

    @ManyToOne
    @JoinColumn(name = "bec_tam_id", nullable = false)
    private TamanoBebida tamanoBebida;

    @NotNull(message = "El precio es requerido")
    @Column(name = "bec_precio", nullable = false, precision = 8, scale = 2)
    private BigDecimal precio;

    @ManyToOne
    @JoinColumn(name = "bec_edl_id")
    private Edulcorante edulcorante;

    @ManyToOne
    @JoinColumn(name = "bec_lec_id")
    private TipoLeche tipoLeche;

    @ManyToOne
    @JoinColumn(name = "bec_esp_id")
    private Especia especia;

    @ManyToOne
    @JoinColumn(name = "bec_crme_id")
    private CremaEspuma cremaEspuma;

    @OneToMany(mappedBy = "bebidaCustom", cascade = CascadeType.ALL)
    private List<BebidaOrden> bebidaOrdenes;
} 