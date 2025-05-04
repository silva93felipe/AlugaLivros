package com.felipelearn.livraria.service;

import org.jasypt.util.text.StrongTextEncryptor;

public class CryptoService {
    private static StrongTextEncryptor encryptor;
    static{
        encryptor = new StrongTextEncryptor();
        encryptor.setPassword("123456");
        // APRENDER A USAR ISSO
        //encryptor.setPassword(System.getenv("ENCRYPT_KEY"));
    }

    public static String encrypt(String value){
        return encryptor.encrypt(value);
    }

    public static String decrypt(String value){
        return encryptor.decrypt(value);
    }
}
