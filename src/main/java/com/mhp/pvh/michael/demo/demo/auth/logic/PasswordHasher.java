package com.mhp.pvh.michael.demo.demo.auth.logic;

import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;

@Service
public class PasswordHasher {
    String hashPassword(String password) {
        var encoder = new BCryptPasswordEncoder(10, new SecureRandom());
        return encoder.encode(password);
    }

    boolean verifyPassword(String password, String hashed) {
        return BCrypt.checkpw(password, hashed);
    }
}