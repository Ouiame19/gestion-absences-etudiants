package com.backend.gestion_absences.service;

import com.backend.gestion_absences.model.Etudiant;
import com.backend.gestion_absences.repository.EtudiantRepository;
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
public class EtudiantService {
    
    private static final Logger logger = LoggerFactory.getLogger(EtudiantService.class);
    
    private final EtudiantRepository etudiantRepository;
    
    public Page<Etudiant> obtenirTousLesEtudiants(Pageable pageable) {
        logger.info("Récupération de tous les étudiants avec pagination");
        return etudiantRepository.findAll(pageable);
    }
    
    public Optional<Etudiant> obtenirEtudiantParId(Long id) {
        logger.info("Récupération de l'étudiant avec l'ID : {}", id);
        return etudiantRepository.findById(id);
    }
    
    public Etudiant creerEtudiant(Etudiant etudiant) {
        logger.info("Création d'un nouvel étudiant : {} {}", etudiant.getNom(), etudiant.getPrenom());
        return etudiantRepository.save(etudiant);
    }
    
    public Etudiant modifierEtudiant(Long id, Etudiant etudiantDetails) {
        logger.info("Modification de l'étudiant avec l'ID : {}", id);
        return etudiantRepository.findById(id)
            .map(etudiant -> {
                etudiant.setNom(etudiantDetails.getNom());
                etudiant.setPrenom(etudiantDetails.getPrenom());
                etudiant.setEmail(etudiantDetails.getEmail());
                etudiant.setCne(etudiantDetails.getCne());
                etudiant.setFiliere(etudiantDetails.getFiliere());
                etudiant.setUtilisateur(etudiantDetails.getUtilisateur());
                return etudiantRepository.save(etudiant);
            })
            .orElseThrow(() -> new RuntimeException("Étudiant non trouvé avec l'ID: " + id));
    }
    
    public void supprimerEtudiant(Long id) {
        logger.info("Suppression de l'étudiant avec l'ID : {}", id);
        if (!etudiantRepository.existsById(id)) {
            throw new RuntimeException("Étudiant non trouvé avec l'ID: " + id);
        }
        etudiantRepository.deleteById(id);
    }
}
