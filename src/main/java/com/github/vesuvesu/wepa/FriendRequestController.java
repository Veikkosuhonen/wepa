package com.github.vesuvesu.wepa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class FriendRequestController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Transactional
    @Secured("USER")
    @PostMapping("/friend-request")
    public String friendRequest(@RequestParam String username) {

        Account account = accountRepository.findByUsername(
                SecurityContextHolder.getContext().getAuthentication().getName());

        User sender = account.getUser();
        User receiver = userRepository.findByName(username);

        if (receiver != null && sender != null) {
            if (!receiver.getFriendRequests().contains(sender))
                receiver.getFriendRequests().add(sender);
        }
        return "redirect:/search";
    }
}
