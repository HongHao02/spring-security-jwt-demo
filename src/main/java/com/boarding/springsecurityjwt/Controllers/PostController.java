package com.boarding.springsecurityjwt.Controllers;

import com.boarding.springsecurityjwt.DTO.CreatePostRequest;
import com.boarding.springsecurityjwt.Models.ResponseObject;
import com.boarding.springsecurityjwt.Models.User;
import com.boarding.springsecurityjwt.Services.PostService;
import com.boarding.springsecurityjwt.Services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/v1/posts")
@RequiredArgsConstructor
public class PostController {


    @Autowired
    private UserService userService;
    @Autowired
    private PostService postService;

    @PostMapping("/chutro/create")
    public ResponseEntity<ResponseObject> createPost(@ModelAttribute CreatePostRequest createPostRequest){
        System.out.println("~~~>Create Post < "+ LocalDateTime.now() + "> "+ createPostRequest.toString());
        return ResponseEntity.ok(postService.createPost(createPostRequest));
    }

    @GetMapping("/chutro/sayHelloChuTro")
    public ResponseEntity<ResponseObject> sayHelloChuTro(@RequestHeader("Authorization") String token){
        try{
            System.out.println("TOKEN "+ token);
            var  authorities= SecurityContextHolder.getContext().getAuthentication().getAuthorities();
            System.out.println("~~~>Say Hello From Authorities: "+ authorities);
            return ResponseEntity.ok(new ResponseObject("ok", "Hello! This is CHUTRO", ""));
        }catch (Exception e){
            return ResponseEntity.internalServerError().body(new ResponseObject("failed","Forbidden",e.getMessage()));
        }
    }
}
