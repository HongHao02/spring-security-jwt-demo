package com.boarding.springsecurityjwt.Services;

import com.boarding.springsecurityjwt.DTO.CreatePostRequest;
import com.boarding.springsecurityjwt.DTO.PostRequest;
import com.boarding.springsecurityjwt.Models.ResponseObject;

public interface PostService {
    ResponseObject fakeCreateAdminPost(String token, PostRequest postRequest);

    ResponseObject createPost(CreatePostRequest createPostRequest);
}
