package com.felipelearn.livraria.domain;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.felipelearn.livraria.exception.DomainException;
import com.felipelearn.livraria.util.Utils;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "livro")
public class Livro {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "titulo", length = 100)
    private String titulo;
    @Column(name = "autor", length = 60)
    private String autor;
    @Column(name = "editora", length = 60)
    private String editora;
    @Column(name = "ano_edicao", length = 4)
    private int anoEdicao;
    @Column(name = "imagem_url")
    private String imagem;
    @Column(name = "disposivel")
    private boolean disponivel = true;
    @Column(name = "avaliacao")
    private int avalicao;
    // @OneToMany(mappedBy = "livro")
    // private List<Aluguel> aluguels;
    private Livro(){}
    public Livro(String titulo, String autor, String editora, String imagem, int anoEdicao){
        setTitulo(titulo);
        setAutor(autor);
        setEditora(editora);
        setImagem(imagem);
        setAnoEdicao(anoEdicao);
    }

    // public List<Aluguel> getAluguels() {
    //     return aluguels;
    // }
    // public void setAluguels(List<Aluguel> aluguels) {
    //     this.aluguels = aluguels;
    // }
   
    public boolean isDisponivel() {
        return disponivel;
    }
    public void setDisponivel(boolean disponivel) {
        this.disponivel = disponivel;
    }
    public int getAvalicao() {
        return avalicao;
    }
    public void setAvalicao(int avalicao) {
        if(avalicao < 1 || avalicao > 5){
            throw new DomainException("A avaliação deve ser entre 1 e 5.");
        }
        this.avalicao = avalicao;
    }
    public Long getId() {
        return id;
    }
    public String getTitulo() {
        return titulo;
    }
    public void setTitulo(String titulo) {
        if(Utils.stringNotNullOrEmptyOrBlank(titulo)){
            throw new DomainException("O título deve conter entre 1 e 100 caracters.");
        }
        this.titulo = titulo;
    }
    public String getAutor() {
        return autor;
    }
    public void setAutor(String autor) {
        if(Utils.stringNotNullOrEmptyOrBlank(autor)){
            throw new DomainException("Deve preencher um autor.");
        }
        this.autor = autor;
    }
    public String getEditora() {
        return editora;
    }
    public void setEditora(String editora) {
        if(Utils.stringNotNullOrEmptyOrBlank(editora)){
            throw new DomainException("Deve preencher uma editora.");
        }
        this.editora = editora;
    }
    public int getAnoEdicao() {
        return anoEdicao;
    }
    public void setAnoEdicao(int anoEdicao) {
        if(anoEdicao < 0 || anoEdicao > Calendar.getInstance().get(Calendar.YEAR)){
            throw new DomainException("Deve preencher uma editora.");
        }
        this.anoEdicao = anoEdicao;
    }
    public String getImagem() {
        return imagem;
    }
    public void setImagem(String imagem) {
        this.imagem = imagem;
    }

    public boolean alugar(){
        if(!isDisponivel()){
            return false;
        }
        setDisponivel(false);
        return true;
    }

    public boolean devolver(){
        if(isDisponivel() ){
            return false;
        }
        setDisponivel(true);
        return true;
    }
    
}
