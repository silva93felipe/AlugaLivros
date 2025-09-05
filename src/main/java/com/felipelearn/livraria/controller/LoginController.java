package com.felipelearn.livraria.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.felipelearn.livraria.dto.ApiResponse;
import com.felipelearn.livraria.dto.LoginRequest;
import com.felipelearn.livraria.service.interfaces.IUserService;

@RestController
@RequestMapping("/login")
public class LoginController {
private final IUserService _userService;

    public LoginController(IUserService userService) {
        this._userService = userService;
    }
    
    @PostMapping()
    public ResponseEntity<ApiResponse> login(@RequestBody LoginRequest request){
        String token = _userService.login(request);
        return ResponseEntity.ok(new ApiResponse(true, token));
    }

}
