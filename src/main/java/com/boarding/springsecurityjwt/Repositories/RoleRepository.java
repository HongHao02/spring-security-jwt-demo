package com.boarding.springsecurityjwt.Repositories;

import com.boarding.springsecurityjwt.Models.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role,Integer> {
    Optional<Role> findByName(String roleName);
}
