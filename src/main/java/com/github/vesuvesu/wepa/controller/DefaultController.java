package com.github.vesuvesu.wepa.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DefaultController {

    @GetMapping("/")
    public String helloWorld(Model model) {
        model.addAttribute("loggedIn", SecurityContextHolder.getContext().getAuthentication().isAuthenticated());
        return "index";
    }
}