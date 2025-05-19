package com.daw.CafeLushAPI.models.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "Subcategoria")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Subcategoria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "scat_id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "scat_cat_id", nullable = false)
    private Categoria categoria;

    @NotBlank(message = "El nombre es requerido")
    @Size(max = 45, message = "El nombre debe tener máximo 45 caracteres")
    @Column(name = "scat_nombre", nullable = false)
    private String nombre;

    @OneToMany(mappedBy = "subcategoria", cascade = CascadeType.ALL)
    private List<Alimento> alimentos;
} 