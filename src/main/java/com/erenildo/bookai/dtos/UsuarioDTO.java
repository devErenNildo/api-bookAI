package com.erenildo.bookai.dtos;

import com.erenildo.bookai.entity.UserRole;
import lombok.Data;

import java.util.Set;

@Data
public class UsuarioDTO {
    private Long id;
    private String nome;
    private String email;
    private UserRole roles;
}
