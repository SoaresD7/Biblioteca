package com.example.biblioteca.model;

import java.time.LocalDateTime;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
@Entity
@Table(name = "livro") 
@Getter
@Setter

public class LivroModel {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;

@ManyToOne
@JoinColumn(name = "id_bibliotecario", nullable = false)
private BibliotecarioModel bibliotecario;

@NotBlank
private String titulo;

@NotBlank
private String autor;

@NotBlank
private String genero;

@NotBlank
private String status = "Disponível";

private LocalDateTime dataCadastro = LocalDateTime.now();

}

