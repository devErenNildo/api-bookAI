package com.erenildo.bookai.service;

import com.erenildo.bookai.dtos.LivroResponseDTO;
import com.erenildo.bookai.repository.LivroRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LivroService {

    private final LivroRepository livroRepository;

    public List<LivroResponseDTO> listarTodos() {
        return livroRepository.findAll().stream()
                .map(livro -> new LivroResponseDTO(
                        livro.getId(),
                        livro.getNome(),
                        livro.getAutor(),
                        livro.getImageUrl()))
                .collect(Collectors.toList());
    }
}
