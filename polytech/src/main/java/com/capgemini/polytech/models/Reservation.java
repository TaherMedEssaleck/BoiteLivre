package com.capgemini.polytech.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;


@Entity
@Table(name = "reservation")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Reservation {

    @EmbeddedId
    private ReservationId id;

    @ManyToOne
    @MapsId("utilisateurId")
    @JoinColumn(name = "utilisateur_id", referencedColumnName = "id")
    private Utilisateur utilisateur;

    @ManyToOne
    @MapsId("boiteId")
    @JoinColumn(name = "boite_id", referencedColumnName = "id")
    private Boite boite;

    @Column(nullable = false)
    private int reservation;
}
