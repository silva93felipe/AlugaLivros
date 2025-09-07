package com.felipelearn.livraria.domain;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
public class EntityBase {
    @Column(name = "ativo")
    private boolean ativo = true;
    @Column(name = "criado_em")
    private Date criadoEm = new Date();
    @Column(name = "atualizado_em")
    private Date atualizadoEm = new Date();
}
