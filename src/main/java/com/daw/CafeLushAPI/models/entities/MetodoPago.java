package com.daw.CafeLushAPI.models.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Pattern;
import java.util.List;

@Entity
@Table(name = "MetodoPago")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MetodoPago {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "mep_id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "mep_cli_usr_id", nullable = false)
    private Cliente cliente;

    @NotBlank(message = "El número de tarjeta es requerido")
    @Size(min = 16, max = 16, message = "El número de tarjeta debe tener 16 dígitos")
    @Pattern(regexp = "\\d{16}", message = "El número de tarjeta debe contener solo dígitos")
    @Column(name = "mep_numero", nullable = false, unique = true)
    private String numero;

    @NotBlank(message = "El mes de expiración es requerido")
    @Pattern(regexp = "\\d{2}", message = "El mes de expiración debe tener 2 dígitos")
    @Column(name = "mep_mes_exp", nullable = false, length = 2)
    private String mesExpiracion;

    @NotBlank(message = "El año de expiración es requerido")
    @Pattern(regexp = "\\d{2}", message = "El año de expiración debe tener 2 dígitos")
    @Column(name = "mep_anio_exp", nullable = false, length = 2)
    private String anioExpiracion;

    @NotBlank(message = "El CVV es requerido")
    @Pattern(regexp = "\\d{3}", message = "El CVV debe tener 3 dígitos")
    @Column(name = "mep_cvv", nullable = false, length = 3)
    private String cvv;

    @OneToMany(mappedBy = "metodoPago", cascade = CascadeType.ALL)
    private List<Orden> ordenes;
} 