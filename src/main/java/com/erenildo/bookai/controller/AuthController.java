package com.erenildo.bookai.controller;

import com.erenildo.bookai.dtos.CadastroSecessoDTO;
import com.erenildo.bookai.dtos.JwtResponseDTO;
import com.erenildo.bookai.dtos.LoginRequestDTO;
import com.erenildo.bookai.dtos.UsuarioRequestDTO;
import com.erenildo.bookai.entity.Usuario;
import com.erenildo.bookai.security.TokenService;
import com.erenildo.bookai.service.UsuarioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UsuarioService usuarioService;
    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity<JwtResponseDTO> login (@RequestBody @Valid LoginRequestDTO dto) {
        UsernamePasswordAuthenticationToken usename =
                new UsernamePasswordAuthenticationToken(dto.getEmail(), dto.getSenha());

        Authentication auth =
                this.authenticationManager.authenticate(usename);

        String token = tokenService.generateToken((Usuario) auth.getPrincipal());
        return ResponseEntity.ok(new JwtResponseDTO(token));
    }

    @PostMapping("/create")
    public ResponseEntity<CadastroSecessoDTO> cadastrar(@RequestBody @Valid UsuarioRequestDTO usuario) {
        return usuarioService.cadastro(usuario);
    }
}
