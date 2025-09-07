package com.felipelearn.livraria.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.felipelearn.livraria.domain.AluguelLivro;

@Repository
public interface AluguelLivroRepository extends JpaRepository<AluguelLivro, Long> {
    List<AluguelLivro> findByLivroId(Long livroId);
}
