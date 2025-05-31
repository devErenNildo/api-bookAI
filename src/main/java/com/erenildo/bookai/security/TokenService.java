package com.erenildo.bookai.security;

import com.erenildo.bookai.entity.Usuario;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TokenService {

    private static final String TOKEN_PREFIX = "Bearer";
    private static final String CARGOS_CLAIM = "cargos";

    @Value("${api.security.token.secret}")
    private String secret;

    public String generateToken(Usuario usuario) {
            Date now = new Date();
            Date exp = new Date(now.getTime() + Duration.ofHours(3).toMillis());

            List<String> cargos = usuario.getRoles().stream()
                    .map(cargo -> "ROLE_" + cargo.getAuthority())
                    .toList();
            return Jwts.builder()
                    .setIssuer("Book-AI")
                    .claim(Claims.ID, usuario.getIdUsuario())
                    .claim(CARGOS_CLAIM, cargos)
                    .setIssuedAt(now)
                    .setExpiration(exp)
                    .signWith(SignatureAlgorithm.HS256, secret)
                    .compact();
    }

    public UsernamePasswordAuthenticationToken isValid(String token) {
        if (token != null) {
            Claims body = Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token.replace(TOKEN_PREFIX, ""))
                    .getBody();
            String user = body.get(Claims.ID, String.class);
            if (user != null) {
                List<String> cargos = body.get(CARGOS_CLAIM, List.class);
                List<SimpleGrantedAuthority> authorities = cargos.stream()
                        .map(SimpleGrantedAuthority::new)
                        .toList();
                return new UsernamePasswordAuthenticationToken(user, null, authorities);
            }
        }
        return null;
    }

    public String getUsername(String token){
        return Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token.replace(TOKEN_PREFIX, ""))
                .getBody()
                .get(Claims.ID, String.class);
    }
}
