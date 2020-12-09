package com.github.vesuvesu.wepa.controller;

import com.github.vesuvesu.wepa.UserService;
import com.github.vesuvesu.wepa.account.AccountRepository;
import com.github.vesuvesu.wepa.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProfileController {

    @Autowired
    private UserService userService;

    @Secured("USER")
    @GetMapping("/myprofile")
    public String userView(Model model) {

        User user = userService.getUser();

        model.addAttribute("user", user);
        return "myprofile";
    }
}
