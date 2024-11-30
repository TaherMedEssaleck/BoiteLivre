package com.capgemini.polytech.mappers;

import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.capgemini.polytech.dto.UtilisateurCreateDto;
import com.capgemini.polytech.dto.UtilisateurDto;
import com.capgemini.polytech.models.Utilisateur;

@Mapper(componentModel = "spring")
public interface UtilisateurMapper {

        
        UtilisateurDto toDTO(Utilisateur Utilisateur);
        List<UtilisateurDto> toDTO(List<Utilisateur> Utilisateurs);

        @Mapping(target = "password", ignore = true)
        Utilisateur toEntity(UtilisateurDto Utilisateur);
        
        UtilisateurDto toDTO(UtilisateurCreateDto UtilisateurDTO);
        
        Utilisateur toEntity(UtilisateurCreateDto Utilisateur);
        

 
        
    
} 