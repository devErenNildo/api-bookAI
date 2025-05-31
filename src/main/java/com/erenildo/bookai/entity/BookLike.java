package com.erenildo.bookai.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "book_like")
public class BookLike {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario user;

    @ManyToOne
    @JoinColumn(name = "id_book", nullable = false)
    private Book book;
}
