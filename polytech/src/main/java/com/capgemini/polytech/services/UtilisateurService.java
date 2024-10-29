package com.capgemini.polytech.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.polytech.dto.ReservationDto;
import com.capgemini.polytech.dto.UtilisateurCreateDto;
import com.capgemini.polytech.dto.UtilisateurDto;
import com.capgemini.polytech.dto.UtilisateurReservationResponseDto;
import com.capgemini.polytech.mappers.UtilisateurMapper;
import com.capgemini.polytech.models.Utilisateur;
import com.capgemini.polytech.repositories.UtilisateurRepository;

@Service
public class UtilisateurService {

    @Autowired
    private UtilisateurRepository utilisateurRepository;

    @Autowired
    private UtilisateurMapper utilisateurMapper;

    public UtilisateurDto getUtilisateurById(Integer id) {
        return utilisateurMapper.toDTO(utilisateurRepository.getReferenceById(id));
    }

    public List<UtilisateurDto> getAllUtilisateurs() {
        return utilisateurMapper.toDTO(utilisateurRepository.findAll());
    }

    public UtilisateurDto createUtilisateur(UtilisateurCreateDto utilisateurcreated) {
        Utilisateur utilisateur = utilisateurMapper.toEntity(utilisateurcreated);
        Utilisateur savedUtilisateur = utilisateurRepository.save(utilisateur);
        return utilisateurMapper.toDTO(savedUtilisateur);
    }

    public UtilisateurDto updateUtilisateur(UtilisateurDto utilisateurDTO) {
        Utilisateur existingUtilisateur = utilisateurRepository
                .getReferenceById(utilisateurMapper.toEntity(utilisateurDTO).getId());
        existingUtilisateur.setNom(utilisateurMapper.toEntity(utilisateurDTO).getNom());
        existingUtilisateur.setPrenom(utilisateurMapper.toEntity(utilisateurDTO).getPrenom());
        existingUtilisateur.setMail(utilisateurMapper.toEntity(utilisateurDTO).getMail());
        existingUtilisateur.setUsername(utilisateurMapper.toEntity(utilisateurDTO).getUsername());
        Utilisateur savedUtilisateur = utilisateurRepository.save(existingUtilisateur);
        return utilisateurMapper.toDTO(savedUtilisateur);
    }

    public UtilisateurDto updateUtilisateurPassword(Integer id, String password) {
        Utilisateur existingUtilisateur = utilisateurRepository.getReferenceById(id);
        existingUtilisateur.setPassword(password);
        Utilisateur savedUtilisateur = utilisateurRepository.save(existingUtilisateur);
        return utilisateurMapper.toDTO(savedUtilisateur);
    }

    public void deleteUtilisateur(Integer id) {
        Utilisateur existingUtilisateur = utilisateurRepository.getReferenceById(id);
        utilisateurRepository.delete(existingUtilisateur);
    }

    public UtilisateurReservationResponseDto createUtilisateurReservationResponse(UtilisateurDto utilisateur,
            List<ReservationDto> reservationDTOs) {
        UtilisateurReservationResponseDto utilisateurReservations = new UtilisateurReservationResponseDto(utilisateur,
                reservationDTOs);
        return utilisateurReservations;
    }

}
