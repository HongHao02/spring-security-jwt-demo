package com.boarding.springsecurityjwt.Repositories;

import com.boarding.springsecurityjwt.Models.Roles;
import com.boarding.springsecurityjwt.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);
    User findByRoles(Roles role);
}
