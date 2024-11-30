package com.capgemini.polytech.services;

import com.capgemini.polytech.models.Boite;
import com.capgemini.polytech.models.Reservation;
import com.capgemini.polytech.models.ReservationId;
import com.capgemini.polytech.models.Utilisateur;

import com.capgemini.polytech.dto.ReservationDto;
import com.capgemini.polytech.dto.ReservationIdsDto;

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
        return reservationMapper.toDTO(reservationRepository.findAll());
    }

    public List<ReservationDto> getAllReservationsByUser(Integer userId) {
        if (!utilisateurRepository.existsById(userId)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Utilisateur not found");
        }
        return reservationRepository.findAllByUtilisateurId(userId)
                .stream()
                .map(reservationMapper::toDTO)
                .collect(Collectors.toList());
    }
    
    public ReservationDto getReservationById(ReservationId reservationId) {
        return reservationRepository.findById(reservationId)
                .map(reservationMapper::toDTO)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Reservation not found"));
    }

    public ReservationDto convertReservationIdsDTOtoReservationDTO(ReservationIdsDto reservationIdsDTO) {
        Utilisateur utilisateur = utilisateurRepository.findById(reservationIdsDTO.getUtilisateurId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Utilisateur not found"));
        Boite boite = boiteRepository.findById(reservationIdsDTO.getBoiteId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Boite not found"));

        ReservationDto reservationDTO = new ReservationDto();
        reservationDTO.setUtilisateur(utilisateurMapper.toDTO(utilisateur));
        reservationDTO.setBoite(boiteMapper.toDTO(boite));
        reservationDTO.setReservation(reservationIdsDTO.getReservation());
        return reservationDTO;
    }

    public ReservationDto createReservation(ReservationDto reservationDTO) {

        ReservationId reservationId = new ReservationId();
        reservationId.setBoiteId(reservationDTO.getBoite().getId());
        reservationId.setUtilisateurId(reservationDTO.getUtilisateur().getId());

        if (reservationRepository.existsById(reservationId)) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Reservation already exists");
        }

        Utilisateur utilisateur = utilisateurRepository.findById(reservationId.getUtilisateurId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Utilisateur not found"));
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
        return reservationRepository.findById(id)
                .map(existingReservation -> {
                    existingReservation.setReservation(reservationDTO.getReservation());
                    Reservation updatedReservation = reservationRepository.save(existingReservation);
                    return reservationMapper.toDTO(updatedReservation);
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Reservation not found"));
    }

    public void deleteReservation(ReservationId id) {
        if (!reservationRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Reservation not found");
        }
        reservationRepository.deleteById(id);
    }
}