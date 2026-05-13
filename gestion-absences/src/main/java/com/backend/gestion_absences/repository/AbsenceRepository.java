package com.backend.gestion_absences.repository;

import com.backend.gestion_absences.model.Absence;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface AbsenceRepository extends JpaRepository<Absence, Long> {
    
    List<Absence> findByEtudiantId(Long etudiantId);
    
    List<Absence> findByMatiereId(Long matiereId);
    
    List<Absence> findByDateAbsence(LocalDate date);
    
    List<Absence> findByJustifiee(boolean justifiee);
}
