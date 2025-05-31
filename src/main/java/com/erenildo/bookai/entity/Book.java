package com.erenildo.bookai.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "book")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    private String authorName;

    private String description;

    @Column(nullable = false)
    private String pdfUrl;

    private int likeCount;

    @ManyToOne
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario postedBy;

    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comentario> comments = new ArrayList<>();
}
