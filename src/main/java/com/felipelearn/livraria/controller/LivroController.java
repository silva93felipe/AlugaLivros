package com.felipelearn.livraria.controller;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.felipelearn.livraria.domain.Livro;
import com.felipelearn.livraria.dto.LivroRequest;
import com.felipelearn.livraria.service.interfaces.ILivroService;

@RestController
@RequestMapping("/livro")
public class LivroController {
    private ILivroService _livroService;
    public LivroController(ILivroService _livroService) {
        this._livroService = _livroService;
    }

    @GetMapping
    public ResponseEntity<List<Livro>> getAll(){
         return ResponseEntity.ok(_livroService.getAll());
    }

    @GetMapping("{id}")
    public ResponseEntity getById(@PathVariable Long id){
        return ResponseEntity.ok(_livroService.getById(id));
    }

    @PostMapping("{id}/alugar")
    public ResponseEntity alugar(@PathVariable Long id, @RequestBody String matricula){
        boolean isAlugado = _livroService.alugar(id, matricula);
        if(isAlugado)
            return ResponseEntity.noContent().build();
        return ResponseEntity.badRequest().build();
    }

    @PostMapping("{id}/devolver")
    public ResponseEntity devolver(@PathVariable Long id){
        boolean isDevolvido = _livroService.devolver(id);
        if(isDevolvido)
            return ResponseEntity.noContent().build();
        return ResponseEntity.badRequest().build();
    }

    @PostMapping()
    public ResponseEntity create(@RequestBody LivroRequest request ){
        Livro newLivro = _livroService.create(request);
        return ResponseEntity.ok(newLivro);
    }
}
