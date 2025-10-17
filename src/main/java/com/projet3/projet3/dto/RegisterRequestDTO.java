package com.projet3.projet3.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class RegisterRequestDTO {
    @Email(message = "Email invalide")
    @NotBlank(message = "Email obligatoire")
    private String email;

    @NotBlank(message = "Nom obligatoire")
    private String name;

    @NotBlank(message = "Mot de passe obligatoire")
    private String password;
}
