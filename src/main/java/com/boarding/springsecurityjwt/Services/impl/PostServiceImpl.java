package com.boarding.springsecurityjwt.Services.impl;

import com.boarding.springsecurityjwt.DTO.CreatePostRequest;
import com.boarding.springsecurityjwt.DTO.PostRequest;
import com.boarding.springsecurityjwt.Models.Post;
import com.boarding.springsecurityjwt.Models.ResponseObject;
import com.boarding.springsecurityjwt.Models.User;
import com.boarding.springsecurityjwt.Repositories.PostRepository;
import com.boarding.springsecurityjwt.Repositories.UserRepository;
import com.boarding.springsecurityjwt.Services.JWTService;
import com.boarding.springsecurityjwt.Services.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JWTService jwtService;
    @Autowired
    private PostRepository postRepository;


    public ResponseObject fakeCreateAdminPost(String token,PostRequest postRequest){
//        var userName= jwtService.extractUsername(token);
        var userName= SecurityContextHolder.getContext().getAuthentication().getName();
        System.out.println("Name "+ userName);

        var user= userRepository.findByUsername(userName);
        if(user.isPresent()){
            return ResponseObject.builder()
                    .status("ok")
                    .message("Create Post successfully!")
                    .data(postRequest)
                    .build();
        }
        return null;
    }

    @Override
    public ResponseObject createPost(CreatePostRequest createPostRequest) {
        try{
            System.out.println("~~~>Create Post info: "+ createPostRequest.toString());
            var userName= SecurityContextHolder.getContext().getAuthentication().getName();
            System.out.println("~~~>Create Post Username: "+ userName);
            var  authorities= SecurityContextHolder.getContext().getAuthentication().getAuthorities();
            System.out.println("~~~>Create Post Authorities: "+ authorities);
            User user= userRepository.findByUsername(userName).orElseThrow(()-> new IllegalArgumentException("Not found by role USER"));
            if(user != null){
                Post post= Post.builder()
                        .title(createPostRequest.getTitle())
                        .description(createPostRequest.getDescription())
                        .published_at(LocalDateTime.now())
                        .user(user)
                        .build();

                return new ResponseObject("ok", "Create new Post successfully", postRepository.save(post));
            }
        }catch (Exception ex){
            return new ResponseObject("failed", "Permission denied!",ex.getMessage());
        }
        return new ResponseObject("failed", "Permission denied!","");
    }


}
