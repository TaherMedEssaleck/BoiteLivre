package com.capgemini.polytech.dto;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UtilisateurDto {

    private int id;
    private String nom;
    private String prenom;
    private String mail;
    private String username;
}
