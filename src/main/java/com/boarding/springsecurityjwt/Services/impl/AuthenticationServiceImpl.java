package com.boarding.springsecurityjwt.Services.impl;

import com.boarding.springsecurityjwt.DTO.JwtAuthencationResponse;
import com.boarding.springsecurityjwt.DTO.RefreshTokenRequest;
import com.boarding.springsecurityjwt.DTO.SignInRequest;
import com.boarding.springsecurityjwt.DTO.SignUpRequest;
import com.boarding.springsecurityjwt.Models.ResponseObject;
import com.boarding.springsecurityjwt.Models.Roles;
import com.boarding.springsecurityjwt.Models.User;
import com.boarding.springsecurityjwt.Repositories.UserRepository;
import com.boarding.springsecurityjwt.Services.AuthenticatonService;
import com.boarding.springsecurityjwt.Services.JWTService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticatonService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JWTService jwtService;

    public User signUp(SignUpRequest signUpRequest) {
        User user = User.builder()
                .username(signUpRequest.getUsername())
                .firstName(signUpRequest.getFirstname())
                .lastName(signUpRequest.getLastname())
                .roles(Roles.USER)
                .password(passwordEncoder.encode(signUpRequest.getPassword()))
                .build();
        return userRepository.save(user);
    }

    public JwtAuthencationResponse signIn(SignInRequest signInRequest) {
        try{
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    signInRequest.getUsername(),
                    signInRequest.getPassword())
            );
            var user = userRepository.findByUsername(signInRequest.getUsername()).orElseThrow(
                    () -> new IllegalArgumentException("Invalid username or password!")
            );
            var jwt = jwtService.generateToken(user);
            var refreshToken = jwtService.generateRefreshToken(new HashMap<>(), user);

            return JwtAuthencationResponse.builder()
                    .token(jwt)
                    .refreshToken(refreshToken)
                    .user(new ResponseObject("ok", "Login successfully!", user))
                    .build();
        }catch (Exception e){
            return JwtAuthencationResponse.builder()
                    .token("")
                    .refreshToken("")
                    .user(new ResponseObject("ok", "Login successfully!", null))
                    .build();
        }
    }

    public JwtAuthencationResponse refreshToken(RefreshTokenRequest refreshTokenRequest){
        String userName = jwtService.extractUsername(refreshTokenRequest.getRefreshToken());
        User user= userRepository.findByUsername(userName).orElseThrow();

        if(jwtService.isTokenValid(refreshTokenRequest.getRefreshToken(), user)){
            var jwt= jwtService.generateToken(user);

            return JwtAuthencationResponse.builder()
                    .token(jwt)
                    .refreshToken(refreshTokenRequest.getRefreshToken())
                    .user(new ResponseObject("ok", "Refresh token successfully!", user))
                    .build();
        }

        return null;
    }
}
