package com.felipelearn.livraria.service.interfaces;

import java.util.List;

import com.felipelearn.livraria.domain.Livro;
import com.felipelearn.livraria.dto.LivroRequest;

public interface ILivroService {
    public List<Livro> getAll();
    public Livro getById(Long id) throws Exception;
    public boolean alugar(Long livroId, String matricula) throws Exception;
    public boolean devolver(Long livroId) throws Exception;
    public Livro create(LivroRequest livro);
}
