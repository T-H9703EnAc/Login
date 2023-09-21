package com.app.login.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class PasswordVerificationService {
        
    private final PasswordEncoder passwordEncoder;

    public PasswordVerificationService(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    public boolean isPasswordValid(String providedPassword, UserDetails userDetails) {
        return passwordEncoder.matches(providedPassword, userDetails.getPassword());
    }
}
