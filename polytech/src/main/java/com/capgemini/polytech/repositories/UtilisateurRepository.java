package com.capgemini.polytech.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.capgemini.polytech.models.Utilisateur;

@Repository
public interface UtilisateurRepository extends JpaRepository <Utilisateur,Integer>{
    
}