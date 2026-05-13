package com.backend.gestion_absences.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "roles")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Role {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, unique = true)
    private NomRole nom;
    
    public enum NomRole {
        ADMIN,
        ETUDIANT,
        ENSEIGNANT
    }
}
