package com.monkilattech.madeinrdc.config;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.monkilattech.madeinrdc.models.ERole;
import com.monkilattech.madeinrdc.models.Role;
import com.monkilattech.madeinrdc.models.User;
import com.monkilattech.madeinrdc.repository.RoleRepository;
import com.monkilattech.madeinrdc.repository.UserRepository;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) {
        insererRoleSiNonExistant(ERole.ROLE_ADMIN);
        insererRoleSiNonExistant(ERole.ROLE_BUYER);
        insererRoleSiNonExistant(ERole.ROLE_SELLER);
        insererRoleSiNonExistant(ERole.ROLE_USER);

        insererAdminParDefaut();
        
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

    private void insererAdminParDefaut() {
        String username = "monkila";
        String email = "jnkiwa25@gmail.com";

        if (userRepository.findByUsername(username).isEmpty()) {
            User admin = new User();
            admin.setUsername(username);
            admin.setEmail(email);
            admin.setPassword(passwordEncoder.encode("405522")); 

            Role roleAdmin = roleRepository.findByName(ERole.ROLE_ADMIN)
                    .orElseThrow(() -> new RuntimeException("ROLE_ADMIN non trouvé"));

            admin.getRoles().add(roleAdmin);
            userRepository.save(admin);

            System.out.println("Utilisateur admin créé avec succès !");
        } else {
            System.out.println("Admin déjà existant.");
        }
    }
}
