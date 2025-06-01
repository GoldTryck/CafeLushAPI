package com.daw.CafeLushAPI.models.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Table(name = "BebidaOrden")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BebidaOrden {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "beo_id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "beo_bec_id", nullable = false)
    private BebidaCustom bebidaCustom;

    @ManyToOne
    @JoinColumn(name = "beo_ord_id", nullable = false)
    private Orden orden;

    @NotNull(message = "La cantidad es requerida")
    @Min(value = 1, message = "La cantidad debe ser mayor a 0")
    @Column(name = "beor_cantidad", nullable = false)
    private Integer cantidad;


    @NotNull(message = "El subtotal es requerido")
    @Column(name = "beor_subtotal", nullable = false, precision = 8, scale = 2)
    private BigDecimal subtotal;
} 