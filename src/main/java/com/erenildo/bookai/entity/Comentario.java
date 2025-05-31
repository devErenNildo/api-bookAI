package com.erenildo.bookai.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "comentario")
public class Comentario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String content;

    private int upvoteCount;

    @ManyToOne @JoinColumn(name = "id_book", nullable = false)
    private Book book;

    @ManyToOne @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario author;

    @ManyToOne @JoinColumn(name = "id_parent_comment")
    private Comentario parentComment;

    @OneToMany(mappedBy = "parentComment", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comentario> replies = new ArrayList<>();
}
