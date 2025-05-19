package com.daw.CafeLushAPI.models.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;

@Entity
@Table(name = "AlimentoOrden")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AlimentoOrden {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "alor_id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "alor_alim_id", nullable = false)
    private Alimento alimento;

    @ManyToOne
    @JoinColumn(name = "alor_ord_id", nullable = false)
    private Orden orden;

    @NotNull(message = "La cantidad es requerida")
    @Min(value = 1, message = "La cantidad debe ser mayor a 0")
    @Column(name = "alor_cantidad", nullable = false)
    private Integer cantidad;

    @NotNull(message = "El subtotal es requerido")
    @Column(name = "alor_subtotal", nullable = false, precision = 8, scale = 2)
    private BigDecimal subtotal;
} 