package com.felipelearn.livraria.exception;

public class LivroAlugadoException extends RuntimeException{
    public LivroAlugadoException(){
        super("Livro já alugado.");
    }
}
