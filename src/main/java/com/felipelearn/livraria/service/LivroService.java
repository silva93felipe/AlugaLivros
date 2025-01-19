package com.felipelearn.livraria.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

import com.felipelearn.livraria.dto.LivroRequest;
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
    
    public Livro getById(Long id) throws Exception{
        Optional<Livro> livroDb = _livroRepository.findById(id);
        if( !livroDb.isPresent()){
            throw new Exception("Livro not found");
        }
        return livroDb.get();
    }

    public boolean alugar(Long livroId, String matricula) throws Exception{
        Livro livroDb = getById(livroId);
        if( !livroDb.alugar() ){
            throw new Exception("Livro já alugado");
        }

        Locatario locatario =_locatarioService.findByMatricula(matricula);
        if(locatario == null){
            throw new Exception("Locatário não encontrado");
        }
        Aluguel newAluguel = new Aluguel(new Date(), livroDb, locatario);
        _livroRepository.save(livroDb);
        _aluguelRepository.save(newAluguel);
        return true;
    }
    
    public boolean devolver(Long livroId) throws Exception{
        Livro livroDb = getById(livroId);
        if( !livroDb.devolver() ){
            throw new Exception("Livro já devolvido");
        }
        _livroRepository.save(livroDb);
        Optional<Aluguel> aluguelDb  = _aluguelRepository.findAll()
                                   .stream()
                                   .filter(e -> e.getLivro().getId() == livroId && e.getEntregueEm() == null)
                                   .findFirst();
        if(aluguelDb.isPresent()){
            aluguelDb.get().devolver(new Date());
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