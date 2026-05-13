package com.backend.gestion_absences.controller;

import com.backend.gestion_absences.model.Utilisateur;
import com.backend.gestion_absences.service.UtilisateurService;
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
@RequestMapping("/api/utilisateurs")
@CrossOrigin(origins = "http://localhost:3000")
@RequiredArgsConstructor
public class UtilisateurController {
    
    private final UtilisateurService utilisateurService;
    
    @GetMapping
    public ResponseEntity<Page<Utilisateur>> obtenirTousLesUtilisateurs(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int taille) {
        return ResponseEntity.ok(utilisateurService.obtenirTousLesUtilisateurs(PageRequest.of(page, taille)));
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Utilisateur> obtenirUtilisateurParId(@PathVariable Long id) {
        try {
            Optional<Utilisateur> utilisateur = utilisateurService.obtenirUtilisateurParId(id);
            return utilisateur.map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    
    @PostMapping
    public ResponseEntity<Utilisateur> creerUtilisateur(@Valid @RequestBody Utilisateur utilisateur) {
        try {
            Utilisateur nouvelUtilisateur = utilisateurService.creerUtilisateur(utilisateur);
            return ResponseEntity.status(HttpStatus.CREATED).body(nouvelUtilisateur);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Utilisateur> modifierUtilisateur(@PathVariable Long id, @Valid @RequestBody Utilisateur utilisateurDetails) {
        try {
            Utilisateur utilisateurModifie = utilisateurService.modifierUtilisateur(id, utilisateurDetails);
            return ResponseEntity.ok(utilisateurModifie);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> supprimerUtilisateur(@PathVariable Long id) {
        try {
            utilisateurService.supprimerUtilisateur(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
