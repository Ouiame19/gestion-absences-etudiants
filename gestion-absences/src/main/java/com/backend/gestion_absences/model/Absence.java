package com.backend.gestion_absences.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "absences")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Absence {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "etudiant_id", nullable = false)
    private Etudiant etudiant;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "matiere_id", nullable = false)
    private Matiere matiere;
    
    @Column(nullable = false)
    private LocalDate dateAbsence;
    
    @Column(nullable = false)
    @Builder.Default
    private Boolean justifiee = false;
    
    private String motif;
}
