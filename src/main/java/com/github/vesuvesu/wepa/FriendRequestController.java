package com.github.vesuvesu.wepa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class FriendRequestController {

    @Autowired
    private UserRepository userRepository;

    @Secured("USER")
    @PostMapping("/friend-request")
    public String friendRequest(@RequestParam String username) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User sender = userRepository.findByName(auth.getName());
        User receiver = userRepository.findByName(username);
        if (receiver != null && sender != null) {
            if (!receiver.getFriendRequests().contains(sender))
                receiver.getFriendRequests().add(sender);
        }
        return "redirect:/search";
    }
}
