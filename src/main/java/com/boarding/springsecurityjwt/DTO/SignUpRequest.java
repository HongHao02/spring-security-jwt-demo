package com.boarding.springsecurityjwt.DTO;

import lombok.Data;

@Data
public class SignUpRequest {

    private String username;
    private String password;
    private String firstname;
    private String lastname;
}
