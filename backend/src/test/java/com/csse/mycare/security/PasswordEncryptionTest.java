package com.csse.mycare.security;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class PasswordEncryptionTest {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    public void testEncryptPassword() {
        String rawPassword = "mySecretPassword";
        String encryptedPassword = passwordEncoder.encode(rawPassword);

        assertNotNull(encryptedPassword);
        assertNotEquals(rawPassword, encryptedPassword);
    }

    @Test
    public void testCheckPassword() {
        String rawPassword = "mySecretPassword";
        String encryptedPassword = passwordEncoder.encode(rawPassword);

        assertTrue(passwordEncoder.matches(rawPassword, encryptedPassword));
        assertFalse(passwordEncoder.matches("wrongPassword", encryptedPassword));
    }
}