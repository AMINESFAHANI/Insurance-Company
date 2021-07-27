package dev.adventure.utils;

import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

public final class Password {
    private static byte[] saltShaker() throws NoSuchAlgorithmException {
        SecureRandom r = SecureRandom.getInstance("SHA1PRNG");
        byte[] salt = new byte[16];
        r.nextBytes(salt);
        return salt;
    }

    private static String hexer(byte[] bytes) {
        BigInteger i = new BigInteger(1, bytes);
        String hex = i.toString(16);
        int padding = (bytes.length * 2) - hex.length();
        if (padding > 0) {
            return String.format("%0" + padding + "d", 0) + hex;
        } else {
            return hex;
        }
    }

    private static byte[] byter(String hex) {
        byte[] bytes = new byte[hex.length() / 2];
        for (int i = 0; i < bytes.length; i++) {
            bytes[i] = (byte) Integer.parseInt(hex.substring(2 * i, 2 * i + 2), 16);
        }
        return bytes;
    }

    /**
     * Takes a new password and returns the hash and salt.
     *
     * In order for a password to be validated in the future, both the hash and the salt should be stored.
     *
     * @param passText: The plaintext password to be hashed
     * @return String[passHash, passSalt]
     *         passHash: The password's hash.
     *         passSalt: The salt used to generate the hash.
     */
    public static String[] hashGriddle(String passText) {

        char[] passChars = passText.toCharArray();
        byte[] passSalt = null;
        PBEKeySpec s;
        SecretKeyFactory f;
        byte[] passHash = null;

        try {
            passSalt = saltShaker();
            s = new PBEKeySpec(passChars, passSalt, 65536, 128);
            f = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
            passHash = f.generateSecret(s).getEncoded();
        } catch (NoSuchAlgorithmException | InvalidKeySpecException exception) {
            exception.printStackTrace();
        }
        return new String[] { hexer(passHash), hexer(passSalt) };
    }

    /**
     * Verifies a password against the stored hash and salt.
     *
     * @param passText: The plaintext password to be checked
     * @param passHash: The stored password hash
     * @param passSalt: The stored password salt
     * @return boolean
     *         returns true if the password and hash match
     *         returns false if the password and hash don't match
     */
    public static boolean checkPass(String passText, String passHash, String passSalt) {
        byte[] hash = byter(passHash);
        byte[] salt = byter(passSalt);
        PBEKeySpec s;
        SecretKeyFactory f;
        byte[] pass = null;

        try {
            s = new PBEKeySpec(passText.toCharArray(), salt, 65536, 128);
            f = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
            pass = f.generateSecret(s).getEncoded();
        } catch (NoSuchAlgorithmException | InvalidKeySpecException exception) {
            exception.printStackTrace();
        }

        int diff = 0;
        assert pass != null;
        if (hash.length != pass.length) {
            diff++;
        }
        for (int i = 0; i < hash.length && i < pass.length; i++) {
            if (hash[i] != pass[i]) {
                diff++;
            }
        }
        return diff == 0;
    }
}