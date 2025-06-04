package com.erenildo.bookai.controller;

import com.erenildo.bookai.dtos.LivroResponseDTO;
import com.erenildo.bookai.service.LivroService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/livros")
@RequiredArgsConstructor
public class LivroController {

    private final LivroService livroService;

    @GetMapping
    public List<LivroResponseDTO> listarLivros() {
        return livroService.listarTodos();
    }
}
