package com.backend.gestion_absences.service;

import com.backend.gestion_absences.model.Absence;
import com.backend.gestion_absences.repository.AbsenceRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AbsenceService {
    
    private static final Logger logger = LoggerFactory.getLogger(AbsenceService.class);
    
    private final AbsenceRepository absenceRepository;
    
    public Page<Absence> obtenirToutesLesAbsences(Pageable pageable) {
        logger.info("Récupération de toutes les absences avec pagination");
        return absenceRepository.findAll(pageable);
    }
    
    public Optional<Absence> obtenirAbsenceParId(Long id) {
        logger.info("Récupération de l'absence avec l'ID : {}", id);
        return absenceRepository.findById(id);
    }
    
    public Absence creerAbsence(Absence absence) {
        logger.info("Création d'une nouvelle absence pour l'étudiant ID : {}", absence.getEtudiant().getId());
        return absenceRepository.save(absence);
    }
    
    public Absence modifierAbsence(Long id, Absence absenceDetails) {
        logger.info("Modification de l'absence avec l'ID : {}", id);
        return absenceRepository.findById(id)
            .map(absence -> {
                absence.setEtudiant(absenceDetails.getEtudiant());
                absence.setMatiere(absenceDetails.getMatiere());
                absence.setDateAbsence(absenceDetails.getDateAbsence());
                absence.setJustifiee(absenceDetails.getJustifiee());
                absence.setMotif(absenceDetails.getMotif());
                return absenceRepository.save(absence);
            })
            .orElseThrow(() -> new RuntimeException("Absence non trouvée avec l'ID: " + id));
    }
    
    public void supprimerAbsence(Long id) {
        logger.info("Suppression de l'absence avec l'ID : {}", id);
        if (!absenceRepository.existsById(id)) {
            throw new RuntimeException("Absence non trouvée avec l'ID: " + id);
        }
        absenceRepository.deleteById(id);
    }
    
    public List<Absence> obtenirAbsencesParEtudiant(Long etudiantId) {
        logger.info("Récupération des absences pour l'étudiant ID : {}", etudiantId);
        return absenceRepository.findByEtudiantId(etudiantId);
    }
    
    public List<Absence> obtenirAbsencesParMatiere(Long matiereId) {
        logger.info("Récupération des absences pour la matière ID : {}", matiereId);
        return absenceRepository.findByMatiereId(matiereId);
    }
    
    public List<Absence> obtenirAbsencesParDate(LocalDate date) {
        logger.info("Récupération des absences pour la date : {}", date);
        return absenceRepository.findByDateAbsence(date);
    }
    
    public List<Absence> obtenirAbsencesJustifiees(boolean justifiee) {
        logger.info("Récupération des absences justifiées : {}", justifiee);
        return absenceRepository.findByJustifiee(justifiee);
    }
}
