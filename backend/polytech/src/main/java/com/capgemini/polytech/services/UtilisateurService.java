package com.capgemini.polytech.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

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
        return utilisateurRepository.findById(id)
                .map(utilisateurMapper::toDTO)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Utilisateur not found"));
    }

    public List<UtilisateurDto> getAllUtilisateurs() {
        return utilisateurMapper.toDTO(utilisateurRepository.findAll());
    }

    public UtilisateurDto createUtilisateur(UtilisateurCreateDto utilisateurcreated) {

        if (utilisateurRepository.existsById(utilisateurcreated.getId())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Utilisateur already exists");
        }
        try {
            Utilisateur utilisateur = utilisateurMapper.toEntity(utilisateurcreated);
            Utilisateur savedUtilisateur = utilisateurRepository.save(utilisateur);
            return utilisateurMapper.toDTO(savedUtilisateur);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to create utilisateur", e);
        }
    }

    public UtilisateurDto updateUtilisateur(UtilisateurDto utilisateurDTO) {
        Utilisateur utilisateur = utilisateurMapper.toEntity(utilisateurDTO);
        return utilisateurRepository.findById(utilisateur.getId())
                .map(existingUtilisateur -> {
                    existingUtilisateur.setNom(utilisateur.getNom());
                    existingUtilisateur.setPrenom(utilisateur.getPrenom());
                    existingUtilisateur.setMail(utilisateur.getMail());
                    existingUtilisateur.setUsername(utilisateur.getUsername());
                    existingUtilisateur.setRole(utilisateur.getRole());
                    Utilisateur savedUtilisateur = utilisateurRepository.save(existingUtilisateur);
                    return utilisateurMapper.toDTO(savedUtilisateur);
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Utilisateur not found"));
    }

    public UtilisateurDto updateUtilisateurPassword(Integer id, String password) {
        return utilisateurRepository.findById(id)
                .map(existingUtilisateur -> {
                    existingUtilisateur.setPassword(password);
                    Utilisateur savedUtilisateur = utilisateurRepository.save(existingUtilisateur);
                    return utilisateurMapper.toDTO(savedUtilisateur);
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Utilisateur not found"));
    }

    public void deleteUtilisateur(Integer id) {
        if (!utilisateurRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Utilisateur not found");
        }
        utilisateurRepository.deleteById(id);
    }

    public UtilisateurReservationResponseDto createUtilisateurReservationResponse(UtilisateurDto utilisateur,
            List<ReservationDto> reservationDTOs) {
        return new UtilisateurReservationResponseDto(utilisateur, reservationDTOs);
    }

}
