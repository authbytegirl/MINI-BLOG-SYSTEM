package com.example.miniblog.repository;

import com.example.miniblog.model.BlogPost;
import com.example.miniblog.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface BlogPostRepository extends JpaRepository<BlogPost, Long>{
    List<BlogPost> findByUser(User user);
}
