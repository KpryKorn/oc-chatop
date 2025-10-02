package com.projet3.projet3.service;

import java.util.Optional;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.projet3.projet3.entity.User;
import com.projet3.projet3.repository.UserRepository;

@Service
public class LoginService {
    private JWTService jwtService;
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    public LoginService(JWTService jwtService, UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.jwtService = jwtService;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public String authenticateAndGenerateToken(String email, String password) {
        Optional<User> user = userRepository.findByEmail(email);
        if (user.isEmpty() || !passwordEncoder.matches(password, user.get().getPassword())) {
            return null;
        }

        var authentication = new UsernamePasswordAuthenticationToken(user.get().getEmail(), null);

        return jwtService.generateToken(authentication);
    }
}
