package com.capgemini.polytech.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.util.List;



@Data
@AllArgsConstructor
public class UtilisateurReservationResponseDto {
    private Integer id;
    private String nom;
    private String prenom;
    private String mail;
    private String username;
    private List<ReservationDto> reservations;

    public UtilisateurReservationResponseDto (UtilisateurDto utilisateurDTO, List<ReservationDto> reservationDTOs){
            super();
            this.id=utilisateurDTO.getId();
            this.nom=utilisateurDTO.getNom();
            this.prenom=utilisateurDTO.getPrenom();
            this.mail=utilisateurDTO.getMail();
            this.username=utilisateurDTO.getUsername();
            this.reservations=reservationDTOs;
    }
}

