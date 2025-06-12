package com.example.biblioteca.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.biblioteca.model.BibliotecarioModel;

public interface BibliotecarioRepository extends JpaRepository<BibliotecarioModel, Long> {

    BibliotecarioModel save(BibliotecarioRepository b);

}