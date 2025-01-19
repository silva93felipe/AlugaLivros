package com.felipelearn.livraria.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.felipelearn.livraria.domain.Locatario;

@Repository
public interface LocatarioRepository extends JpaRepository<Locatario, Long>{
    Locatario findByMatricula(String mastricula);
}
