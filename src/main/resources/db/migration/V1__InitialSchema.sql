-- Tabela de roles
CREATE TABLE tb_roles (
    role_id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL
);

-- Tabela de usuários
CREATE TABLE tb_users (
    user_id UUID PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    senha VARCHAR(255) NOT NULL,
    foto VARCHAR(255)
    );

-- Tabela de associação N:N entre usuários e roles
CREATE TABLE tb_users_roles (
    user_id UUID NOT NULL,
    role_id INTEGER NOT NULL,
    PRIMARY KEY (user_id, role_id),
    FOREIGN KEY (user_id) REFERENCES tb_users(user_id) ON DELETE CASCADE,
    FOREIGN KEY (role_id) REFERENCES tb_roles(role_id) ON DELETE CASCADE
);

-- Tabela de livros
CREATE TABLE book (
    id SERIAL PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    author_name VARCHAR(255),
    description TEXT,
    pdf_url VARCHAR(255) NOT NULL,
    like_count INTEGER DEFAULT 0,
    user_id UUID NOT NULL,
    FOREIGN KEY (user_id) REFERENCES tb_users(user_id) ON DELETE CASCADE
);

-- Tabela de curtidas em livros
CREATE TABLE book_like (
    id SERIAL PRIMARY KEY,
    user_id UUID NOT NULL,
    id_book INTEGER NOT NULL,
    FOREIGN KEY (user_id) REFERENCES tb_users(user_id) ON DELETE CASCADE,
    FOREIGN KEY (id_book) REFERENCES book(id) ON DELETE CASCADE
);

-- Tabela de comentários
CREATE TABLE comentario (
    id SERIAL PRIMARY KEY,
    content TEXT NOT NULL,
    upvote_count INTEGER DEFAULT 0,
    id_book INTEGER NOT NULL,
    user_id UUID NOT NULL,
    id_parent_comment INTEGER,
    FOREIGN KEY (id_book) REFERENCES book(id) ON DELETE CASCADE,
    FOREIGN KEY (user_id) REFERENCES tb_users(user_id) ON DELETE CASCADE,
    FOREIGN KEY (id_parent_comment) REFERENCES comentario(id) ON DELETE CASCADE
);

-- Tabela de upvotes em comentários
CREATE TABLE comentario_upvote (
    id SERIAL PRIMARY KEY,
    user_id UUID NOT NULL,
    id_comentario INTEGER NOT NULL,
    FOREIGN KEY (user_id) REFERENCES tb_users(user_id) ON DELETE CASCADE,
    FOREIGN KEY (id_comentario) REFERENCES comentario(id) ON DELETE CASCADE
);
