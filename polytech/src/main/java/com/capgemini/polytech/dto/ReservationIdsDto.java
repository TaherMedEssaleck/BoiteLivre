package com.capgemini.polytech.dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReservationIdsDto {


    @Column(name="utilisateur_id")
    private int utilisateurId;
    @Column(name="boite_id")
    private int boiteId;
    @Column(nullable = false, name = "reservation")
    private int reservation;
}
