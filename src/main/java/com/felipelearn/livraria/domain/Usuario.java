package com.felipelearn.livraria.domain;

import com.felipelearn.livraria.domain.enums.TipoUsuarioEnum;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Usuario extends EntityBase {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String matricula;
    private String password;
    private TipoUsuarioEnum tipo;
    private String email;
    private Usuario(){}
    public Usuario(String email, String password){
        this.email = email;
        this.password = password;
        this.tipo = TipoUsuarioEnum.ALUNO;
    }
    public Long getId() {
        return id;
    }
    public String getMatricula() {
        return matricula;
    }
    public String getPassword() {
        return password;
    }
    public TipoUsuarioEnum getPermissao() {
        return tipo;
    }
    public void setPermissao(TipoUsuarioEnum tipo) {
        this.tipo = tipo;
    }
        public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
}
