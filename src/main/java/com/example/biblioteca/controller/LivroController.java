package com.example.biblioteca.controller;

import com.example.biblioteca.model.Livro;
import com.example.biblioteca.repository.LivroRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/livros")
public class LivroController {

    private final LivroRepository livroRepository;

    public LivroController(LivroRepository livroRepository) {
        this.livroRepository = livroRepository;
    }

    // Cadastra um novo livro
    @PostMapping
    public ResponseEntity<Livro> criarLivro(@RequestBody Livro livro) {
        livro.setId(null); // garante que o banco gere o id
        Livro salvo = livroRepository.save(livro);
        return ResponseEntity.status(HttpStatus.CREATED).body(salvo);
    }

    // Lista todos os livros
    @GetMapping
    public List<Livro> listarLivros() {
        return livroRepository.findAll();
    }

    // Busca um livro pelo id
    @GetMapping("/{id}")
    public ResponseEntity<Livro> buscarLivroPorId(@PathVariable Long id) {
        return livroRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Atualiza um livro existente
    @PutMapping("/{id}")
    public ResponseEntity<Livro> atualizarLivro(@PathVariable Long id, @RequestBody Livro dados) {
        return livroRepository.findById(id)
                .map(livro -> {
                    livro.setTitulo(dados.getTitulo());
                    livro.setAutor(dados.getAutor());
                    livro.setIsbn(dados.getIsbn());
                    livro.setAnoPublicacao(dados.getAnoPublicacao());
                    return ResponseEntity.ok(livroRepository.save(livro));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    // Remove um livro
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarLivro(@PathVariable Long id) {
        if (!livroRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        livroRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
