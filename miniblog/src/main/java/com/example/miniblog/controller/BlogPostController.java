package com.example.miniblog.controller;

import com.example.miniblog.model.BlogPost;
import com.example.miniblog.repository.BlogPostRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/posts")
public class BlogPostController {

    private final BlogPostRepository repository;

    public BlogPostController(BlogPostRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public String getAllPosts(Model model) {
        model.addAttribute("posts", repository.findAll());
        return "posts";
    }

    @GetMapping("/{id}")
    public String getPost(@PathVariable Long id, Model model) {
        BlogPost post = repository.findById(id).orElseThrow();
        model.addAttribute("post", post);
        return "post";
    }

    @GetMapping("/new")
    public String createForm(Model model) {
        model.addAttribute("post", new BlogPost());
        return "create_post";
    }

    @PostMapping
    public String savePost(@ModelAttribute BlogPost post) {
        repository.save(post);
        return "redirect:/posts";
    }

    @GetMapping("/delete/{id}")
    public String deletePost(@PathVariable Long id) {
        repository.deleteById(id);
        return "redirect:/posts"; // redirect back to all posts
    }
}

