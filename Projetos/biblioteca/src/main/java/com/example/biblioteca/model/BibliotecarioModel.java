package com.example.biblioteca.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "bibliotecario") 
@Getter
@Setter
public class BibliotecarioModel {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;

@NotBlank
private String nome;

@NotBlank
private String email;

}

