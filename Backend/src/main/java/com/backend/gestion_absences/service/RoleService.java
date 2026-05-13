package com.backend.gestion_absences.service;

import com.backend.gestion_absences.model.Role;
import com.backend.gestion_absences.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RoleService {
    
    private static final Logger logger = LoggerFactory.getLogger(RoleService.class);
    
    private final RoleRepository roleRepository;
    
    public Page<Role> obtenirTousLesRoles(Pageable pageable) {
        logger.info("Récupération de tous les rôles avec pagination");
        return roleRepository.findAll(pageable);
    }
    
    public Optional<Role> obtenirRoleParId(Long id) {
        logger.info("Récupération du rôle avec l'ID : {}", id);
        return roleRepository.findById(id);
    }
    
    public Role creerRole(Role role) {
        logger.info("Création d'un nouveau rôle : {}", role.getNom());
        return roleRepository.save(role);
    }
    
    public Role modifierRole(Long id, Role roleDetails) {
        logger.info("Modification du rôle avec l'ID : {}", id);
        return roleRepository.findById(id)
            .map(role -> {
                role.setNom(roleDetails.getNom());
                return roleRepository.save(role);
            })
            .orElseThrow(() -> new RuntimeException("Rôle non trouvé avec l'ID: " + id));
    }
    
    public void supprimerRole(Long id) {
        logger.info("Suppression du rôle avec l'ID : {}", id);
        if (!roleRepository.existsById(id)) {
            throw new RuntimeException("Rôle non trouvé avec l'ID: " + id);
        }
        roleRepository.deleteById(id);
    }
}
