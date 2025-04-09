package com.monkilattech.madeinrdc.config;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.monkilattech.madeinrdc.models.ERole;
import com.monkilattech.madeinrdc.models.Role;
import com.monkilattech.madeinrdc.repository.RoleRepository;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public void run(String... args) {
        insererRoleSiNonExistant(ERole.ROLE_ADMIN);
        insererRoleSiNonExistant(ERole.ROLE_BUYER);
        insererRoleSiNonExistant(ERole.ROLE_SELLER);
        insererRoleSiNonExistant(ERole.ROLE_USER);
        
    }

    private void insererRoleSiNonExistant(ERole nom) {
        Optional<Role> roleExistant = roleRepository.findByName(nom);
        if (roleExistant.isEmpty()) {
            Role role = new Role();
            role.setName(nom);
            roleRepository.save(role);
            System.out.println("Role inséré : " + nom);
        } else {
            System.out.println("Role déjà existant : " + nom);
        }
    }
}
