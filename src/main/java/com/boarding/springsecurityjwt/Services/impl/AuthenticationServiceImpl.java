package com.boarding.springsecurityjwt.Services.impl;

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
import com.boarding.springsecurityjwt.Services.JWTService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.HashSet;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JWTService jwtService;
    private final RoleRepository roleRepository;

    public ResponseObject signUp(SignUpRequest signUpRequest) {
        Role role;
        if (!signUpRequest.getRole().isEmpty()) {
            role = roleRepository.findByName(signUpRequest.getRole()).orElseThrow(() -> new IllegalArgumentException("Not found by Role name!"));
        } else {
            role = roleRepository.findByName("USER").orElseThrow(() -> new IllegalArgumentException("Not found by role USER"));
        }
        var user = userRepository.findByUsername(signUpRequest.getUsername());
        if (user.isPresent()) {
            return new ResponseObject("failed", "Username is already use", "");
        }
        User newUser = User.builder()
                .username(signUpRequest.getUsername())
                .firstName(signUpRequest.getFirstname())
                .lastName(signUpRequest.getLastname())
                .password(passwordEncoder.encode(signUpRequest.getPassword()))
                .roles(new HashSet<>())
                .build();
        newUser.getRoles().add(role);
        return new ResponseObject("ok", "create user successfully", userRepository.save(newUser));
    }

    public JwtAuthencationResponse signIn(SignInRequest signInRequest) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    signInRequest.getUsername(),
                    signInRequest.getPassword())
            );
            var user = userRepository.findByUsername(signInRequest.getUsername()).orElseThrow(
                    () -> new IllegalArgumentException("Invalid username or password!")
            );
            System.out.println("~~~UserLogin~~~> " + user.toString());
            var roles = user.getRoles();
            System.out.println("~~~UserRoles~~~> " + roles.toString());
            System.out.println("~~~Authority~~~> " + user.getAuthorities().toString());
            var jwt = jwtService.generateToken(user);
            var refreshToken = jwtService.generateRefreshToken(new HashMap<>(), user);

            return JwtAuthencationResponse.builder()
                    .token(jwt)
                    .refreshToken(refreshToken)
                    .user(new ResponseObject("ok", "Login successfully!", user))
                    .build();
        } catch (Exception e) {
            return JwtAuthencationResponse.builder()
                    .token("")
                    .refreshToken("")
                    .user(new ResponseObject("ok", "Invalid username or password!", e.getMessage()))
                    .build();
        }
    }

    public JwtAuthencationResponse refreshToken(RefreshTokenRequest refreshTokenRequest) {
        String userName = jwtService.extractUsername(refreshTokenRequest.getRefreshToken());
        User user = userRepository.findByUsername(userName).orElseThrow();

        if (jwtService.isTokenValid(refreshTokenRequest.getRefreshToken(), user)) {
            var jwt = jwtService.generateToken(user);

            return JwtAuthencationResponse.builder()
                    .token(jwt)
                    .refreshToken(refreshTokenRequest.getRefreshToken())
                    .user(new ResponseObject("ok", "Refresh token successfully!", user))
                    .build();
        }

        return null;
    }
}
