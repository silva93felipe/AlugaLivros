package com.felipelearn.livraria.exception;

public class ComentarioInvalidoException extends RuntimeException {
    public ComentarioInvalidoException(String mensagem){
        super(mensagem);
    }
}
