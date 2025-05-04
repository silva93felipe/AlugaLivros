package com.felipelearn.livraria.service;

import org.springframework.stereotype.Service;

import com.felipelearn.livraria.domain.Usuario;
import com.felipelearn.livraria.dto.LoginRequest;
import com.felipelearn.livraria.exception.UserInvalidException;
import com.felipelearn.livraria.repository.UserRepository;
import com.felipelearn.livraria.service.interfaces.IUserService;
import com.felipelearn.livraria.util.Utils;

@Service
public class UserService implements IUserService{
    private final UserRepository _userRepository;

    public UserService(UserRepository _userRepository) {
        this._userRepository = _userRepository;
    }

    @Override
    public String login(LoginRequest request) {
        Usuario usuario = _userRepository.findByEmail(request.username());
        if (usuario == null) 
            throw new UserInvalidException();

        String passwordDescriptografado = CryptoService.decrypt(usuario.getPassword());
        if(!passwordDescriptografado.equals(request.password()))
            throw new UserInvalidException();

        return "";
    }

    @Override
    public void create(String matricula, String password) {
        if(Utils.stringNotNullOrEmptyOrBlank(password) || Utils.stringNotNullOrEmptyOrBlank(matricula))
            throw new UserInvalidException();
        Usuario newUsario = new Usuario(matricula, CryptoService.encrypt(password));
        _userRepository.save(newUsario);
    }

}
