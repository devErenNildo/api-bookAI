package com.erenildo.bookai.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "comentario_upvote")
public class CommentUpvote {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_usuario", nullable = false)
    private User user;

    @ManyToOne @JoinColumn(name = "id_comentario", nullable = false)
    private Comentario comment;
}
