package com.felipelearn.livraria.service;

import java.util.ArrayList;
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
import com.felipelearn.livraria.exception.UserInvalidException;
import com.felipelearn.livraria.domain.AluguelLivro;
import com.felipelearn.livraria.domain.ComentarioLivro;
import com.felipelearn.livraria.domain.Livro;
import com.felipelearn.livraria.domain.Usuario;
import com.felipelearn.livraria.repository.AluguelLivroRepository;
import com.felipelearn.livraria.repository.ComentarioLivroRepository;
import com.felipelearn.livraria.repository.LivroRepository;
import com.felipelearn.livraria.repository.UserRepository;
import com.felipelearn.livraria.service.interfaces.ILivroService;
import com.felipelearn.livraria.util.Constantes;
import com.felipelearn.livraria.util.Utils;

@Service
public class LivroService implements ILivroService {
    private final LivroRepository _livroRepository;
    private final AluguelLivroRepository _aluguelRepository;
    private final ComentarioLivroRepository _comentarioRepository;
    private final UserRepository _userRepository;

    public LivroService(LivroRepository livroRepository, AluguelLivroRepository aluguelRepository, ComentarioLivroRepository comentarioRepository, UserRepository userRepository){
        _livroRepository = livroRepository;
        _aluguelRepository = aluguelRepository;
        _comentarioRepository = comentarioRepository;
        _userRepository = userRepository;

    }

    public List<Livro> getAll(){
        return _livroRepository.findAll();
    }
    
    public Livro getById(Long id){
        Optional<Livro> livroDb = _livroRepository.findById(id);
        if( !livroDb.isPresent()){
            throw new LivroNotFoundException();
        }
        return livroDb.get();
    }

    public boolean alugar(Long livroId, Long usuarioId){
        Livro livroDb = getById(livroId);
        if( !livroDb.alugar() ){
            throw new LivroAlugadoException();
        }

        Optional<Usuario> usuario =_userRepository.findById(usuarioId);
        if(!usuario.isPresent()){
            throw new UserInvalidException();
        }
        AluguelLivro newAluguel = new AluguelLivro(new Date(), livroDb, usuario.get());
        _livroRepository.save(livroDb);
        _aluguelRepository.save(newAluguel);
        return Constantes.SUCCESS;
    }

    public List<AluguelLivro> alugueis(Long livroId){
        getById(livroId);
        return _aluguelRepository.findAll();
    }
    
    public boolean devolver(Long livroId) {
        Livro livroDb = getById(livroId);
        if( !livroDb.devolver() ){
            throw new LivroDevolvidoException();
        }
        _livroRepository.save(livroDb);
        Optional<AluguelLivro> aluguelDb  = _aluguelRepository.findAll()
                                   .stream()
                                   .filter(e -> e.getLivro().equals(livroId) && e.getEntregueEm().equals(null))
                                   .findFirst();
        if(aluguelDb.isPresent()){
            aluguelDb.get().devolver(Calendar.getInstance().getTime());
            _aluguelRepository.save(aluguelDb.get());
            return Constantes.SUCCESS;
        }

        return Constantes.FAILURE;
    }
    

    public Livro create(LivroRequest livro) {
        Livro newLivro = new Livro(livro.title(), livro.autor(), livro.editora(), livro.imagem(), livro.anoEdicao());
        _livroRepository.save(newLivro);
        return newLivro;
    }

    @Override
    public void comentar(Long livroId, String comentario) {
        Livro livroDb = getById(livroId);

        if(Utils.stringNotNullOrEmptyOrBlank(comentario)){
            throw new ComentarioInvalidoException("Digite um comentário válido.");
        }
        ComentarioLivro newComentario = new ComentarioLivro(comentario, livroDb);
        _comentarioRepository.save(newComentario);
    }

    @Override
    public List<ComentarioLivro> comentarios(Long livroId) {
        getById(livroId);
        return _comentarioRepository.findAll();
    }
}