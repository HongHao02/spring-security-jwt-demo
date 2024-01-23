package com.boarding.springsecurityjwt.Services;

import com.boarding.springsecurityjwt.DTO.SignUpRequest;
import com.boarding.springsecurityjwt.Models.User;

public interface AuthenticatonService {
    User signUp(SignUpRequest signUpRequest);
}
