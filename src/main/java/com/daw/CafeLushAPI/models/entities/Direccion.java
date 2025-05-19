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
@Table(name = "Direccion", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"dir_cli_usr_id", "dir_numero", "dir_interior", "dir_colonia", "dir_codigo_postal"})
})
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Direccion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "dir_id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "dir_cli_usr_id", nullable = false)
    private Cliente cliente;

    @Column(name = "dir_es_principal", nullable = false)
    @Enumerated(EnumType.STRING)
    private Boolean esPrincipal;

    @NotBlank(message = "El número es requerido")
    @Size(max = 45, message = "El número debe tener máximo 45 caracteres")
    @Column(name = "dir_numero", nullable = false)
    private String numero;

    @Size(max = 45, message = "El número interior debe tener máximo 45 caracteres")
    @Column(name = "dir_interior")
    private String interior;

    @NotBlank(message = "La colonia es requerida")
    @Size(max = 45, message = "La colonia debe tener máximo 45 caracteres")
    @Column(name = "dir_colonia", nullable = false)
    private String colonia;

    @Size(max = 45, message = "El código postal debe tener máximo 45 caracteres")
    @Column(name = "dir_codigo_postal")
    private String codigoPostal;

    @OneToMany(mappedBy = "direccion", cascade = CascadeType.ALL)
    private List<Orden> ordenes;
} 