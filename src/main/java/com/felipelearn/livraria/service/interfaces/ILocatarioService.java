package com.felipelearn.livraria.service.interfaces;

import java.util.List;

import com.felipelearn.livraria.domain.Locatario;
import com.felipelearn.livraria.dto.LocatarioRequest;

public interface ILocatarioService {
    List<Locatario> getAll();
    Locatario getById(Long id) throws Exception;
    void create(LocatarioRequest request) throws Exception ;
    void save(Locatario newLocatario);
    Locatario findByMatricula(String matricula);
}
