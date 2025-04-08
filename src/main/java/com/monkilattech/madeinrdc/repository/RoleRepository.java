package com.monkilattech.madeinrdc.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.monkilattech.madeinrdc.models.ERole;
import com.monkilattech.madeinrdc.models.Role;

public interface RoleRepository extends JpaRepository<Role, UUID> {
    Optional<Role> findByName(ERole name);
}
