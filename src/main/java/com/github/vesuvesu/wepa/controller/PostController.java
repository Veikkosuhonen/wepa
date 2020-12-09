package com.github.vesuvesu.wepa.controller;

import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PostController {

    @Secured("USER")
    @GetMapping("/newpost")
    public String newPost() {
        return "newpost";
    }
}
