package br.com.pedidogm.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Douglas Gusson
 */
public class Seguranca {

    public static String criptografarSHA256(String str) {
        if (str != null) {
            try {
                MessageDigest algorithm = MessageDigest.getInstance("SHA-256");
                byte messageDigest[] = algorithm.digest(str.getBytes("UTF-8"));

                StringBuilder hexString = new StringBuilder();

                for (byte b : messageDigest) {
                    hexString.append(String.format("%02X", 0xFF & b));
                }

                return hexString.toString();

            } catch (NoSuchAlgorithmException | UnsupportedEncodingException ex) {
                Logger.getLogger(Seguranca.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }

    public static String encryptDecrypt(String input) {

        char[] key = {'K', 'C', 'Q'};

        StringBuilder output = new StringBuilder();
        for (int i = 0; i < input.length(); i++) {
            output.append((char) (input.charAt(i) ^ key[i % key.length]));
        }
        return output.toString();
    }

}
