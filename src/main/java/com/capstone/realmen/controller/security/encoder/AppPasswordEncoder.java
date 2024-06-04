package com.capstone.realmen.controller.security.encoder;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AppPasswordEncoder {
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
