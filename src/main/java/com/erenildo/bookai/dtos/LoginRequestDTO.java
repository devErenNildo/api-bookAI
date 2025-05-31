package com.erenildo.bookai.dtos;

import jakarta.validation.constraints.NotBlank;

public class LoginRequestDTO {

    @NotBlank
    private String email;

    @NotBlank
    private String senha;
}
