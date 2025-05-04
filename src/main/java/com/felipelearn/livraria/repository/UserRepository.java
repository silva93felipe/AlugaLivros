package com.felipelearn.livraria.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.felipelearn.livraria.domain.Usuario;

@Repository
public interface UserRepository extends JpaRepository<Usuario, Long>{
    Usuario findByEmail(String email);
}
