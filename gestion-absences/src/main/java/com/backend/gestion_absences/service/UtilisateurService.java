package com.backend.gestion_absences.service;

import com.backend.gestion_absences.model.Utilisateur;
import com.backend.gestion_absences.repository.UtilisateurRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UtilisateurService {
    
    private static final Logger logger = LoggerFactory.getLogger(UtilisateurService.class);
    
    private final UtilisateurRepository utilisateurRepository;
    
    public Page<Utilisateur> obtenirTousLesUtilisateurs(Pageable pageable) {
        logger.info("Récupération de tous les utilisateurs avec pagination");
        return utilisateurRepository.findAll(pageable);
    }
    
    public Optional<Utilisateur> obtenirUtilisateurParId(Long id) {
        logger.info("Récupération de l'utilisateur avec l'ID : {}", id);
        return utilisateurRepository.findById(id);
    }
    
    public Utilisateur creerUtilisateur(Utilisateur utilisateur) {
        logger.info("Création d'un nouvel utilisateur : {}", utilisateur.getNomUtilisateur());
        return utilisateurRepository.save(utilisateur);
    }
    
    public Utilisateur modifierUtilisateur(Long id, Utilisateur utilisateurDetails) {
        logger.info("Modification de l'utilisateur avec l'ID : {}", id);
        return utilisateurRepository.findById(id)
            .map(utilisateur -> {
                utilisateur.setNomUtilisateur(utilisateurDetails.getNomUtilisateur());
                utilisateur.setMotDePasse(utilisateurDetails.getMotDePasse());
                utilisateur.setEmail(utilisateurDetails.getEmail());
                utilisateur.setRole(utilisateurDetails.getRole());
                return utilisateurRepository.save(utilisateur);
            })
            .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé avec l'ID: " + id));
    }
    
    public void supprimerUtilisateur(Long id) {
        logger.info("Suppression de l'utilisateur avec l'ID : {}", id);
        if (!utilisateurRepository.existsById(id)) {
            throw new RuntimeException("Utilisateur non trouvé avec l'ID: " + id);
        }
        utilisateurRepository.deleteById(id);
    }
}
