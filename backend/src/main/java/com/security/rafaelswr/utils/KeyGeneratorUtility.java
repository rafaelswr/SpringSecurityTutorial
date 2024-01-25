package com.security.rafaelswr.utils;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.util.jar.JarEntry;

public class KeyGeneratorUtility {
    public static KeyPair generateRSAKey(){

        try {
            //generate a RSA algorithm keyPair with a 2048 size key
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
            keyPairGenerator.initialize(2048);
            //return an keyPair generated
            return keyPairGenerator.generateKeyPair();

        }catch (Exception e){
            throw new RuntimeException("Erro ao gerar chaves RSA", e);
        }


    }
}
