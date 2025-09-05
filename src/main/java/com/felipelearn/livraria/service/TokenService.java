package com.felipelearn.livraria.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.felipelearn.livraria.domain.Usuario;

@Service
public class TokenService {
    @Value("jwt.secret")
    private String secret;

    public String generate(Usuario usuario){
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.create()
                        .withIssuer("livraria")
                        .withSubject(usuario.getMatricula())
                        .sign(algorithm);
        } catch (JWTCreationException e) {
            throw new RuntimeException("Porblema na geração do token.");
        }
    }

    public String validate(String token){
        try {
             Algorithm algorithm = Algorithm.HMAC256(secret);
             return JWT.require(algorithm)
                        .withIssuer("livraria")
                        .build()
                        .verify(token)
                        .getSubject();
        } catch (JWTVerificationException e) {
            return "";
        }

    }
}
