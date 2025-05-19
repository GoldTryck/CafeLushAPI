package com.daw.CafeLushAPI.models.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "Orden")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Orden {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ord_id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "ord_cli_usr_id", nullable = false)
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "ord_mep_id")
    private MetodoPago metodoPago;

    @ManyToOne
    @JoinColumn(name = "ord_dir_id")
    private Direccion direccion;

    @Column(name = "ord_fecha", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime fecha;

    @Enumerated(EnumType.STRING)
    @Column(name = "ord_estado")
    private EstadoOrden estado;

    @Column(name = "ord_total", precision = 8, scale = 2)
    private BigDecimal total;

    @Column(name = "ord_nota", columnDefinition = "TEXT")
    private String nota;

    @OneToMany(mappedBy = "orden", cascade = CascadeType.ALL)
    private List<AlimentoOrden> alimentoOrdenes;

    @OneToMany(mappedBy = "orden", cascade = CascadeType.ALL)
    private List<BebidaOrden> bebidaOrdenes;

    public enum EstadoOrden {
        pendiente, en_preparacion, completado, cancelado
    }
} 