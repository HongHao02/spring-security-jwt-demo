package com.boarding.springsecurityjwt.Services;

import com.boarding.springsecurityjwt.Models.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.HashMap;

public interface JWTService {

    String extractUsername(String token);
    Long extractId(String token);

    String generateToken(UserDetails userDetails);
    boolean isTokenValid(String token, UserDetails userDetails);

    String generateRefreshToken(HashMap<Object, Object> objectObjectHashMap, UserDetails userDetails);
}
