package com.example.biblioteca.service;

import java.util.List;
import java.util.Optional;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.biblioteca.model.BibliotecarioModel;
import com.example.biblioteca.repository.BibliotecarioRepository;

@Service
public class BibliotecarioService {

@Autowired
private BibliotecarioRepository repository;

public List<BibliotecarioModel> listarTodos() {
return repository.findAll();
}

public Optional<BibliotecarioModel> buscarPorId(Long id) {
return repository.findById(id);
}

public BibliotecarioModel salvar(BibliotecarioModel b) {
return repository.save(b);
}

public ResponseEntity<BibliotecarioModel> atualizar(Long id, BibliotecarioModel b2) {
return repository.findById(id).map(b -> {
b.setNome(b2.getNome());
b.setEmail(b2.getEmail());
return ResponseEntity.ok(repository.save(b));
}).orElse(ResponseEntity.notFound().build());
}

public ResponseEntity<Void> excluir(Long id) {
if (repository.existsById(id)) {
repository.deleteById(id);
return ResponseEntity.noContent().build();
}
return ResponseEntity.notFound().build();
}

@SuppressWarnings("hiding")
public <BibliotecarioModel> BibliotecarioModel salvar(BibliotecarioModel bibliotecario) {
    throw new UnsupportedOperationException("Unimplemented method salvar");
}

@SuppressWarnings("hiding")
public <BibliotecarioModel> BibliotecarioModel atualizar (BibliotecarioModel bibliotecario) {
    throw new UnsupportedOperationException("Unimplemented method atualizar");
}
}