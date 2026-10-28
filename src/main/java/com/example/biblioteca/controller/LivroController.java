package com.example.biblioteca.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.biblioteca.model.Livro;
import com.example.biblioteca.repository.LivroRepository;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class LivroController {

    @Autowired
    LivroRepository livroRepository;

    // Lista todos os livros (ou filtra pelo título com ?titulo=...)
    @GetMapping("/livros")
    public ResponseEntity<List<Livro>> listarLivros(
            @RequestParam(required = false) String titulo) {
        try {
            List<Livro> livros = livroRepository.findAll();

            if (titulo != null) {
                livros = livroRepository.findByTituloContaining(titulo);
            }

            if (livros.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(livros, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Busca um livro pelo id
    @GetMapping("/livros/{id}")
    public ResponseEntity<Livro> buscarLivroPorId(@PathVariable("id") long id) {
        Optional<Livro> livroData = livroRepository.findById(id);

        if (livroData.isPresent()) {
            return new ResponseEntity<>(livroData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Cadastra um novo livro
    @PostMapping("/livros")
    public ResponseEntity<Livro> criarLivro(@RequestBody Livro livro) {
        try {
            Livro novoLivro = livroRepository.save(
                    new Livro(livro.getTitulo(),
                              livro.getAutor(),
                              livro.getIsbn(),
                              livro.getAnoPublicacao()));
            return new ResponseEntity<>(novoLivro, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Atualiza um livro pelo id
    @PutMapping("/livros/{id}")
    public ResponseEntity<Livro> atualizarLivro(
            @PathVariable("id") long id,
            @RequestBody Livro livro) {
        Optional<Livro> livroData = livroRepository.findById(id);

        if (livroData.isPresent()) {
            Livro livroExistente = livroData.get();
            livroExistente.setTitulo(livro.getTitulo());
            livroExistente.setAutor(livro.getAutor());
            livroExistente.setIsbn(livro.getIsbn());
            livroExistente.setAnoPublicacao(livro.getAnoPublicacao());
            return new ResponseEntity<>(
                    livroRepository.save(livroExistente), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Remove um livro pelo id
    @DeleteMapping("/livros/{id}")
    public ResponseEntity<HttpStatus> deletarLivro(@PathVariable("id") long id) {
        try {
            livroRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Remove todos os livros
    @DeleteMapping("/livros")
    public ResponseEntity<HttpStatus> deletarTodosLivros() {
        try {
            livroRepository.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Busca os livros de um autor
    @GetMapping("/livros/autor/{autor}")
    public ResponseEntity<List<Livro>> buscarPorAutor(@PathVariable("autor") String autor) {
        try {
            List<Livro> livros = livroRepository.findByAutor(autor);

            if (livros.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(livros, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
