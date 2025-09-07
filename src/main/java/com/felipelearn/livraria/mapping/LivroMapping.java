package com.felipelearn.livraria.mapping;

import com.felipelearn.livraria.domain.Livro;
import com.felipelearn.livraria.dto.LivroDto;

public class LivroMapping {

    public static Livro toEntity(LivroDto dto){
        return new Livro(dto.titulo(), dto.autor(), dto.editora(), dto.imagem(), dto.anoEdicao());
    }

    public static LivroDto toDto(Livro entity){
        return new LivroDto(entity.getId(), entity.getTitulo(), entity.getAutor(), entity.getEditora(), entity.getAnoEdicao(), entity.getImagem(), entity.isDisponivel(), entity.getAvalicao());
    }
}
