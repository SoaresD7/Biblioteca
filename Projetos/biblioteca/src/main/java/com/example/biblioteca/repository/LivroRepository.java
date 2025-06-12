package com.example.biblioteca.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.biblioteca.model.LivroModel;

public interface LivroRepository extends JpaRepository<LivroModel, Long> {
}
