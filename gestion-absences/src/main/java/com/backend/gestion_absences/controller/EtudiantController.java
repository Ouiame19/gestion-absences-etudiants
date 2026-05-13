package com.backend.gestion_absences.controller;

import com.backend.gestion_absences.model.Etudiant;
import com.backend.gestion_absences.service.EtudiantService;
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
@RequestMapping("/api/etudiants")
@CrossOrigin(origins = "http://localhost:3000")
@RequiredArgsConstructor
public class EtudiantController {
    
    private final EtudiantService etudiantService;
    
    @GetMapping
    public ResponseEntity<Page<Etudiant>> obtenirTousLesEtudiants(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int taille) {
        return ResponseEntity.ok(etudiantService.obtenirTousLesEtudiants(PageRequest.of(page, taille)));
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Etudiant> obtenirEtudiantParId(@PathVariable Long id) {
        try {
            Optional<Etudiant> etudiant = etudiantService.obtenirEtudiantParId(id);
            return etudiant.map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    
    @PostMapping
    public ResponseEntity<Etudiant> creerEtudiant(@Valid @RequestBody Etudiant etudiant) {
        try {
            Etudiant nouvelEtudiant = etudiantService.creerEtudiant(etudiant);
            return ResponseEntity.status(HttpStatus.CREATED).body(nouvelEtudiant);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Etudiant> modifierEtudiant(@PathVariable Long id, @Valid @RequestBody Etudiant etudiantDetails) {
        try {
            Etudiant etudiantModifie = etudiantService.modifierEtudiant(id, etudiantDetails);
            return ResponseEntity.ok(etudiantModifie);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> supprimerEtudiant(@PathVariable Long id) {
        try {
            etudiantService.supprimerEtudiant(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
