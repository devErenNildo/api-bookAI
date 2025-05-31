package com.erenildo.bookai.dtos;

import lombok.Data;

import java.util.Set;

@Data
public class UsuarioDTO {
    private Long id;
    private String nome;
    private String email;
    private Set<String> roles;
}
