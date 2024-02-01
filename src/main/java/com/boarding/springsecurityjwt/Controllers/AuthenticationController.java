package com.boarding.springsecurityjwt.Controllers;

import com.boarding.springsecurityjwt.DTO.JwtAuthencationResponse;
import com.boarding.springsecurityjwt.DTO.RefreshTokenRequest;
import com.boarding.springsecurityjwt.DTO.SignInRequest;
import com.boarding.springsecurityjwt.DTO.SignUpRequest;
import com.boarding.springsecurityjwt.Models.ResponseObject;
import com.boarding.springsecurityjwt.Models.Role;
import com.boarding.springsecurityjwt.Models.User;
import com.boarding.springsecurityjwt.Repositories.RoleRepository;
import com.boarding.springsecurityjwt.Repositories.UserRepository;
import com.boarding.springsecurityjwt.Services.AuthenticationService;
import com.boarding.springsecurityjwt.Services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService authenticationService;
    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<ResponseObject> signUp(@RequestBody SignUpRequest signUpRequest) {
        System.out.println("SignUpRequest " + signUpRequest.toString());
        return ResponseEntity.ok(authenticationService.signUp(signUpRequest));
    }

    @PostMapping("/signup-simple")
    public ResponseEntity<ResponseObject> signUpSimple(@ModelAttribute User user) {
        if (user != null) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        }
        assert user != null;
        return ResponseEntity.ok(new ResponseObject("ok", "create User successfully", userRepository.save(user)));
    }

    @GetMapping("/{idUser}")
    public ResponseEntity<ResponseObject> getUserById(@PathVariable Long idUser) {
        User user= userRepository.findById(idUser).orElse(null);
        assert user != null;
//        user.getRoles().size();
        return ResponseEntity.ok(new ResponseObject("ok", "Get user successfully", userRepository.findById(idUser)));
    }

    @PutMapping("/addRole/{idRole}/{idUser}")
    public ResponseEntity<ResponseObject> addRoleToStudent(@PathVariable Integer idRole, @PathVariable Long idUser) {
        Role role = roleRepository.findById(idRole).orElse(null);
        User user = userRepository.findById(idUser).orElse(null);
        assert user != null;
        user.getRoles().add(role);
        return ResponseEntity.ok(new ResponseObject("ok", "add role successfully", userRepository.save(user)));
    }

    @PostMapping("/signin")
    public ResponseEntity<JwtAuthencationResponse> signIn(@RequestBody SignInRequest signInRequest) {
        try {
            System.out.println("SignUpRequest " + signInRequest.toString());
            return ResponseEntity.ok(authenticationService.signIn(signInRequest));
        } catch (Exception e) {
            throw new RuntimeException();
        }

    }

    @PostMapping("/refresh")
    public ResponseEntity<JwtAuthencationResponse> refresh(@RequestBody RefreshTokenRequest refreshTokenRequest) {
        try {
//            System.out.println("SignUpRequest "+ signInRequest.toString());
            return ResponseEntity.ok(authenticationService.refreshToken(refreshTokenRequest));
        } catch (Exception e) {
            throw new RuntimeException();
        }

    }

    @GetMapping("/hello")
    public ResponseEntity<ResponseObject> sayHelloAuth() {
        return ResponseEntity.ok(new ResponseObject("ok", "Hello Im here", ""));
    }

    @PostMapping("/create-role")
    public ResponseEntity<ResponseObject> createRole(@ModelAttribute Role role) {
        return ResponseEntity.ok(new ResponseObject("ok", "Crete Role successfully", roleRepository.save(role)));
    }
}
