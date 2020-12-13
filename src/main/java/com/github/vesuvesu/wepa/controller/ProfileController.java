package com.github.vesuvesu.wepa.controller;

import com.github.vesuvesu.wepa.service.UserService;
import com.github.vesuvesu.wepa.service.WallService;
import com.github.vesuvesu.wepa.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ProfileController {

    @Autowired
    private UserService userService;

    @Autowired
    private WallService wallService;

    @Secured("USER")
    @GetMapping("/profile")
    public String userView(Model model) {
        User user = userService.getUser();
        model.addAttribute("user", user);
        model.addAttribute("wall", wallService.getWallPosts(user));
        return "profile";
    }

    @Secured("USER")
    @GetMapping("/users/{username}")
    public String publicUserView(@PathVariable String username, Model model) {
        User actor = userService.getUser();
        User user = userService.getUserByName(username);

        if (!actor.getFriends().contains(user)) {
            model.addAttribute("user", actor);
            return "profile";
        }

        model.addAttribute("user", user);
        model.addAttribute("wall", wallService.getWallPosts(user));
        return "publicprofile";
    }

    @Secured("USER")
    @PostMapping("/setprofilepic")
    public String setProfilePic(@RequestParam Long postId) {
        userService.setProfilePic(postId);
        return "redirect:/profile#album";
    }
}
