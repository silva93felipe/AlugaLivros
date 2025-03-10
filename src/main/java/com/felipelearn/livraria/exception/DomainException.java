package com.felipelearn.livraria.exception;

public class DomainException extends RuntimeException{
    public DomainException(String mensagem){
        super(mensagem);
    }
}
