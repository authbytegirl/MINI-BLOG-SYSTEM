package com.example.miniblog.model;

import jakarta.persistence.*;

@Entity

public class BlogPost {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    private String title;

    @Column(length = 5000)
    private String content;

    private String author;

    public Long getId(){return id;}
    public void setId(Long id){this.id = id;}

    public String getTitle(){return title;}
    public void setTitle(String title){this.title = title;}

    public String getContent(){return content;}
    public void setContent(String content){this.content = content;}

    public String getAuthor(){return author;}
    public void setAuthor(String author){this.author = author;}
}
