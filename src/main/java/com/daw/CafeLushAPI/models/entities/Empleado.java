package com.daw.CafeLushAPI.models.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Empleado")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Empleado {
    
    @Id
    @Column(name = "empl_usr_id")
    private Integer id;
    
    @Column(name = "numero_empleado", unique = true, nullable = false)
    private Integer numeroEmpleado;
    
    @OneToOne
    @MapsId
    @JoinColumn(name = "empl_usr_id")
    private Usuario usuario;
}