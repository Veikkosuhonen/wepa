package com.github.vesuvesu.wepa.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DefaultController {
    @GetMapping("/")
    public String helloWorld(Model model) {
        model.addAttribute("message", "fabulous");
        return "index";
    }

    @GetMapping("/authenticated")
    public String authenticated(Model model) {
        model.addAttribute("message", "Authenticated");
        return "index";
    }
}
