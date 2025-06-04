package com.erenildo.bookai.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LivroResponseDTO {
    private Long id;
    private String nome;
    private String autor;
    private String imageUrl;
}
