package com.boarding.springsecurityjwt.DTO;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CreatePostRequest {
    private String title;
    private String description;
}
