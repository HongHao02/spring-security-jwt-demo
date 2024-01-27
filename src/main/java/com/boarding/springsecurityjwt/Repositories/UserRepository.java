package com.boarding.springsecurityjwt.Repositories;

import com.boarding.springsecurityjwt.Models.Roles;
import com.boarding.springsecurityjwt.Models.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

//    @Query("SELECT u FROM nguoidung u JOIN FETCH u.roles WHERE u.username = :username")
//    Optional<User> findByUsername(@Param("username")String username);

    //    @EntityGraph(attributePaths = "roles")
//    @Query("SELECT u FROM User u WHERE u.username = :username")

//    SELECT *
//    FROM nguoidung nd
//    JOIN user_roles r1 ON nd.id = r1.id
//    JOIN roles r0 ON r0.idrole = r1.idrole
//    WHERE nd.username = 'admin@gmail.com';
//    @Query("SELECT u FROM User u JOIN FETCH u.roles WHERE u.username = :username")
    Optional<User> findByUsername(String username);

    User findByRoles(Roles role);
}
