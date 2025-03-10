package com.felipelearn.livraria.service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

import com.felipelearn.livraria.dto.LivroRequest;
import com.felipelearn.livraria.exception.LivroAlugadoException;
import com.felipelearn.livraria.exception.LivroDevolvidoException;
import com.felipelearn.livraria.exception.LivroNotFoundException;
import com.felipelearn.livraria.exception.LocatarioNotFoundException;
import com.felipelearn.livraria.domain.Aluguel;
import com.felipelearn.livraria.domain.Livro;
import com.felipelearn.livraria.domain.Locatario;
import com.felipelearn.livraria.repository.AluguelRepository;
import com.felipelearn.livraria.repository.LivroRepository;
import com.felipelearn.livraria.service.interfaces.ILivroService;

@Service
public class LivroService implements ILivroService {
    private LivroRepository _livroRepository;
    private AluguelRepository _aluguelRepository;
    private LocatarioService _locatarioService;

    public LivroService(LivroRepository livroRepository, AluguelRepository aluguelRepository, LocatarioService locatarioService){
        this._livroRepository = livroRepository;
        this._aluguelRepository = aluguelRepository;
        this._locatarioService = locatarioService;
    }

    public List<Livro> getAll(){
        return _livroRepository.findAll().stream().filter(l -> l.isDisponivel()).toList();
    }
    
    public Livro getById(Long id){
        Optional<Livro> livroDb = _livroRepository.findById(id);
        if( !livroDb.isPresent()){
            throw new LivroNotFoundException();
        }
        return livroDb.get();
    }

    public boolean alugar(Long livroId, String matricula){
        Livro livroDb = getById(livroId);
        if( !livroDb.alugar() ){
            throw new LivroAlugadoException();
        }

        Locatario locatario =_locatarioService.findByMatricula(matricula);
        if(locatario == null){
            throw new LocatarioNotFoundException();
        }
        Aluguel newAluguel = new Aluguel(new Date(), livroDb, locatario);
        _livroRepository.save(livroDb);
        _aluguelRepository.save(newAluguel);
        return true;
    }
    
    public boolean devolver(Long livroId) {
        Livro livroDb = getById(livroId);
        if( !livroDb.devolver() ){
            throw new LivroDevolvidoException();
        }
        _livroRepository.save(livroDb);
        Optional<Aluguel> aluguelDb  = _aluguelRepository.findAll()
                                   .stream()
                                   .filter(e -> e.getLivro().getId() == livroId && e.getEntregueEm().equals(null))
                                   .findFirst();
        if(aluguelDb.isPresent()){
            aluguelDb.get().devolver(Calendar.getInstance().getTime());
            _aluguelRepository.save(aluguelDb.get());
            return true;
        }

        return false;
    }

    public Livro create(LivroRequest livro) {
        Livro newLivro = new Livro(livro.title(), livro.autor(), livro.editora(), livro.imagem(), livro.anoEdicao());
        _livroRepository.save(newLivro);
        return newLivro;
    }
}