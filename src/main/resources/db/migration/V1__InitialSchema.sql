-- Tabela de usuários
CREATE TABLE usuarios (
    id_usuario VARCHAR(255) PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    senha VARCHAR(255) NOT NULL,
    foto VARCHAR(255)
);

-- Tabela de cargos (roles)
CREATE SEQUENCE seq_cargo START 1 INCREMENT 1;
CREATE TABLE cargo (
    id_cargo INTEGER PRIMARY KEY DEFAULT nextval('seq_cargo'),
    cargo VARCHAR(255) NOT NULL
);

-- Tabela de relacionamento N:N entre usuários e cargos
CREATE TABLE usuario_cargo (
    id_usuario VARCHAR(255) NOT NULL,
    id_cargo INTEGER NOT NULL,
    PRIMARY KEY (id_usuario, id_cargo),
    FOREIGN KEY (id_usuario) REFERENCES usuarios(id_usuario) ON DELETE CASCADE,
    FOREIGN KEY (id_cargo) REFERENCES cargo(id_cargo) ON DELETE CASCADE
);

-- Tabela de livros
CREATE TABLE book (
    id SERIAL PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    author_name VARCHAR(255),
    description TEXT,
    pdf_url VARCHAR(255) NOT NULL,
    like_count INTEGER DEFAULT 0,
    id_usuario VARCHAR(255) NOT NULL,
    FOREIGN KEY (id_usuario) REFERENCES usuarios(id_usuario) ON DELETE CASCADE
);

-- Tabela de curtidas em livros
CREATE TABLE book_like (
    id SERIAL PRIMARY KEY,
    id_usuario VARCHAR(255) NOT NULL,
    id_book INTEGER NOT NULL,
    FOREIGN KEY (id_usuario) REFERENCES usuarios(id_usuario) ON DELETE CASCADE,
    FOREIGN KEY (id_book) REFERENCES book(id) ON DELETE CASCADE
);

-- Tabela de comentários
CREATE TABLE comentario (
    id SERIAL PRIMARY KEY,
    content TEXT NOT NULL,
    upvote_count INTEGER DEFAULT 0,
    id_book INTEGER NOT NULL,
    id_usuario VARCHAR(255) NOT NULL,
    id_parent_comment INTEGER,
    FOREIGN KEY (id_book) REFERENCES book(id) ON DELETE CASCADE,
    FOREIGN KEY (id_usuario) REFERENCES usuarios(id_usuario) ON DELETE CASCADE,
    FOREIGN KEY (id_parent_comment) REFERENCES comentario(id) ON DELETE CASCADE
);

-- Tabela de upvotes em comentários
CREATE TABLE comentario_upvote (
    id SERIAL PRIMARY KEY,
    id_usuario VARCHAR(255) NOT NULL,
    id_comentario INTEGER NOT NULL,
    FOREIGN KEY (id_usuario) REFERENCES usuarios(id_usuario) ON DELETE CASCADE,
    FOREIGN KEY (id_comentario) REFERENCES comentario(id) ON DELETE CASCADE
);
