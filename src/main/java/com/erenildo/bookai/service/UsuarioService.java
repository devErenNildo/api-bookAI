package com.erenildo.bookai.service;

import com.erenildo.bookai.dtos.CadastroSecessoDTO;
import com.erenildo.bookai.dtos.UsuarioRequestDTO;
import com.erenildo.bookai.entity.UserRole;
import com.erenildo.bookai.entity.Usuario;
import com.erenildo.bookai.repository.UsuarioRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final ObjectMapper objectMapper;
    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public ResponseEntity<CadastroSecessoDTO> cadastro(UsuarioRequestDTO dto) {
        if (usuarioRepository.existsByEmail(dto.getEmail())) {
            throw new IllegalArgumentException("Email já cadastrado!");
        }

        Usuario usuario = new Usuario();
        usuario = objectMapper.convertValue(dto, Usuario.class);
        usuario.setSenha(passwordEncoder.encode(dto.getSenha()));
        usuario.setRole(UserRole.USER);
        usuario.setIdUsuario(UUID.randomUUID().toString());

        usuarioRepository.save(usuario);

        CadastroSecessoDTO resposta = new CadastroSecessoDTO("Usuario cadastrado com sucesso!");

        return ResponseEntity.status(HttpStatus.CREATED).body(resposta);
    }
}
