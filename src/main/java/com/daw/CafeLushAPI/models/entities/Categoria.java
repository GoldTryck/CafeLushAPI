package com.daw.CafeLushAPI.models.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.util.List;
import com.daw.CafeLushAPI.models.entities.Product;



@Entity
@Table(name = "categorias")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Categoria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El nombre es requerido")
    @Size(min = 1, max = 50, message = "El nombre debe tener entre 1 y 50 caracteres")
    private String nombre;

    private String descripcion;

    @OneToMany(mappedBy = "categoria", cascade = CascadeType.ALL)
    private List<Product> productos;
}



