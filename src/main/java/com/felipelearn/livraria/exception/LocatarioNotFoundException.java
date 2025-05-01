package com.felipelearn.livraria.exception;

public class LocatarioNotFoundException extends RuntimeException {
    public LocatarioNotFoundException(){
        super("Locatário não encontrado.");
    }
}
