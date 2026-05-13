package com.backend.gestion_absences.controller;

import com.backend.gestion_absences.model.Absence;
import com.backend.gestion_absences.service.AbsenceService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/absences")
@CrossOrigin(origins = "http://localhost:3000")
@RequiredArgsConstructor
public class AbsenceController {
    
    private final AbsenceService absenceService;
    
    @GetMapping
    public ResponseEntity<Page<Absence>> obtenirToutesLesAbsences(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int taille) {
        return ResponseEntity.ok(absenceService.obtenirToutesLesAbsences(PageRequest.of(page, taille)));
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Absence> obtenirAbsenceParId(@PathVariable Long id) {
        try {
            Optional<Absence> absence = absenceService.obtenirAbsenceParId(id);
            return absence.map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    
    @PostMapping
    public ResponseEntity<Absence> creerAbsence(@Valid @RequestBody Absence absence) {
        try {
            Absence nouvelleAbsence = absenceService.creerAbsence(absence);
            return ResponseEntity.status(HttpStatus.CREATED).body(nouvelleAbsence);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Absence> modifierAbsence(@PathVariable Long id, @Valid @RequestBody Absence absenceDetails) {
        try {
            Absence absenceModifiee = absenceService.modifierAbsence(id, absenceDetails);
            return ResponseEntity.ok(absenceModifiee);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> supprimerAbsence(@PathVariable Long id) {
        try {
            absenceService.supprimerAbsence(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    
    @GetMapping("/etudiant/{etudiantId}")
    public ResponseEntity<List<Absence>> obtenirAbsencesParEtudiant(@PathVariable Long etudiantId) {
        try {
            List<Absence> absences = absenceService.obtenirAbsencesParEtudiant(etudiantId);
            return ResponseEntity.ok(absences);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    
    @GetMapping("/justifiees")
    public ResponseEntity<List<Absence>> obtenirAbsencesJustifiees(@RequestParam boolean justifiee) {
        try {
            List<Absence> absences = absenceService.obtenirAbsencesJustifiees(justifiee);
            return ResponseEntity.ok(absences);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
