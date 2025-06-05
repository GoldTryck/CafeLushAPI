package com.daw.CafeLushAPI.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SubcategoriaResponse {
    private Integer id;
    private String nombre;
    private Integer categoriaId;
    private String categoriaNombre;
}
