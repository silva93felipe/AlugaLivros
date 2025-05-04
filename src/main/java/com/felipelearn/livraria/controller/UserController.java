package com.felipelearn.livraria.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.felipelearn.livraria.dto.ApiResponse;
import com.felipelearn.livraria.dto.LoginRequest;
import com.felipelearn.livraria.dto.UserRequest;
import com.felipelearn.livraria.service.interfaces.IUserService;

@RestController
public class UserController {
    private final IUserService _loginService;

    public UserController(IUserService _loginService) {
        this._loginService = _loginService;
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse> login(@RequestBody LoginRequest request){
        String token = _loginService.login(request);
          return ResponseEntity.ok(new ApiResponse(true, token));
    }

    @PostMapping("/user")
    public ResponseEntity create(@RequestBody UserRequest request){
        _loginService.create(request.email(), request.password());
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
