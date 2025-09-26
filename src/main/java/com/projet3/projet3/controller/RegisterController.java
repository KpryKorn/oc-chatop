package com.projet3.projet3.controller;

import org.springframework.web.bind.annotation.RestController;

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
    public ResponseEntity<JwtResponseDTO> register(@RequestBody RegisterRequestDTO registerRequest) {
        String token = registerService.registerAndGenerateToken(registerRequest.getEmail(), registerRequest.getName(),
                registerRequest.getPassword());

        return ResponseEntity.ok(new JwtResponseDTO(token));
    }

}
