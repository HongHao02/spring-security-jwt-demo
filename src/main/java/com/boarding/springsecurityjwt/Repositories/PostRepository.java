package com.boarding.springsecurityjwt.Repositories;

import com.boarding.springsecurityjwt.Models.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}
