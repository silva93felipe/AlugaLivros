package com.felipelearn.livraria.service;

import org.jasypt.util.text.StrongTextEncryptor;

public class CryptoService {
    private static StrongTextEncryptor encryptor;
    private static final String KEY_CRYPTOR = "9ceff5e5-1efb-4b98-b89f-85f2763e1fa1";
    static{
        encryptor = new StrongTextEncryptor();
        encryptor.setPassword(KEY_CRYPTOR);
        // APRENDER A USAR GETENV
        //encryptor.setPassword(System.getenv("ENCRYPT_KEY"));
    }

    public static String encrypt(String value){
        return encryptor.encrypt(value);
    }

    public static String decrypt(String value){
        return encryptor.decrypt(value);
    }
}
