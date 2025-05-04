package com.felipelearn.livraria.exception;

public class UserInvalidException extends RuntimeException{
    public UserInvalidException(){
        super("Usuário inválido");
    }
}