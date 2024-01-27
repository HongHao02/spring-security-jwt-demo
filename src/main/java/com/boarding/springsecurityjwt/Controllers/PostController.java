package com.boarding.springsecurityjwt.Controllers;

import com.boarding.springsecurityjwt.DTO.CreatePostRequest;
import com.boarding.springsecurityjwt.Models.ResponseObject;
import com.boarding.springsecurityjwt.Models.User;
import com.boarding.springsecurityjwt.Services.PostService;
import com.boarding.springsecurityjwt.Services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/v1/post")
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
}
