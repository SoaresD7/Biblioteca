CREATE DATABASE biblioteca;

USE biblioteca;

CREATE TABLE bibliotecario (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(200) NOT NULL,
    email VARCHAR (200) NOT null
);

CREATE TABLE livro (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    bibliotecario_id BIGINT NOT NULL,
    titulo VARCHAR (200) NOT NULL,
    autor VARCHAR (200) NOT NULL,
    genero VARCHAR (200) NOT NULL,
    status VARCHAR (200) NOT NULL,
    data_cadasto DATE
);

ALTER TABLE `livro` ADD FOREIGN KEY (`bibliotecario_id`) REFERENCES `bibliotecario`(`id`) ON DELETE CASCADE;