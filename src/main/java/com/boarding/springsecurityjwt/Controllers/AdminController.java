package com.boarding.springsecurityjwt.Controllers;

import com.boarding.springsecurityjwt.DTO.PostRequest;
import com.boarding.springsecurityjwt.Models.ResponseObject;
import com.boarding.springsecurityjwt.Services.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/admin")
@RequiredArgsConstructor
public class AdminController {
    @Autowired
    private PostService postService;
    @GetMapping("/sayAdmin")
    public ResponseEntity<ResponseObject> sayAdmin(){
        return ResponseEntity.ok(new ResponseObject("ok", "Hello! This is admin", ""));
    }

    @PostMapping("/fakePost")
    public ResponseEntity<ResponseObject> fakePost(@RequestHeader("Authorization") String token,
                                                   @RequestBody PostRequest postRequest){
        return ResponseEntity.ok(postService.fakeCreateAdminPost(token,postRequest));
    }
}
