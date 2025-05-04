package com.felipelearn.livraria.service.interfaces;

import com.felipelearn.livraria.dto.LoginRequest;

public interface IUserService {
    public String login(LoginRequest request);
    public void create(String email, String password);
}
