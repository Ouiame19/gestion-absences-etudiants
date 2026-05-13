package com.backend.gestion_absences.controller;

import com.backend.gestion_absences.model.Matiere;
import com.backend.gestion_absences.service.MatiereService;
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
@RequestMapping("/api/matieres")
@CrossOrigin(origins = "http://localhost:3000")
@RequiredArgsConstructor
public class MatiereController {
    
    private final MatiereService matiereService;
    
    @GetMapping
    public ResponseEntity<Page<Matiere>> obtenirToutesLesMatieres(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int taille) {
        return ResponseEntity.ok(matiereService.obtenirToutesLesMatieres(PageRequest.of(page, taille)));
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Matiere> obtenirMatiereParId(@PathVariable Long id) {
        try {
            Optional<Matiere> matiere = matiereService.obtenirMatiereParId(id);
            return matiere.map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    
    @PostMapping
    public ResponseEntity<Matiere> creerMatiere(@Valid @RequestBody Matiere matiere) {
        try {
            Matiere nouvelleMatiere = matiereService.creerMatiere(matiere);
            return ResponseEntity.status(HttpStatus.CREATED).body(nouvelleMatiere);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Matiere> modifierMatiere(@PathVariable Long id, @Valid @RequestBody Matiere matiereDetails) {
        try {
            Matiere matiereModifiee = matiereService.modifierMatiere(id, matiereDetails);
            return ResponseEntity.ok(matiereModifiee);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> supprimerMatiere(@PathVariable Long id) {
        try {
            matiereService.supprimerMatiere(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
