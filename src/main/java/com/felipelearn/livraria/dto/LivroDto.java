package com.felipelearn.livraria.dto;

public record LivroDto(Long id, String titulo, String autor, String editora, int anoEdicao, String imagem, boolean disponivel, int avalicao){}
