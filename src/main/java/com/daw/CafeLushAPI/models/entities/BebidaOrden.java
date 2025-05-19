package com.daw.CafeLushAPI.models.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
} 