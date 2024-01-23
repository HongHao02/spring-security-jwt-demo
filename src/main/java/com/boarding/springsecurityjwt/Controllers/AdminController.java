package com.boarding.springsecurityjwt.Controllers;

import com.boarding.springsecurityjwt.Models.ResponseObject;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/admin")
@RequiredArgsConstructor
public class AdminController {
    @GetMapping("/sayAdmin")
    public ResponseEntity<ResponseObject> sayAdmin(){
        return ResponseEntity.ok(new ResponseObject("ok", "Hello! This is admin", ""));
    }
}
