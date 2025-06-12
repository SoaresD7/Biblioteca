package com.example.biblioteca.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;
import com.example.biblioteca.model.BibliotecarioModel;
import com.example.biblioteca.service.BibliotecarioService;

@RestController
@RequestMapping("/api/bibliotecario")
@CrossOrigin ("*")
public class BibliotecarioController<Bibliotecario> {

@Autowired
private BibliotecarioService service;

@GetMapping
public List<BibliotecarioModel> listar() {
return service.listarTodos();
}

@PostMapping
public BibliotecarioModel criar(@RequestBody BibliotecarioModel bibliotecario) {
    return service.salvar(bibliotecario);
}
}
