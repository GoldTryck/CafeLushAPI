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
@Table(name = "TipoLeche")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TipoLeche {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "lec_id")
    private Integer id;

    @NotBlank(message = "El tipo es requerido")
    @Size(max = 45, message = "El tipo debe tener máximo 45 caracteres")
    @Column(name = "lec_tipo", nullable = false, unique = true)
    private String tipo;

    @OneToMany(mappedBy = "tipoLeche", cascade = CascadeType.ALL)
    private List<BebidaCustom> bebidasCustom;
} 