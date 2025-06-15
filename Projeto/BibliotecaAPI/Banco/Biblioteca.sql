-- Criação do banco de dados
CREATE DATABASE IF NOT EXISTS Biblioteca;
USE Biblioteca;

CREATE TABLE bibliotecarios (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE
);

CREATE TABLE livros (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    id_bibliotecario BIGINT NOT NULL,
    titulo VARCHAR(45) NOT NULL,
    autor VARCHAR(45) NOT NULL,
    genero VARCHAR (45) NULL,
    status VARCHAR (45) DEFAULT 'Disponível',
    data_cadastro TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    

    CONSTRAINT fk_bibliotecario
        FOREIGN KEY (id_bibliotecario)
        REFERENCES bibliotecarios(id)
        ON DELETE CASCADE
);
