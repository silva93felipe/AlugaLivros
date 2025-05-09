package com.felipelearn.livraria.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.felipelearn.livraria.domain.Livro;
import com.felipelearn.livraria.dto.ApiResponse;
import com.felipelearn.livraria.dto.LivroRequest;
import com.felipelearn.livraria.service.interfaces.ILivroService;
import com.felipelearn.livraria.util.Constantes;


@RestController
@RequestMapping("/livro")
public class LivroController {
    private ILivroService _livroService;
   
    public LivroController(ILivroService _livroService) {
        this._livroService = _livroService;
    }

    @GetMapping
    public ResponseEntity<ApiResponse> getAll(){
        return ResponseEntity.ok(new ApiResponse(HttpStatus.OK.value(), Constantes.SUCCESS, _livroService.getAll()));
    }

    @GetMapping("{id}")
    public ResponseEntity<ApiResponse> getById(@PathVariable Long id){
        return ResponseEntity.ok(new ApiResponse(HttpStatus.OK.value(), Constantes.SUCCESS, _livroService.getById(id)));
    }

    @PostMapping("{id}/alugar")
    public ResponseEntity<ApiResponse> alugar(@PathVariable Long id, @RequestBody String matricula){
        _livroService.alugar(id, matricula);   
        return ResponseEntity.noContent().build();
    }

    @PostMapping("{id}/devolver")
    public ResponseEntity<ApiResponse> devolver(@PathVariable Long id){
         _livroService.devolver(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("{id}/comentar")
    public ResponseEntity<ApiResponse> comentar(@PathVariable Long id, @RequestBody String comentario){
        _livroService.comentar(id, comentario);
        return ResponseEntity.noContent().build();
    }

    @PostMapping()
    public ResponseEntity<ApiResponse> create(@RequestBody LivroRequest request ){
        Livro newLivro = _livroService.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body((new ApiResponse(HttpStatus.CREATED.value(), Constantes.SUCCESS, newLivro)));
    }
}
