package com.felipelearn.livraria.service.interfaces;

import java.util.List;

import com.felipelearn.livraria.domain.Livro;
import com.felipelearn.livraria.dto.LoginRequest;

public interface IUserService {
    public String login(LoginRequest request);
    public void create(String email, String password);
    List<Livro> livrosAlugadosByUsuarioId(Long usuarioId);
}
