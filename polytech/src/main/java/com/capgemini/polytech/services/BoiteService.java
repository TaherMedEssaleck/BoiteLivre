package com.capgemini.polytech.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.polytech.dto.BoiteDto;
import com.capgemini.polytech.mappers.BoiteMapper;
import com.capgemini.polytech.models.Boite;
import com.capgemini.polytech.repositories.BoiteRepository;


@Service
public class BoiteService {

    @Autowired
    private BoiteRepository boiteRepository;

    @Autowired
    private BoiteMapper boiteMapper;


    public List<BoiteDto> getAllBoites() {
        return boiteMapper.toDTO(boiteRepository.findAll());
    }


    public BoiteDto getBoiteById(Integer id) {
    
        return boiteMapper.toDTO(boiteRepository.getReferenceById(id));
    }

    public BoiteDto createBoite(BoiteDto boiteDTO) {
        Boite boite = boiteMapper.toEntity(boiteDTO);
        Boite savedBoite = boiteRepository.save(boite);
        return boiteMapper.toDTO(savedBoite);
    }

    public BoiteDto updateBoite(BoiteDto boiteDTO) {
        Boite existingBoite = boiteRepository.getReferenceById(boiteMapper.toEntity(boiteDTO).getId());
        existingBoite.setNom(boiteMapper.toEntity(boiteDTO).getNom());
        existingBoite.setDescription(boiteMapper.toEntity(boiteDTO).getDescription());
        existingBoite.setPointGeo(boiteMapper.toEntity(boiteDTO).getPointGeo());
        existingBoite.setQuantite(boiteMapper.toEntity(boiteDTO).getQuantite());
        Boite savedBoite = boiteRepository.save(existingBoite);
        return boiteMapper.toDTO(savedBoite);
    }

     public void deleteBoite(Integer id) {
        Boite existingBoite = boiteRepository.getReferenceById(id);
        boiteRepository.delete(existingBoite);
    }


}
