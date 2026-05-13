package com.backend.gestion_absences.service;

import com.backend.gestion_absences.model.Matiere;
import com.backend.gestion_absences.repository.MatiereRepository;
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
public class MatiereService {
    
    private static final Logger logger = LoggerFactory.getLogger(MatiereService.class);
    
    private final MatiereRepository matiereRepository;
    
    public Page<Matiere> obtenirToutesLesMatieres(Pageable pageable) {
        logger.info("Récupération de toutes les matières avec pagination");
        return matiereRepository.findAll(pageable);
    }
    
    public Optional<Matiere> obtenirMatiereParId(Long id) {
        logger.info("Récupération de la matière avec l'ID : {}", id);
        return matiereRepository.findById(id);
    }
    
    public Matiere creerMatiere(Matiere matiere) {
        logger.info("Création d'une nouvelle matière : {}", matiere.getNom());
        return matiereRepository.save(matiere);
    }
    
    public Matiere modifierMatiere(Long id, Matiere matiereDetails) {
        logger.info("Modification de la matière avec l'ID : {}", id);
        return matiereRepository.findById(id)
            .map(matiere -> {
                matiere.setNom(matiereDetails.getNom());
                matiere.setCode(matiereDetails.getCode());
                matiere.setEnseignant(matiereDetails.getEnseignant());
                return matiereRepository.save(matiere);
            })
            .orElseThrow(() -> new RuntimeException("Matière non trouvée avec l'ID: " + id));
    }
    
    public void supprimerMatiere(Long id) {
        logger.info("Suppression de la matière avec l'ID : {}", id);
        if (!matiereRepository.existsById(id)) {
            throw new RuntimeException("Matière non trouvée avec l'ID: " + id);
        }
        matiereRepository.deleteById(id);
    }
}
