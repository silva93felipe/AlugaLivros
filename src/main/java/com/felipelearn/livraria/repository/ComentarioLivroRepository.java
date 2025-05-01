package com.felipelearn.livraria.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.felipelearn.livraria.domain.ComentarioLivro;

public interface ComentarioLivroRepository extends JpaRepository<ComentarioLivro, Long> {

}
