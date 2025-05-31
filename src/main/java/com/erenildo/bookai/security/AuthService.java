package com.erenildo.bookai.security;

import com.erenildo.bookai.entity.Usuario;
import com.erenildo.bookai.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService implements UserDetailsService {

    private final UsuarioService usuarioService;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<Usuario> usuarioObtido = usuarioService.findByEmail(email);
        return usuarioObtido
                .orElseThrow(() -> new UsernameNotFoundException("usuário inválido"));
    }
}
