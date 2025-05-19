package com.daw.CafeLushAPI.models.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "BebidaBase")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BebidaBase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "beb_id")
    private Integer id;

    @NotBlank(message = "El nombre es requerido")
    @Size(max = 45, message = "El nombre debe tener máximo 45 caracteres")
    @Column(name = "beb_nombre", nullable = false)
    private String nombre;

    @Enumerated(EnumType.STRING)
    @Column(name = "beb_tipo", nullable = false)
    private TipoBebida tipo;

    @Column(name = "beb_precio", precision = 8, scale = 2)
    private BigDecimal precio;

    @OneToMany(mappedBy = "bebidaBase", cascade = CascadeType.ALL)
    private List<BebidaCustom> bebidasCustom;

    public enum TipoBebida {
        F, C
    }
} 