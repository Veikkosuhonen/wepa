package com.github.vesuvesu.wepa.controller;

import com.github.vesuvesu.wepa.service.FriendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class FriendController {

    @Autowired
    private FriendService friendService;


    @Secured("USER")
    @PostMapping("/users/{name}/friend")
    public String friendRequest(@PathVariable String name) {
        friendService.sendRequest(name);
        return "redirect:/search";
    }


    @Secured("USER")
    @PostMapping("/users/{name}/friend/{action}")
    public String resolveFriendRequest(@PathVariable String name, @PathVariable String action) {
        friendService.resolveRequest(name, action);
        return "redirect:/myprofile#friends";
    }

    @Secured("USER")
    @PostMapping("/requests/{id}/cancel")
    public String cancelFriendRequest(@PathVariable Long id) {
        friendService.cancelRequest(id);
        return "redirect:/myprofile#friends";
    }
}