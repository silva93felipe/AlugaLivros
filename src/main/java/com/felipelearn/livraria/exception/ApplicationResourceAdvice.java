package com.felipelearn.livraria.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.felipelearn.livraria.dto.ApiResponse;
import com.felipelearn.livraria.util.Constantes;

@RestControllerAdvice
public class ApplicationResourceAdvice {
    @ExceptionHandler(LivroAlugadoException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiResponse handleException(LivroAlugadoException exception){
        Map<String, String> errors = new HashMap<>();
        errors.put("message", exception.getMessage());
        return new ApiResponse(  Constantes.FAILURE, null, errors);
    }

    @ExceptionHandler(LivroDevolvidoException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiResponse handleException(LivroDevolvidoException exception){
        Map<String, String> errors = new HashMap<>();
        errors.put("message", exception.getMessage());
        return new ApiResponse(  Constantes.FAILURE, null, errors);
    }

    @ExceptionHandler(LivroNotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiResponse handleException(LivroNotFoundException exception){
        Map<String, String> errors = new HashMap<>();
        errors.put("message", exception.getMessage());
        return new ApiResponse(  Constantes.FAILURE, null, errors);
    }

    @ExceptionHandler(MatriculaUtilizadaException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiResponse handleException(MatriculaUtilizadaException exception){
        Map<String, String> errors = new HashMap<>();
        errors.put("message", exception.getMessage());
        return new ApiResponse(  Constantes.FAILURE, null, errors);
    }

    @ExceptionHandler(ComentarioInvalidoException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiResponse handleException(ComentarioInvalidoException exception){
        Map<String, String> errors = new HashMap<>();
        errors.put("message", exception.getMessage());
        return new ApiResponse(  Constantes.FAILURE, null, errors);
    }

    @ExceptionHandler(DomainException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ApiResponse handleException(DomainException exception){
        Map<String, String> errors = new HashMap<>();
        errors.put("message", exception.getMessage());
        return new ApiResponse(  Constantes.FAILURE, null, errors);
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ApiResponse handleException(Exception exception){
        Map<String, String> errors = new HashMap<>();
        errors.put("message", "Houve um problema no momento. Tente novamente mais tarde.");
        return new ApiResponse( Constantes.FAILURE, null, errors);
    }
    @ExceptionHandler(UserInvalidException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ApiResponse handleException(UserInvalidException exception){
        Map<String, String> errors = new HashMap<>();
        errors.put("message", exception.getMessage());
        return new ApiResponse(  Constantes.FAILURE, null, errors);
    }
}
