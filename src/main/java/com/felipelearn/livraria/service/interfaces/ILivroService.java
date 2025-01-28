package com.felipelearn.livraria.service.interfaces;

import java.util.List;

import com.felipelearn.livraria.domain.Livro;
import com.felipelearn.livraria.dto.LivroRequest;

public interface ILivroService {
    public List<Livro> getAll();
    public Livro getById(Long id);
    public boolean alugar(Long livroId, String matricula);
    public boolean devolver(Long livroId);
    public Livro create(LivroRequest livro);
}
