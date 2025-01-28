package com.felipelearn.livraria.exception;

public class MatriculaUtilizadaException extends RuntimeException{
    public MatriculaUtilizadaException(){
        super("Matrícula já utilizada.");
    }
}
