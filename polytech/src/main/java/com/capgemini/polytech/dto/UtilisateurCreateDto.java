package com.capgemini.polytech.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UtilisateurCreateDto {
    private int id;
    private String nom;
    private String prenom;
    private String mail;
    private String username;
    private String password;
    
        // Getters et Setters
    }
