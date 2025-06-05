package com.daw.CafeLushAPI.dtos.response;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CategoriaResponse {

    private Integer id;
    private String nombre;
}
