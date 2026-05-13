package com.backend.gestion_absences.controller;

import com.backend.gestion_absences.model.Role;
import com.backend.gestion_absences.service.RoleService;
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
@RequestMapping("/api/roles")
@CrossOrigin(origins = "http://localhost:3000")
@RequiredArgsConstructor
public class RoleController {
    
    private final RoleService roleService;
    
    @GetMapping
    public ResponseEntity<Page<Role>> obtenirTousLesRoles(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int taille) {
        return ResponseEntity.ok(roleService.obtenirTousLesRoles(PageRequest.of(page, taille)));
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Role> obtenirRoleParId(@PathVariable Long id) {
        try {
            Optional<Role> role = roleService.obtenirRoleParId(id);
            return role.map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    
    @PostMapping
    public ResponseEntity<Role> creerRole(@Valid @RequestBody Role role) {
        try {
            Role nouveauRole = roleService.creerRole(role);
            return ResponseEntity.status(HttpStatus.CREATED).body(nouveauRole);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Role> modifierRole(@PathVariable Long id, @Valid @RequestBody Role roleDetails) {
        try {
            Role roleModifie = roleService.modifierRole(id, roleDetails);
            return ResponseEntity.ok(roleModifie);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> supprimerRole(@PathVariable Long id) {
        try {
            roleService.supprimerRole(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
