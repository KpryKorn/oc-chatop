package com.projet3.projet3.controller;

import org.springframework.web.bind.annotation.RestController;

import com.projet3.projet3.service.JWTService;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;

@RestController
public class LoginController {
    private JWTService jwtService;

    public LoginController(JWTService jwtService) {
        this.jwtService = jwtService;
    }

    @PostMapping("/login")
    // todo: placer la logique de création du token dans un service après
    // vérification des identifiants
    public String getToken(Authentication authentication) {
        return this.jwtService.generateToken(authentication);
    }

}
