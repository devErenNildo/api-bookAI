package com.erenildo.bookai.dtos;

import lombok.Getter;

@Getter
public class JwtResponseDTO {
    private String token;

    public JwtResponseDTO(String token) {
        this.token = token;
    }
}
