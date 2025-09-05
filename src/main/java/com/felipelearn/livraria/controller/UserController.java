package com.felipelearn.livraria.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.felipelearn.livraria.dto.UserRequest;
import com.felipelearn.livraria.service.interfaces.IUserService;

@RestController
@RequestMapping("/user")
public class UserController {
    private final IUserService _loginService;

    public UserController(IUserService _loginService) {
        this._loginService = _loginService;
    }

    @PostMapping()
    public ResponseEntity create(@RequestBody UserRequest request){
        _loginService.create(request.email(), request.password());
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
