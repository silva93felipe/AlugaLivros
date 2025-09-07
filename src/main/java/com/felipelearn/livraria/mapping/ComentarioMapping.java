package com.felipelearn.livraria.mapping;

import com.felipelearn.livraria.domain.ComentarioLivro;
import com.felipelearn.livraria.dto.ComentarioDto;

public class ComentarioMapping {

   // public static ComentarioLivro toEntity(){}

    public static ComentarioDto toDto(ComentarioLivro entity){
        return new ComentarioDto(entity.getId(), entity.getDescricao());
    }

}
