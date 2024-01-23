package com.boarding.springsecurityjwt;

import com.boarding.springsecurityjwt.Models.Roles;
import com.boarding.springsecurityjwt.Models.User;
import com.boarding.springsecurityjwt.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    //simulate adminAccount
    @Override
    public void run(String... args) throws Exception {
        User admindAccount = userRepository.findByRoles(Roles.ADMIN);

        if (admindAccount == null) {

            User user = new User();
            user.setUsername("admin@gmail.com");
            user.setLastName("AdminAccount");
            user.setRoles(Roles.ADMIN);
            user.setPassword(new BCryptPasswordEncoder().encode("admin@123"));

            userRepository.save(user);
        }


    }
}
