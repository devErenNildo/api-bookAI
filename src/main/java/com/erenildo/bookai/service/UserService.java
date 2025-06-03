package com.erenildo.bookai.service;

import com.erenildo.bookai.dtos.CadastroSecessoDTO;
import com.erenildo.bookai.dtos.UserRequestDTO;
import com.erenildo.bookai.entity.Role;
import com.erenildo.bookai.entity.User;
import com.erenildo.bookai.exceptions.EmailCadastradoException;
import com.erenildo.bookai.repository.RoleRepository;
import com.erenildo.bookai.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final ObjectMapper objectMapper;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public CadastroSecessoDTO cadastrarUsuario(UserRequestDTO dto) {
        if(userRepository.findByEmail(dto.getEmail()).isPresent()) {
            throw new EmailCadastradoException(dto.getEmail());
        }

        Role roleBasic = roleRepository.findByRoleId(Role.Values.BASIC.getRoleId());

        if (roleBasic == null) {
            throw new IllegalStateException("Role BASIC não foi encontrada no banco de dados.");
        }

        User user = objectMapper.convertValue(dto, User.class);
        user.setSenha(bCryptPasswordEncoder.encode(dto.getSenha()));
        user.setRoles(Set.of(roleBasic));

        userRepository.save(user);

        return new CadastroSecessoDTO("Cadastrado com sucesso!");


    }


}
