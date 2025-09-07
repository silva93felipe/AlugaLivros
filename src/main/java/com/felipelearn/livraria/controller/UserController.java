package com.felipelearn.livraria.controller;

import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.felipelearn.livraria.dto.ApiResponse;
import com.felipelearn.livraria.dto.LoginRequest;
import com.felipelearn.livraria.dto.UserRequest;
import com.felipelearn.livraria.mapping.LivroMapping;
import com.felipelearn.livraria.service.interfaces.IUserService;
import com.felipelearn.livraria.util.Constantes;

@RestController
@RequestMapping("/users")
public class UserController {
    private final IUserService _userService;

    public UserController(IUserService userService) {
        this._userService = userService;
    }

    @PostMapping()
    public ResponseEntity<ApiResponse> create(@RequestBody UserRequest request){
        _userService.create(request.email(), request.password());
        return ResponseEntity.status(HttpStatusCode.valueOf(201)).body(new ApiResponse(Constantes.SUCCESS, "", null));
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse> login(@RequestBody LoginRequest request){
        String token = _userService.login(request);
        return ResponseEntity.ok(new ApiResponse(Constantes.SUCCESS, token, null));
    }

    @GetMapping("{id}/livros")
    public ResponseEntity<ApiResponse> livrosAlugadosByUsuarioId(@PathVariable Long id){
        return ResponseEntity.ok(new ApiResponse( Constantes.SUCCESS, _userService.livrosAlugadosByUsuarioId(id).stream().map(e -> LivroMapping.toDto(e)), null));
    }
    
}
