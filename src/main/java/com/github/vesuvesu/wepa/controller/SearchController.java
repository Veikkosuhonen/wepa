package com.github.vesuvesu.wepa.controller;

import com.github.vesuvesu.wepa.service.UserService;
import com.github.vesuvesu.wepa.user.User;
import com.github.vesuvesu.wepa.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class SearchController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Secured("USER")
    @GetMapping("/search")
    public String searchPage(Model model) {
        model.addAttribute("users", new ArrayList<String>());
        model.addAttribute("user", userService.getUser());
        return "search";
    }

    @Secured("USER")
    @PostMapping("/search")
    public String searchPage(@RequestParam String entry) {
        return "redirect:/search/" + entry;
    }

    @Secured("USER")
    @GetMapping("/search/{entry}")
    public String searchResults(@PathVariable String entry, Model model) {
        List<User> results = userRepository.findByNameContainingIgnoreCase(entry);
        model.addAttribute("users", results);
        model.addAttribute("user", userService.getUser());
        return "search";
    }
}
