package com.felipelearn.livraria.domain;

import java.util.List;

import com.felipelearn.livraria.exception.DomainException;
import com.felipelearn.livraria.util.Utils;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
@Entity
public class Locatario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 100)
    private String nome;
    @Column(length = 20)
    private String matricula;
    // @OneToMany(mappedBy = "locatario")
    // private List<Aluguel> aluguels;
    private Locatario(){}
    public Locatario(String nome, String matricula) {
        setNome(nome);
        setMatricula(matricula);
    }
    public Long getId() {
        return id;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        if(Utils.stringNotNullOrEmptyOrBlank(nome)){
            throw new DomainException("O nome dever ser preenchido");
        }
        this.nome = nome;
    }
    public String getMatricula() {
        return matricula;
    }
    public void setMatricula(String matricula) {
        if(Utils.stringNotNullOrEmptyOrBlank(matricula)){
            throw new DomainException("A matrícula deve ser preenchida.");
        }
        this.matricula = matricula;
    }
}
