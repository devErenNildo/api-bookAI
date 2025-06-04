CREATE TABLE livros (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    autor VARCHAR(255) NOT NULL,
    image_url TEXT
);

INSERT INTO livros (nome, autor, image_url) VALUES
    ('1984', 'George Orwell', 'https://m.media-amazon.com/images/I/71kxa1-0mfL.jpg'),
    ('O Senhor dos Anéis', 'J.R.R. Tolkien', 'https://m.media-amazon.com/images/I/81t2CVWEsUL.jpg'),
    ('Dom Quixote', 'Miguel de Cervantes', 'https://m.media-amazon.com/images/I/61RgnZP8gXL.jpg'),
    ('Harry Potter e a Pedra Filosofal', 'J.K. Rowling', 'https://m.media-amazon.com/images/I/81YOuOGFCJL.jpg'),
    ('O Pequeno Príncipe', 'Antoine de Saint-Exupéry', 'https://m.media-amazon.com/images/I/81Otwki3LfL.jpg'),
    ('A Revolução dos Bichos', 'George Orwell', 'https://m.media-amazon.com/images/I/91e5f3b8+VL.jpg'),
    ('Orgulho e Preconceito', 'Jane Austen', 'https://m.media-amazon.com/images/I/81wgcld4wxL.jpg'),
    ('O Hobbit', 'J.R.R. Tolkien', 'https://m.media-amazon.com/images/I/91b0C2YNSrL.jpg'),
    ('Cem Anos de Solidão', 'Gabriel García Márquez', 'https://m.media-amazon.com/images/I/81Z9IGeU1yL.jpg'),
    ('O Código Da Vinci', 'Dan Brown', 'https://m.media-amazon.com/images/I/81QXNHFAh+L.jpg');

