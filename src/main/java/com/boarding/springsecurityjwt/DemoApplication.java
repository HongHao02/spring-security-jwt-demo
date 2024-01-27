package com.boarding.springsecurityjwt;

import com.boarding.springsecurityjwt.Models.Role;
import com.boarding.springsecurityjwt.Models.Roles;
import com.boarding.springsecurityjwt.Models.User;
import com.boarding.springsecurityjwt.Repositories.RoleRepository;
import com.boarding.springsecurityjwt.Repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.client.HttpClientErrorException;

import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    //simulate adminAccount
    @Override
    @Transactional
    public void run(String... args) throws Exception {
        System.out.println("~~~~~~~> run method");
//        Role role= roleRepository.findByName("USER").orElseThrow(()-> new IllegalArgumentException("Not found"));
//        Set<Role> initRoles= new HashSet<>();
//        initRoles.add(role);
//
//
//
//        if (admindAccount == null) {
//
//            User user = new User();
//            user.setUsername("admin@gmail.com");
//            user.setLastName("AdminAccount");
//            user.setRoles(Roles.ADMIN);
//            user.setPassword(new BCryptPasswordEncoder().encode("admin@123"));
//
//            userRepository.save(user);
//        }
    }
}
