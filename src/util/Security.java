/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

import java.util.Optional;

/**
 *
 * @author Edgar
 */
public class Security {
    public static final char[] ALPHABET = "abcdefghijklmnñopqrstuvwxzyABCDEFGHIJKLMNÑOPQRSTUVWXYZ1234567890<>.,:;-_'?!\"#$%&/()=¿¡".toCharArray();
    
    public static int nonnegativeModulo(int m, int n) {
        m = m % n;
        if (m < 0) return m + n;
        return m;
    }
    
    public static Optional<String> encryptWith(int offset, String text) {
        String cipheredText = new String();
        outer_loop:
        for (int i = 0; i < text.length(); i++) {
            for (int j = 0; j < ALPHABET.length; j++) {
                if (ALPHABET[j] == text.charAt(i)) {
                    final int cipheredIndex = nonnegativeModulo(j + offset, ALPHABET.length);
                    final char cipheredChar = ALPHABET[cipheredIndex];
                    cipheredText += cipheredChar;
                    continue outer_loop;
               }
            }
            return Optional.empty();
        }
        return Optional.of(cipheredText);
    }
    
    public static Optional<String> decryptWith(int offset, String text) {
        return encryptWith(-offset, text);
    }
    
    public static Optional<String> encrypt(String text) {
        return encryptWith(3, text);
    }
    
    public static Optional<String> decrypt(String text) {
        return encryptWith(-3, text);
    }
}
