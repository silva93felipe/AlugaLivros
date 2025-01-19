package com.felipelearn.livraria.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.felipelearn.livraria.domain.Aluguel;

@Repository
public interface AluguelRepository extends JpaRepository<Aluguel, Long> {}
