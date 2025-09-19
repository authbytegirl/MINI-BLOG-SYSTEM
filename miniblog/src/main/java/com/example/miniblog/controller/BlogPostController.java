package com.example.miniblog.controller;

import com.example.miniblog.model.BlogPost;
import com.example.miniblog.model.User;
import com.example.miniblog.repository.BlogPostRepository;
import com.example.miniblog.repository.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/posts")
public class BlogPostController {

    private final BlogPostRepository postRepository;
    private final UserRepository userRepository;

    public BlogPostController(BlogPostRepository postRepository, UserRepository userRepository) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }

    // Show all posts for logged-in user
    @GetMapping
    public String listPosts(Model model, Authentication auth) {
        User user = userRepository.findByUsername(auth.getName()).orElseThrow();
        List<BlogPost> posts = postRepository.findByUser(user);
        model.addAttribute("posts", posts);
        return "posts";
    }

    // Show single post (only if it belongs to logged-in user)
    @GetMapping("/{id}")
    public String getPost(@PathVariable Long id, Model model, Authentication auth) {
        BlogPost post = postRepository.findById(id).orElseThrow();
        if (!post.getUser().getUsername().equals(auth.getName())) {
            return "redirect:/posts"; // prevent accessing others' posts
        }
        model.addAttribute("post", post);
        return "post";
    }

    // Show form to create post
    @GetMapping("/new")
    public String createForm(Model model) {
        model.addAttribute("post", new BlogPost());
        return "create_post";
    }

    // Save post for logged-in user
    @PostMapping
    public String savePost(@ModelAttribute BlogPost post, Authentication auth) {
        User user = userRepository.findByUsername(auth.getName()).orElseThrow();
        post.setUser(user);
        postRepository.save(post);
        return "redirect:/posts";
    }

    // Delete post (only if belongs to logged-in user)
    @GetMapping("/delete/{id}")
    public String deletePost(@PathVariable Long id, Authentication auth) {
        BlogPost post = postRepository.findById(id).orElseThrow();
        if (post.getUser().getUsername().equals(auth.getName())) {
            postRepository.delete(post);
        }
        return "redirect:/posts";
    }
}


