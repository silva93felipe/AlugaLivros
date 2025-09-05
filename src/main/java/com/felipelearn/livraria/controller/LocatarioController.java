package com.felipelearn.livraria.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.felipelearn.livraria.dto.ApiResponse;
import com.felipelearn.livraria.dto.LocatarioRequest;
import com.felipelearn.livraria.service.LocatarioService;
import com.felipelearn.livraria.util.Constantes;

@RestController
@RequestMapping("/locatario")
public class LocatarioController {
    private LocatarioService _locatarioService;

    public LocatarioController(LocatarioService locatarioService) {
        this._locatarioService = locatarioService;
    }

    @GetMapping
    public ResponseEntity<ApiResponse> getAll(){
        return ResponseEntity.ok(new ApiResponse( Constantes.SUCCESS, _locatarioService.getAll()));
    }

    @GetMapping("{id}")
    public ResponseEntity<ApiResponse> getById(@PathVariable Long id){
        return ResponseEntity.ok(new ApiResponse( Constantes.SUCCESS, _locatarioService.getById(id)));
    }

    @PostMapping()
    public ResponseEntity<ApiResponse> create(@RequestBody LocatarioRequest request){
        _locatarioService.create(request);
        return ResponseEntity.noContent().build();
    }
}
