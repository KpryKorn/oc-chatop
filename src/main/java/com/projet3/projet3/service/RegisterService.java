package com.projet3.projet3.service;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.projet3.projet3.entity.User;
import com.projet3.projet3.repository.UserRepository;

@Service
public class RegisterService {
    private JWTService jwtService;
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    public RegisterService(JWTService jwtService, UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.jwtService = jwtService;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public String registerAndGenerateToken(String email, String name, String password) {

        userRepository.findByEmail(email).ifPresent(u -> {
            throw new RuntimeException("L'email existe déjà");
        });

        User user = User.builder()
                .email(email)
                .name(name)
                .password(passwordEncoder.encode(password))
                .build();

        userRepository.save(user);

        var authentication = new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword());

        return jwtService.generateToken(authentication);
    }
}
