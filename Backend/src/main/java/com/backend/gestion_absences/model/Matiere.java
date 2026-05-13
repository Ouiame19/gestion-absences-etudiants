package com.backend.gestion_absences.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "matieres")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Matiere {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String nom;
    
    @Column(nullable = false, unique = true)
    private String code;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "enseignant_id", nullable = false)
    private Enseignant enseignant;
}
