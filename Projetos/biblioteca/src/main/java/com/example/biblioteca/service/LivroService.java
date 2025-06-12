package com.example.biblioteca.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.biblioteca.model.LivroModel;
import com.example.biblioteca.repository.BibliotecarioRepository;
import com.example.biblioteca.repository.LivroRepository;

@Service
public class LivroService {

@Autowired
private LivroRepository livroRepo;

@Autowired
private BibliotecarioRepository bibliotecarioRepo;

public List<LivroModel> listarTodos() {
return livroRepo.findAll();
}

public Optional<LivroModel> buscarPorId(Long id) {
return livroRepo.findById(id);
}

public ResponseEntity<?> salvar(LivroModel livro) {
if (!bibliotecarioRepo.existsById(livro.getBibliotecario().getId())) {
return ResponseEntity.badRequest().body("Bibliotecário não encontrado");
}
return ResponseEntity.ok(livroRepo.save(livro));
}

public ResponseEntity<?> atualizar(Long id, LivroModel livroAtualizado) {
return livroRepo.findById(id).map(livro -> {
livro.setTitulo(livroAtualizado.getTitulo());
livro.setAutor(livroAtualizado.getAutor());
livro.setGenero(livroAtualizado.getGenero());
livro.setStatus(livroAtualizado.getStatus());
return ResponseEntity.ok(livroRepo.save(livro));
}).orElse(ResponseEntity.notFound().build());
}

public ResponseEntity<?> alterarStatus(Long id, String novoStatus) {
return livroRepo.findById(id).map(livro -> {
livro.setStatus(novoStatus);
return ResponseEntity.ok(livroRepo.save(livro));
}).orElse(ResponseEntity.notFound().build());
}

public ResponseEntity<Void> excluir(Long id) {
if (livroRepo.existsById(id)) {
livroRepo.deleteById(id);
return ResponseEntity.noContent().build();
}
return ResponseEntity.notFound().build();
}
}