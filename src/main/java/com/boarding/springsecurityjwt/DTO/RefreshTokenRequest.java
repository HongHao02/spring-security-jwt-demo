package com.boarding.springsecurityjwt.DTO;

import lombok.Data;

@Data
public class RefreshTokenRequest {
    private String refreshToken;
}
