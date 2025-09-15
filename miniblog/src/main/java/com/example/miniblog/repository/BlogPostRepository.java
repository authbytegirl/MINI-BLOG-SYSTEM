package com.example.miniblog.repository;

import com.example.miniblog.model.BlogPost;
import  org.springframework.data.jpa.repository.JpaRepository;

public interface BlogPostRepository extends JpaRepository<BlogPost, Long>{
}
