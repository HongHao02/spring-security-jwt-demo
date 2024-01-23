package com.boarding.springsecurityjwt.Controllers;

import com.boarding.springsecurityjwt.DTO.SignUpRequest;
import com.boarding.springsecurityjwt.Models.ResponseObject;
import com.boarding.springsecurityjwt.Services.AuthenticatonService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    @Autowired
    private AuthenticatonService authenticatonService;


    @PostMapping("/signup")
    public ResponseEntity<ResponseObject> signUp(@RequestBody SignUpRequest signUpRequest){
        try{
            return ResponseEntity.ok().body(new ResponseObject("ok", "Create User Successfully", authenticatonService.signUp(signUpRequest)));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new ResponseObject("Forbidden","Server error",""));

        }

    }
}
