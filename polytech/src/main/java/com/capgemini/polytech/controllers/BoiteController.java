package com.capgemini.polytech.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.capgemini.polytech.dto.BoiteDto;
import com.capgemini.polytech.services.BoiteService;


import lombok.AllArgsConstructor;


@AllArgsConstructor
@RestController
@RequestMapping("/api/boites/")
public class BoiteController {

    @Autowired
    private BoiteService boiteService;


    @GetMapping("{id}")
    public ResponseEntity<BoiteDto> getBoiteById(@PathVariable Integer id) {
        BoiteDto boite= boiteService.getBoiteById(id);
        return ResponseEntity.ok(boite);
    }

     @GetMapping("all")
    public ResponseEntity<List<BoiteDto>> getAllUtilisateurs() {
        List<BoiteDto> utilisateurs = boiteService.getAllBoites();
        return ResponseEntity.ok(utilisateurs);
    }

    
    @PostMapping("create")
    public ResponseEntity<BoiteDto> createBoite(@RequestBody BoiteDto boiteDTO) {
        BoiteDto createdBoite = boiteService.createBoite(boiteDTO);
        return ResponseEntity.ok(createdBoite);
    }

    @PutMapping("update")
    public ResponseEntity<BoiteDto> updateUtilisateur(@RequestBody BoiteDto boiteDTO) {
        BoiteDto updatedBoite = boiteService.updateBoite(boiteDTO);
        return ResponseEntity.ok(updatedBoite);
    }

     @DeleteMapping("delete/{id}")
    public ResponseEntity<Void> deleteUtilisateur(@PathVariable Integer id) {
        boiteService.deleteBoite(id);
        return ResponseEntity.noContent().build();
    }
}