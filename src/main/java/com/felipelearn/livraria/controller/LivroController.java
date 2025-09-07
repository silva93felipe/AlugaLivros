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
import com.felipelearn.livraria.mapping.LivroMapping;
import com.felipelearn.livraria.service.interfaces.ILivroService;
import com.felipelearn.livraria.util.Constantes;


@RestController
@RequestMapping("/livros")
public class LivroController {
    private ILivroService _livroService;
   
    public LivroController(ILivroService _livroService) {
        this._livroService = _livroService;
    }

    @GetMapping
    public ResponseEntity<ApiResponse> getAll(){
        return ResponseEntity.ok(new ApiResponse( Constantes.SUCCESS, _livroService.getAll().stream().filter(e -> e.isDisponivel()).map(e -> LivroMapping.toDto(e)), null));
    }

    @GetMapping("{id}")
    public ResponseEntity<ApiResponse> getById(@PathVariable Long id){
        return ResponseEntity.ok(new ApiResponse( Constantes.SUCCESS, LivroMapping.toDto(_livroService.getById(id)), null));
    }

    @PostMapping("{id}/alugueis")
    public ResponseEntity<ApiResponse> alugar(@PathVariable Long id, @RequestBody Long usuarioId){
        _livroService.alugar(id, usuarioId);   
        return ResponseEntity.noContent().build();
    }

    @GetMapping("{id}/alugueis")
    public ResponseEntity<ApiResponse> alugueis(@PathVariable Long id){
        return ResponseEntity.ok(new ApiResponse( Constantes.SUCCESS, _livroService.alugueis(id).stream().filter(e -> e.getLivro().getId().equals(id)), null));   
    }

    @PostMapping("{id}/devolucoes")
    public ResponseEntity<ApiResponse> devolver(@PathVariable Long id){
         _livroService.devolver(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("{id}/comentarios")
    public ResponseEntity<ApiResponse> comentar(@PathVariable Long id, @RequestBody String comentario){
        _livroService.comentar(id, comentario);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("{id}/comentarios")
    public ResponseEntity<ApiResponse> comentarios(@PathVariable Long id){
         return ResponseEntity.ok(new ApiResponse( Constantes.SUCCESS, 
                        _livroService.comentarios(id)
                                    .stream()
                                    .filter(e -> e.getLivro().getId().equals(id)),
                                    null));
    }

    @PostMapping()
    public ResponseEntity<ApiResponse> create(@RequestBody LivroRequest request ){
        Livro newLivro = _livroService.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body((new ApiResponse(Constantes.SUCCESS, newLivro, null)));
    }
}
