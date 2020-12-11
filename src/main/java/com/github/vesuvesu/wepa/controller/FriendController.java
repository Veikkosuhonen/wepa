package com.github.vesuvesu.wepa.controller;

import com.github.vesuvesu.wepa.FriendService;
import com.github.vesuvesu.wepa.UserService;
import com.github.vesuvesu.wepa.account.Account;
import com.github.vesuvesu.wepa.account.AccountRepository;
import com.github.vesuvesu.wepa.friend.FriendRequest;
import com.github.vesuvesu.wepa.friend.FriendRequestRepository;
import com.github.vesuvesu.wepa.friend.FriendRequestStatus;
import com.github.vesuvesu.wepa.user.User;
import com.github.vesuvesu.wepa.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Date;

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
        return "redirect:/myprofile?tab=friends";
    }
}