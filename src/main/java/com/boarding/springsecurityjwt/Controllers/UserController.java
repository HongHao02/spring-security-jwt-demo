package com.boarding.springsecurityjwt.Controllers;

import com.boarding.springsecurityjwt.Models.ResponseObject;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {
    @GetMapping("/sayUser")
    public ResponseEntity<ResponseObject> sayAdmin(){
        try{
            return ResponseEntity.ok(new ResponseObject("ok", "Hello! This is User", ""));
        }catch (Exception e){
            return ResponseEntity.internalServerError().body(new ResponseObject("failed","Forbidden",e.fillInStackTrace().getMessage()));
        }
    }
}
