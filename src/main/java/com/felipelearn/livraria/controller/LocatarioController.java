package com.felipelearn.livraria.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.felipelearn.livraria.domain.Locatario;
import com.felipelearn.livraria.dto.LocatarioRequest;
import com.felipelearn.livraria.service.LocatarioService;

@RestController
@RequestMapping("/locatario")
public class LocatarioController {
    private LocatarioService _locatarioService;

    public LocatarioController(LocatarioService locatarioService) {
        this._locatarioService = locatarioService;
    }

    @GetMapping
    public ResponseEntity<List<Locatario>> gelAll(){
        return ResponseEntity.ok(_locatarioService.getAll());
    }

    @GetMapping("{id}")
    public ResponseEntity getById(@PathVariable Long id){
        try {
            return ResponseEntity.ok(_locatarioService.getById(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping()
    public ResponseEntity create(@RequestBody LocatarioRequest request){
        try{
            _locatarioService.create(request);
            return ResponseEntity.noContent().build();
        }catch(Exception e ){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
