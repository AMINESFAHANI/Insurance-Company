package dev.adventure.utiltests;

import dev.adventure.utils.Password;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

public class PasswordTest {
    @Test
    void testCorrectPassword() {
        String passText = "password";
        String[] passHash = Password.hashGriddle(passText);
        Assert.assertTrue(Password.checkPass("password", passHash[0], passHash[1]));
    }

    @Test
    void testIncorrectPassword() {
        String passText = "password";
        String[] passHash = Password.hashGriddle(passText);
        Assert.assertFalse(Password.checkPass("wrong_password", passHash[0], passHash[1]));
    }
}
