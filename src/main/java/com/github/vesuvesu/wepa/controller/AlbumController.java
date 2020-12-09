package com.github.vesuvesu.wepa.controller;

import com.github.vesuvesu.wepa.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AlbumController {

    @Autowired
    private UserService userService;

    @GetMapping("/myalbum")
    public String getAlbum(Model model) {
        model.addAttribute("user", userService.getUser());
        return "myalbum";
    }
}