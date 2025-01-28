package com.felipelearn.livraria.exception;

public class LocatarioNotFoundException extends RuntimeException {
    public LocatarioNotFoundException(){
        super("Locatária não encontrado.");
    }
}
