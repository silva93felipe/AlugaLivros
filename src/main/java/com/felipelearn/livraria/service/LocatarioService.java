package com.felipelearn.livraria.service;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

import com.felipelearn.livraria.domain.Locatario;
import com.felipelearn.livraria.dto.LocatarioRequest;
import com.felipelearn.livraria.repository.LocatarioRepository;
import com.felipelearn.livraria.service.interfaces.ILocatarioService;

@Service
public class LocatarioService implements ILocatarioService {
    private LocatarioRepository _locatarioRepository;

    public LocatarioService(LocatarioRepository locatarioRepository) {
        this._locatarioRepository = locatarioRepository;
    }

    @Override
    public List<Locatario> getAll() {
        return _locatarioRepository.findAll();
    }

    @Override
    public Locatario getById(Long id) throws Exception {
        Optional<Locatario> optional = _locatarioRepository.findById(id); 
        if( !optional.isPresent())
            throw new Exception("Locatário não encontrado");
        
        return optional.get();
    }

    @Override
    public void create(LocatarioRequest request) throws Exception  {
        Locatario locatario = findByMatricula(request.matricula()); 
        if(locatario != null)
            throw new Exception("Matricula já utilizada");
        Locatario newLocatario = new Locatario(request.nome(),  request.matricula());
        save(newLocatario);
    }

    @Override
    public Locatario findByMatricula(String matricula) {
        return _locatarioRepository.findByMatricula(matricula);
    }

    @Override
    public void save(Locatario newLocatario) {
        _locatarioRepository.save(newLocatario);
    }
}
