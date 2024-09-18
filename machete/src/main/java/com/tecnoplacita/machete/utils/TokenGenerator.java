package com.tecnoplacita.machete.utils;

import java.security.SecureRandom;

public class TokenGenerator {

    private static final SecureRandom random = new SecureRandom();

    public static String generateToken() {
        int token = 10000 + random.nextInt(90000); // Genera un número aleatorio de 5 dígitos
        return String.valueOf(token);
    }

    public static void main(String[] args) {
        String token = generateToken();
        System.out.println("Generated token: " + token);
    }
}
