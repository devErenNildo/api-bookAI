package com.erenildo.bookai.security;

import com.erenildo.bookai.dtos.LoginRequestDTO;
import com.erenildo.bookai.dtos.LoginResponseDTO;
import com.erenildo.bookai.entity.Role;
import com.erenildo.bookai.entity.User;
import com.erenildo.bookai.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TokenService {

    private final UserRepository userRepository;
    private final JwtEncoder jwtEncoder;
    private final PasswordEncoder passwordEncoder;

    public LoginResponseDTO generateToken(LoginRequestDTO dto) {
        Optional<User> user = userRepository.findByEmail(dto.getEmail());

        if (user.isEmpty() || !user.get().isLoginCorrect(dto, passwordEncoder)) {
            throw new BadCredentialsException("user or password is invalid!");
        }
        var now = Instant.now();
        var expiresIn = 300L;

        var scopes = user.get().getRoles()
                .stream()
                .map(Role::getName)
                .collect(Collectors.joining(" "));

        var claims = JwtClaimsSet.builder()
                .issuer("bookai")
                .subject(user.get().getEmail())
                .issuedAt(now)
                .expiresAt(now.plusSeconds(expiresIn))
                .claim("scope", scopes)
                .build();

        var jwtValue = jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();

        return new LoginResponseDTO(jwtValue, expiresIn);
    }
}
