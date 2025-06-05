package com.daw.CafeLushAPI.models.entities;

import com.daw.CafeLushAPI.models.Complemento;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "Edulcorante")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Edulcorante implements Complemento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "edl_id")
    private Integer id;

    @NotBlank(message = "El tipo es requerido")
    @Size(max = 45, message = "El tipo debe tener máximo 45 caracteres")
    @Column(name = "edl_tipo", nullable = false, unique = true)
    private String tipo;

    @OneToMany(mappedBy = "edulcorante", cascade = CascadeType.ALL)
    private List<BebidaCustom> bebidasCustom;
} 