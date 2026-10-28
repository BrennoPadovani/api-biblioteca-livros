package com.example.biblioteca.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.biblioteca.model.Livro;

public interface LivroRepository extends JpaRepository<Livro, Long> {

    List<Livro> findByAutor(String autor);

    List<Livro> findByTituloContaining(String titulo);
}
