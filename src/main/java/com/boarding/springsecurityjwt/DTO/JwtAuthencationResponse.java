package com.boarding.springsecurityjwt.DTO;

import com.boarding.springsecurityjwt.Models.ResponseObject;
import lombok.*;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class JwtAuthencationResponse {

    private ResponseObject user;
    private String token;
    private String refreshToken;

}
