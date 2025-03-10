package com.felipelearn.livraria.domain;

import java.util.Calendar;
import java.util.Date;
import com.felipelearn.livraria.exception.DomainException;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Aluguel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "alugado_em")
    private Date alugadoEm;
    @Column(name = "entregue_em")
    private Date entregueEm;
    @ManyToOne
    @JoinColumn(name = "livro_id")
    private Livro livro;
    @ManyToOne
    @JoinColumn(name = "locatario_id")
    private Locatario locatario;
  
    private Aluguel(){}
    public Aluguel(Date alugadoEm, Livro livro, Locatario locatario){
        setAlugadoEm(alugadoEm);
        setLivro(livro);
        setLocatario(locatario);
    }
    public Locatario getLocatario() {
        return locatario;
    }
    public void setLocatario(Locatario locatario) {
        this.locatario = locatario;
    }
    public Long getId() {
        return id;
    }
    public Date getAlugadoEm() {
        return alugadoEm;
    }
    public void setAlugadoEm(Date alugadoEm) {
        if(Calendar.getInstance().before(alugadoEm)){
            throw new DomainException("Data de aluguel não pode ser superior a data atual.");
        }
        this.alugadoEm = alugadoEm;
    }
    public Date getEntregueEm() {
        return entregueEm;
    }
    public void setEntregueEm(Date entregueEm) {
        if(Calendar.getInstance().before(entregueEm)){
            throw new DomainException("Data de entrega não pode ser superior a data atual.");
        }
        this.entregueEm = entregueEm;
    }
    public Livro getLivro() {
        return livro;
    }
    public void setLivro(Livro livro) {
        if(!livro.equals(null)){
            throw new DomainException("Deve associar um livro válido.");
        }
        this.livro = livro;
    }

    public void devolver(Date entregueEm){
        setEntregueEm(entregueEm);
    }
}
