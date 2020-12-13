package com.github.vesuvesu.wepa.controller;

import com.github.vesuvesu.wepa.service.WallService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class WallController {

    @Autowired
    WallService wallService;

    @Transactional
    @Secured("USER")
    @PostMapping("/users/{wallOwnerName}/wall")
    public String wallPost(@RequestParam String text, @PathVariable String wallOwnerName) {
        boolean result = wallService.wallPost(text, wallOwnerName);
        return result ? "redirect:/users/"+wallOwnerName+"#wall" : "redirect:/myprofile";
    }

    @Transactional
    @Secured("USER")
    @PostMapping("/users/{wallOwnerName}/wall/{id}/comment")
    public String commentWallPost(@RequestParam String text, @PathVariable String wallOwnerName, @PathVariable Long id) {
        boolean result = wallService.commentWallPost(text, wallOwnerName, id);
        return result ? "redirect:/users/"+wallOwnerName+"#wall" : "redirect:/myprofile";
    }

    @Transactional
    @Secured("USER")
    @PostMapping("/users/{wallOwnerName}/wall/{id}/like")
    public String likeWallPost(@PathVariable String wallOwnerName, @PathVariable Long id) {
        boolean result = wallService.likeWallPost(wallOwnerName, id);
        return result ? "redirect:/users/"+wallOwnerName+"#wall" : "redirect:/myprofile";
    }
}
