package com.boarding.springsecurityjwt.Controllers;

import com.boarding.springsecurityjwt.DTO.JwtAuthencationResponse;
import com.boarding.springsecurityjwt.DTO.RefreshTokenRequest;
import com.boarding.springsecurityjwt.DTO.SignInRequest;
import com.boarding.springsecurityjwt.DTO.SignUpRequest;
import com.boarding.springsecurityjwt.Models.ResponseObject;
import com.boarding.springsecurityjwt.Services.AuthenticatonService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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
            System.out.println("SignUpRequest "+ signUpRequest.toString());
            return ResponseEntity.ok().body(new ResponseObject("ok", "Create User Successfully", authenticatonService.signUp(signUpRequest)));
        } catch (Exception e) {
            throw new RuntimeException(String.valueOf(new ResponseObject("failed","Forbidden", "")));
        }

    }
    @PostMapping("/signin")
    public ResponseEntity<JwtAuthencationResponse> signIn(@RequestBody SignInRequest signInRequest){
        try{
            System.out.println("SignUpRequest "+ signInRequest.toString());
            return ResponseEntity.ok(authenticatonService.signIn(signInRequest));
        } catch (Exception e) {
            throw new RuntimeException();
        }

    }
    @PostMapping("/refresh")
    public ResponseEntity<JwtAuthencationResponse> refresh(@RequestBody RefreshTokenRequest refreshTokenRequest){
        try{
//            System.out.println("SignUpRequest "+ signInRequest.toString());
            return ResponseEntity.ok(authenticatonService.refreshToken(refreshTokenRequest));
        } catch (Exception e) {
            throw new RuntimeException();
        }

    }
    @GetMapping("/hello")
    public ResponseEntity<ResponseObject> sayHelloAuth(){
        return ResponseEntity.ok(new ResponseObject("ok", "Hello Im here", ""));
    }
}
