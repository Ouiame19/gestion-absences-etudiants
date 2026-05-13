package com.backend.gestion_absences.repository;

import com.backend.gestion_absences.model.Matiere;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MatiereRepository extends JpaRepository<Matiere, Long> {
}
