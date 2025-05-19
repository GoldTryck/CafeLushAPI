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
@Table(name = "CremaEspuma")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CremaEspuma {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "crme_id")
    private Integer id;

    @NotBlank(message = "El tipo es requerido")
    @Size(max = 50, message = "El tipo debe tener máximo 50 caracteres")
    @Column(name = "crme_tipo", nullable = false)
    private String tipo;

    @OneToMany(mappedBy = "cremaEspuma", cascade = CascadeType.ALL)
    private List<BebidaCustom> bebidasCustom;
} 