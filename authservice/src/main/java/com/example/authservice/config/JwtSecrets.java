package com.example.authservice.config;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

public final class JwtSecrets {

    private static final RSAPrivateKey PRIVATE_KEY;
    private static final RSAPublicKey PUBLIC_KEY;

    static {
        KeyPairGenerator keyGen;
        try {
            keyGen = KeyPairGenerator.getInstance("RSA");
            keyGen.initialize(2048);
            KeyPair keyPair = keyGen.generateKeyPair();
            PUBLIC_KEY = (RSAPublicKey) keyPair.getPublic();
            PRIVATE_KEY = (RSAPrivateKey) keyPair.getPrivate();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Failed to generate RSA key pair", e);
        }
    }

    private JwtSecrets() {
        // Private constructor to prevent instantiation
    }

    public static RSAPrivateKey getPrivateKey() {
        return PRIVATE_KEY;
    }

    public static RSAPublicKey getPublicKey() {
        return PUBLIC_KEY;
    }
}