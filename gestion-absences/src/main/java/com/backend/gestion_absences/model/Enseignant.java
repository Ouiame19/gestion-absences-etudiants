package com.backend.gestion_absences.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "enseignants")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Enseignant {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String nom;
    
    @Column(nullable = false)
    private String prenom;
    
    @Column(nullable = false, unique = true)
    private String email;
    
    @Column(nullable = false)
    private String specialite;
    
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "utilisateur_id", nullable = false)
    private Utilisateur utilisateur;
}
