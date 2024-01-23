package com.boarding.springsecurityjwt.Services;

import com.boarding.springsecurityjwt.DTO.JwtAuthencationResponse;
import com.boarding.springsecurityjwt.DTO.RefreshTokenRequest;
import com.boarding.springsecurityjwt.DTO.SignInRequest;
import com.boarding.springsecurityjwt.DTO.SignUpRequest;
import com.boarding.springsecurityjwt.Models.User;

public interface AuthenticatonService {
    User signUp(SignUpRequest signUpRequest);
    JwtAuthencationResponse signIn(SignInRequest signInRequest);
    JwtAuthencationResponse refreshToken(RefreshTokenRequest refreshTokenRequest);
}
