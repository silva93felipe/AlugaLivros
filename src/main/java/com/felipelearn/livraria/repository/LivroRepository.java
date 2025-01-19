package com.felipelearn.livraria.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.felipelearn.livraria.domain.Livro;

@Repository
public interface LivroRepository extends JpaRepository<Livro, Long>{ }
