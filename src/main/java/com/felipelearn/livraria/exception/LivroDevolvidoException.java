package com.felipelearn.livraria.exception;

public class LivroDevolvidoException extends RuntimeException{
    public LivroDevolvidoException(){
        super("Livro já devolvido.");
    }
}
