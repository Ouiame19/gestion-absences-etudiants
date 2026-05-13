package com.backend.gestion_absences.controller;

import com.backend.gestion_absences.model.Enseignant;
import com.backend.gestion_absences.service.EnseignantService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/enseignants")
@CrossOrigin(origins = "http://localhost:3000")
@RequiredArgsConstructor
public class EnseignantController {
    
    private final EnseignantService enseignantService;
    
    @GetMapping
    public ResponseEntity<Page<Enseignant>> obtenirTousLesEnseignants(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int taille) {
        return ResponseEntity.ok(enseignantService.obtenirTousLesEnseignants(PageRequest.of(page, taille)));
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Enseignant> obtenirEnseignantParId(@PathVariable Long id) {
        try {
            Optional<Enseignant> enseignant = enseignantService.obtenirEnseignantParId(id);
            return enseignant.map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    
    @PostMapping
    public ResponseEntity<Enseignant> creerEnseignant(@Valid @RequestBody Enseignant enseignant) {
        try {
            Enseignant nouvelEnseignant = enseignantService.creerEnseignant(enseignant);
            return ResponseEntity.status(HttpStatus.CREATED).body(nouvelEnseignant);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Enseignant> modifierEnseignant(@PathVariable Long id, @Valid @RequestBody Enseignant enseignantDetails) {
        try {
            Enseignant enseignantModifie = enseignantService.modifierEnseignant(id, enseignantDetails);
            return ResponseEntity.ok(enseignantModifie);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> supprimerEnseignant(@PathVariable Long id) {
        try {
            enseignantService.supprimerEnseignant(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
