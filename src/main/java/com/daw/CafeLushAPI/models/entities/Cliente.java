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
@Table(name = "Cliente")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Cliente {
    @Id
    @Column(name = "cli_usr_id")
    private Integer id;

    @OneToOne
    @MapsId
    @JoinColumn(name = "cli_usr_id")
    private Usuario usuario;

    @NotBlank(message = "El teléfono es requerido")
    @Size(max = 20, message = "El teléfono debe tener máximo 20 caracteres")
    @Column(name = "cli_telefono", nullable = false, unique = true)
    private String telefono;

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
    private List<Direccion> direcciones;

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
    private List<MetodoPago> metodosPago;

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
    private List<Orden> ordenes;
} 