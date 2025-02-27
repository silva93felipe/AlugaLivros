package com.felipelearn.livraria.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.felipelearn.livraria.dto.ApiResponse;

@RestControllerAdvice
public class ApplicationResourceAdvice {

    @ExceptionHandler(LivroAlugadoException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiResponse handleException(LivroAlugadoException exception){
        return new ApiResponse(HttpStatus.BAD_REQUEST.value(), false, exception.getMessage());
    }

    @ExceptionHandler(LivroDevolvidoException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiResponse handleException(LivroDevolvidoException exception){
        return new ApiResponse(HttpStatus.BAD_REQUEST.value(), false, exception.getMessage());
    }

    @ExceptionHandler(LivroNotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiResponse handleException(LivroNotFoundException exception){
        return new ApiResponse(HttpStatus.BAD_REQUEST.value(), false, exception.getMessage());
    }
    @ExceptionHandler(LocatarioNotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiResponse handleException(LocatarioNotFoundException exception){
        return new ApiResponse(HttpStatus.BAD_REQUEST.value(), false, exception.getMessage());
    }

    @ExceptionHandler(MatriculaUtilizadaException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiResponse handleException(MatriculaUtilizadaException exception){
        return new ApiResponse(HttpStatus.BAD_REQUEST.value(), false, exception.getMessage());
    }
}
