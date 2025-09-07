package com.felipelearn.livraria.service.interfaces;

import java.util.List;

import com.felipelearn.livraria.domain.AluguelLivro;
import com.felipelearn.livraria.domain.ComentarioLivro;
import com.felipelearn.livraria.domain.Livro;
import com.felipelearn.livraria.dto.LivroRequest;

public interface ILivroService {
    public List<Livro> getAll();
    public Livro getById(Long id);
    public boolean alugar(Long livroId, Long usuarioId);
    public boolean devolver(Long livroId);
    public Livro create(LivroRequest livro);
    public void comentar(Long livroId, String comentario);
    public List<AluguelLivro> alugueis(Long id);
    public List<ComentarioLivro> comentarios(Long id);
}
