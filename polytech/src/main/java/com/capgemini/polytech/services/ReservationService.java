package com.capgemini.polytech.services;

import com.capgemini.polytech.models.Boite;
import com.capgemini.polytech.models.Reservation;
import com.capgemini.polytech.models.ReservationId;
import com.capgemini.polytech.models.Utilisateur;
import com.capgemini.polytech.dto.BoiteDto;
import com.capgemini.polytech.dto.ReservationDto;
import com.capgemini.polytech.dto.ReservationIdsDto;
import com.capgemini.polytech.dto.UtilisateurDto;
import com.capgemini.polytech.mappers.BoiteMapper;
import com.capgemini.polytech.mappers.ReservationMapper;
import com.capgemini.polytech.mappers.UtilisateurMapper;
import com.capgemini.polytech.repositories.BoiteRepository;
import com.capgemini.polytech.repositories.ReservationRepository;
import com.capgemini.polytech.repositories.UtilisateurRepository;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private UtilisateurRepository utilisateurRepository;

    @Autowired
    private BoiteRepository boiteRepository;

    @Autowired
    private ReservationMapper reservationMapper;
    @Autowired
    private UtilisateurMapper utilisateurMapper;
    @Autowired
    private BoiteMapper boiteMapper;

    public List<ReservationDto> getAllReservations() {
        List<ReservationDto> reservations = reservationMapper.toDTO(reservationRepository.findAll());
        return reservations;
    }

    public List<ReservationDto> getAllReservationsByUser(Integer userId) {
        // Fetch reservations associated with the given Utilisateur ID
        List<Reservation> reservations = reservationRepository.findAllByUtilisateurId(userId);

        // Map each Reservation entity to a ReservationDTO and return the list
        return reservations.stream()
                .map(reservation -> reservationMapper.toDTO(reservation))
                .collect(Collectors.toList());
    }

    public ReservationDto getResevationById(ReservationId reservationId) {
        ReservationDto reservation = reservationMapper.toDTO(reservationRepository.getReferenceById(reservationId));
        return reservation;
    }

    public ReservationDto getResevationById(int uid, int bid) {
        ReservationId reservationId = new ReservationId();
        reservationId.setBoiteId(bid);
        reservationId.setUtilisateurId(uid);
        return reservationMapper.toDTO(reservationRepository.getReferenceById(reservationId));
    }

    public ReservationDto convertReservationIdsDTOtoReservationDTO(ReservationIdsDto reservationIdsDTO) {
        ReservationDto reservationDTO = new ReservationDto();
        Utilisateur utilisateur = utilisateurRepository.getReferenceById(reservationIdsDTO.getUtilisateurId());
        UtilisateurDto utilisateurDTO = utilisateurMapper.toDTO(utilisateur);
        Boite boite = boiteRepository.getReferenceById(reservationIdsDTO.getBoiteId());
        BoiteDto boiteDTO = boiteMapper.toDTO(boite);
        reservationDTO.setUtilisateur(utilisateurDTO);
        reservationDTO.setBoite(boiteDTO);
        reservationDTO.setReservation(reservationIdsDTO.getReservation());
        return reservationDTO;
    }

    // Création d'une nouvelle réservation
    public ReservationDto createReservation(ReservationDto reservationDTO) {
        // Create the composite key
        ReservationId reservationId = new ReservationId();
        reservationId.setBoiteId(reservationDTO.getBoite().getId());
        reservationId.setUtilisateurId(reservationDTO.getUtilisateur().getId());

        // Map DTO to entity
        Reservation reservation = new Reservation();
        reservation.setId(reservationId); // Set the composite key
        reservation.setReservation(reservationDTO.getReservation()); // Set the reservation/quantity

        // Fetch Utilisateur and Boite from DB if necessary
        Utilisateur utilisateur = utilisateurRepository.findById(reservationId.getUtilisateurId())
                .orElseThrow(() -> new EntityNotFoundException("Utilisateur not found"));
        Boite boite = boiteRepository.findById(reservationId.getBoiteId())
                .orElseThrow(() -> new EntityNotFoundException("Boite not found"));

        reservation.setUtilisateur(utilisateur);
        reservation.setBoite(boite);

        reservationRepository.save(reservation);

        return reservationMapper.toDTO(reservation);
    }

    public ReservationDto updateReservation(ReservationDto reservationDTO) {
        ReservationId id = new ReservationId();
        id.setBoiteId(reservationDTO.getBoite().getId());
        id.setUtilisateurId(reservationDTO.getUtilisateur().getId());

        Reservation existingReservation = reservationRepository.getReferenceById(id);
        existingReservation.setReservation(reservationDTO.getReservation());
        Reservation updatedReservation = reservationRepository.save(existingReservation);
        return reservationMapper.toDTO(updatedReservation);
    }

    public void deleteReservation(ReservationId id) {
        Reservation existingReservation = reservationRepository.getReferenceById(id);
        reservationRepository.delete(existingReservation);
    }

}
