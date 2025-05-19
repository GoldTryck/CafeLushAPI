package com.daw.CafeLushAPI.models.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "TamanoBebida")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TamanoBebida {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tam_id")
    private Integer id;

    @NotBlank(message = "El nombre es requerido")
    @Size(max = 45, message = "El nombre debe tener máximo 45 caracteres")
    @Column(name = "tam_nombre", nullable = false, unique = true)
    private String nombre;

    @NotNull(message = "El factor de precio es requerido")
    @Column(name = "tam_fact_precio", nullable = false, precision = 10, scale = 2)
    private BigDecimal factorPrecio;

    @OneToMany(mappedBy = "tamanoBebida", cascade = CascadeType.ALL)
    private List<BebidaCustom> bebidasCustom;
} 