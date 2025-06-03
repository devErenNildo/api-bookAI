package com.erenildo.bookai.controller;

import com.erenildo.bookai.dtos.CadastroSecessoDTO;
import com.erenildo.bookai.dtos.LoginRequestDTO;
import com.erenildo.bookai.dtos.LoginResponseDTO;
import com.erenildo.bookai.dtos.UserRequestDTO;
import com.erenildo.bookai.security.TokenService;
import com.erenildo.bookai.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;
    private final TokenService tokenService;

    @PostMapping("/create")
    public ResponseEntity<CadastroSecessoDTO> registerUser(@RequestBody @Valid UserRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.cadastrarUsuario(dto));
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login (@RequestBody @Valid LoginRequestDTO dto) {
        return ResponseEntity.ok().body(tokenService.generateToken(dto));
    }
}
