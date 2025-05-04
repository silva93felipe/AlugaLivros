package com.felipelearn.livraria.service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

import com.felipelearn.livraria.dto.LivroRequest;
import com.felipelearn.livraria.exception.ComentarioInvalidoException;
import com.felipelearn.livraria.exception.LivroAlugadoException;
import com.felipelearn.livraria.exception.LivroDevolvidoException;
import com.felipelearn.livraria.exception.LivroNotFoundException;
import com.felipelearn.livraria.exception.LocatarioNotFoundException;
import com.felipelearn.livraria.domain.AluguelLivro;
import com.felipelearn.livraria.domain.ComentarioLivro;
import com.felipelearn.livraria.domain.Livro;
import com.felipelearn.livraria.domain.Locatario;
import com.felipelearn.livraria.repository.AluguelLivroRepository;
import com.felipelearn.livraria.repository.ComentarioLivroRepository;
import com.felipelearn.livraria.repository.LivroRepository;
import com.felipelearn.livraria.service.interfaces.ILivroService;
import com.felipelearn.livraria.util.Utils;

@Service
public class LivroService implements ILivroService {
    private final LivroRepository _livroRepository;
    private final AluguelLivroRepository _aluguelRepository;
    private final LocatarioService _locatarioService;
    private final ComentarioLivroRepository _comentarioRepository;

    public LivroService(LivroRepository livroRepository, AluguelLivroRepository aluguelRepository, LocatarioService locatarioService, ComentarioLivroRepository comentarioRepository){
        this._livroRepository = livroRepository;
        this._aluguelRepository = aluguelRepository;
        this._locatarioService = locatarioService;
        this._comentarioRepository = comentarioRepository;
    }

    public List<Livro> getAll(){
        return _livroRepository.findAll()
                                .stream()
                                .filter(l -> l.isDisponivel())
                                .toList();
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
        AluguelLivro newAluguel = new AluguelLivro(new Date(), livroDb, locatario);
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
        Optional<AluguelLivro> aluguelDb  = _aluguelRepository.findAll()
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

    @Override
    public void comentar(Long id, String comentario) {
        Optional<Livro> livroDb = _livroRepository.findById(id);
        if( !livroDb.isPresent()){
            throw new LivroNotFoundException();
        }

        if(Utils.stringNotNullOrEmptyOrBlank(comentario)){
            throw new ComentarioInvalidoException("Digite um comentário válido.");
        }
        ComentarioLivro newComentario = new ComentarioLivro(comentario, livroDb.get());
        _comentarioRepository.save(newComentario);
    }
}