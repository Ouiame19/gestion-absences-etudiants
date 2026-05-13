package com.backend.gestion_absences.service;

import com.backend.gestion_absences.model.Enseignant;
import com.backend.gestion_absences.repository.EnseignantRepository;
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
public class EnseignantService {
    
    private static final Logger logger = LoggerFactory.getLogger(EnseignantService.class);
    
    private final EnseignantRepository enseignantRepository;
    
    public Page<Enseignant> obtenirTousLesEnseignants(Pageable pageable) {
        logger.info("Récupération de tous les enseignants avec pagination");
        return enseignantRepository.findAll(pageable);
    }
    
    public Optional<Enseignant> obtenirEnseignantParId(Long id) {
        logger.info("Récupération de l'enseignant avec l'ID : {}", id);
        return enseignantRepository.findById(id);
    }
    
    public Enseignant creerEnseignant(Enseignant enseignant) {
        logger.info("Création d'un nouvel enseignant : {} {}", enseignant.getNom(), enseignant.getPrenom());
        return enseignantRepository.save(enseignant);
    }
    
    public Enseignant modifierEnseignant(Long id, Enseignant enseignantDetails) {
        logger.info("Modification de l'enseignant avec l'ID : {}", id);
        return enseignantRepository.findById(id)
            .map(enseignant -> {
                enseignant.setNom(enseignantDetails.getNom());
                enseignant.setPrenom(enseignantDetails.getPrenom());
                enseignant.setEmail(enseignantDetails.getEmail());
                enseignant.setSpecialite(enseignantDetails.getSpecialite());
                enseignant.setUtilisateur(enseignantDetails.getUtilisateur());
                return enseignantRepository.save(enseignant);
            })
            .orElseThrow(() -> new RuntimeException("Enseignant non trouvé avec l'ID: " + id));
    }
    
    public void supprimerEnseignant(Long id) {
        logger.info("Suppression de l'enseignant avec l'ID : {}", id);
        if (!enseignantRepository.existsById(id)) {
            throw new RuntimeException("Enseignant non trouvé avec l'ID: " + id);
        }
        enseignantRepository.deleteById(id);
    }
}
