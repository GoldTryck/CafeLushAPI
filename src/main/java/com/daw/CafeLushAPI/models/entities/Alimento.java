package com.daw.CafeLushAPI.models.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.DecimalMin;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "Alimento")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Alimento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "alim_id")
    private Integer id;

    @NotBlank(message = "El nombre es requerido")
    @Size(max = 45, message = "El nombre debe tener máximo 45 caracteres")
    @Column(name = "alim_nombre", nullable = false)
    private String nombre;

    @NotBlank(message = "La descripción es requerida")
    @Size(max = 245, message = "La descripción debe tener máximo 45 caracteres")
    @Column(name = "alim_descripcion", nullable = false)
    private String descripcion;

    @Size(max = 455, message = "La imagen debe tener máximo 455 caracteres")
    @Column(name = "alim_imagen")
    private String imagen;

    @NotBlank(message = "El tiempo de preparación es requerido")
    @Size(max = 45, message = "El tiempo de preparación debe tener máximo 45 caracteres")
    @Column(name = "alim_tiempo_prep", nullable = false)
    private String tiempoPreparacion;

    @NotNull(message = "El precio es requerido")
    @DecimalMin(value = "0.0", inclusive = false, message = "El precio debe ser mayor a 0")
    @Column(name = "alim_precio", nullable = false, precision = 8, scale = 2)
    private BigDecimal precio;

    @ManyToOne
    @JoinColumn(name = "alim_scat_id", nullable = false)
    private Subcategoria subcategoria;

    @OneToMany(mappedBy = "alimento", cascade = CascadeType.ALL)
    private List<AlimentoOrden> alimentoOrdenes;
} 