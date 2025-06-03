package com.erenildo.bookai.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/user")
public class UsuarioController {

//    public ResponseEntity<UsuarioDTO> criarUsuario(UsuarioRequestDTO usuarioRequestDTODTO) {
//
//    }

    @GetMapping()
    public ResponseEntity<String> teste() {
        return ResponseEntity.ok("teste");
    }

    @GetMapping("/adm")
    public ResponseEntity<String> testeAdm() {
        return ResponseEntity.ok("teste");
    }


}
