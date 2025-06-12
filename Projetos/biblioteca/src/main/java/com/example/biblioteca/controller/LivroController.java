package com.example.biblioteca.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.biblioteca.model.LivroModel;
import com.example.biblioteca.service.LivroService;

@RestController
@RequestMapping("/api/livro")
@CrossOrigin ("*")


public class LivroController {

@Autowired
private LivroService service;

@GetMapping
public List<LivroModel> listar() {
return service.listarTodos();
}

@PostMapping
public ResponseEntity<?> criar(@RequestBody @Validated LivroModel livro) {
return service.salvar(livro);
}

@PutMapping("/{id}")
public ResponseEntity<?> editar(@PathVariable Long id, @RequestBody @Validated LivroModel livroAtualizado) {
return service.atualizar(id, livroAtualizado);
}

@PatchMapping("/{id}/status")
public ResponseEntity<?> alterarStatus(@PathVariable Long id, @RequestBody Map<String, String> req) {
String novoStatus = req.get("status");
return service.alterarStatus(id, novoStatus);
}

@DeleteMapping("/{id}")
public ResponseEntity<Void> excluir(@PathVariable Long id) {
return service.excluir(id);
}
}