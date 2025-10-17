package com.projet3.projet3.controller;

import org.springframework.web.bind.annotation.RestController;

import com.projet3.projet3.dto.ErrorResponseDTO;
import com.projet3.projet3.dto.JwtResponseDTO;
import com.projet3.projet3.dto.LoginRequestDTO;
import com.projet3.projet3.dto.UserResponseDTO;
import com.projet3.projet3.entity.User;
import com.projet3.projet3.service.LoginService;

import jakarta.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/auth")
public class LoginController {
    private LoginService loginService;

    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequestDTO loginRequest) {
        String token = loginService.authenticateAndGenerateToken(loginRequest.getEmail(), loginRequest.getPassword());
        if (token == null) {
            return ResponseEntity.status(401).body(new ErrorResponseDTO("Email ou mot de passe incorrect"));
        }

        return ResponseEntity.ok(new JwtResponseDTO(token));
    }

    @GetMapping("/me")
    public ResponseEntity<?> getMe(Authentication auth) {
        String email = auth.getName();

        User user = loginService.verifyUser(email);

        if (user == null) {
            return ResponseEntity.status(401).body(new ErrorResponseDTO(email + " non trouvé"));
        }

        UserResponseDTO response = new UserResponseDTO(user.getId(), user.getEmail(), user.getName(),
                user.getCreated_at(), user.getUpdated_at());

        return ResponseEntity.ok(response);
    }
}