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

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

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

    public ReservationDto getReservationById(ReservationId reservationId) {
    return reservationRepository.findById(reservationId)
            .map(reservationMapper::toDTO)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Reservation not found"));
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

        ReservationId reservationId = new ReservationId();
        reservationId.setBoiteId(reservationDTO.getBoite().getId());
        reservationId.setUtilisateurId(reservationDTO.getUtilisateur().getId());
    
        if (reservationRepository.existsById(reservationId)) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Reservation already exists");
        }
    
        // Fetch Utilisateur and Boite from DB, handle case where either may be missing
        Utilisateur utilisateur = utilisateurRepository.findById(reservationId.getUtilisateurId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Utilisateur not found"));
        Boite boite = boiteRepository.findById(reservationId.getBoiteId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Boite not found"));
    
        Reservation reservation = reservationMapper.toEntity(reservationDTO);
        reservation.setId(reservationId);
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
