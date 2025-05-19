package com.daw.CafeLushAPI.models.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Email;
import java.time.LocalDateTime;

@Entity
@Table(name = "Usuario")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "usr_id")
    private Integer id;

    @NotBlank(message = "El nombre es requerido")
    @Size(max = 45, message = "El nombre debe tener máximo 45 caracteres")
    @Column(name = "usr_nombre", nullable = false)
    private String nombre;

    @NotBlank(message = "El primer apellido es requerido")
    @Size(max = 45, message = "El primer apellido debe tener máximo 45 caracteres")
    @Column(name = "usr_primer_ap", nullable = false)
    private String primerApellido;

    @Size(max = 45, message = "El segundo apellido debe tener máximo 45 caracteres")
    @Column(name = "usr_segundo_ap")
    private String segundoApellido;

    @NotBlank(message = "El email es requerido")
    @Email(message = "El email debe ser válido")
    @Size(max = 45, message = "El email debe tener máximo 45 caracteres")
    @Column(name = "usr_email", nullable = false, unique = true)
    private String email;

    @Column(name = "usr_fecha_registro", nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime fechaRegistro;

    @Size(max = 255, message = "La contraseña debe tener máximo 255 caracteres")
    @Column(name = "usr_password")
    private String password;

    @OneToOne(mappedBy = "usuario", cascade = CascadeType.ALL)
    private Cliente cliente;

    @OneToOne(mappedBy = "usuario", cascade = CascadeType.ALL)
    private Empleado empleado;
} 