package com.erenildo.bookai.repository;

import com.erenildo.bookai.entity.Usuario;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, String> {
//    Optional<Usuario> findByEmail(String email);

    boolean existsByEmail(@NotBlank(message = "Email é obrigatório") @Email(message = "Email deve ser válido") String email);
    UserDetails findByEmail(String email);
}
