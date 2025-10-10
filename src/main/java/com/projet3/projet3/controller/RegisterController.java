package com.projet3.projet3.controller;

import org.springframework.web.bind.annotation.RestController;

import com.projet3.projet3.dto.ErrorResponseDTO;
import com.projet3.projet3.dto.JwtResponseDTO;
import com.projet3.projet3.dto.RegisterRequestDTO;
import com.projet3.projet3.service.RegisterService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/auth")
public class RegisterController {
    private RegisterService registerService;

    public RegisterController(RegisterService registerService) {
        this.registerService = registerService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequestDTO registerRequest) {
        String error = validateRequest(registerRequest);
        if (error != null) {
            return ResponseEntity.badRequest().body(new ErrorResponseDTO(error));
        }

        String token = registerService.registerAndGenerateToken(registerRequest.getEmail(), registerRequest.getName(),
                registerRequest.getPassword());

        if (token == null) {
            return ResponseEntity.status(400).body(new ErrorResponseDTO("Email déjà utilisé"));
        }

        return ResponseEntity.ok(new JwtResponseDTO(token));
    }

    // créer une fonction utilitaire réutilisable ?
    private String validateRequest(RegisterRequestDTO request) {
        if (request.getEmail() == null || request.getEmail().isBlank()) {
            return "Email obligatoire";
        }
        if (!request.getEmail().contains("@")) {
            return "Email invalide";
        }
        if (request.getName() == null || request.getName().isBlank()) {
            return "Nom obligatoire";
        }
        if (request.getPassword() == null || request.getPassword().isBlank()) {
            return "Mot de passe obligatoire";
        }
        if (request.getPassword().length() < 6) {
            return "Mot de passe trop court (min 6 caractères)";
        }
        return null;
    }
}
