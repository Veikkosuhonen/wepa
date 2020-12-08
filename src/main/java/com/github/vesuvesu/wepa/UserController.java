package com.github.vesuvesu.wepa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;

@Controller
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Transactional
    @Secured("USER")
    @PostMapping("/users/{name}/friend")
    public String friendRequest(@PathVariable String name) {
        Account account = accountRepository.findByUsername(
                SecurityContextHolder.getContext().getAuthentication().getName());

        User sender = account.getUser();
        User receiver = userRepository.findByName(name);

        if (receiver != null && sender != null) {
            if (!receiver.getFriendRequests().contains(sender))
                receiver.getFriendRequests().add(sender);
        }
        return "redirect:/search";
    }

    @Transactional
    @Secured("USER")
    @PostMapping("/users/{name}/{action}")
    public String resolveFriendRequest(@PathVariable String name, @PathVariable String action) {
        Account account = accountRepository.findByUsername(
                SecurityContextHolder.getContext().getAuthentication().getName());

        User actor = account.getUser();
        if (actor==null) {
            System.out.println("Actor of this friend request is null");
            return "redirect:/myprofile";
        }

        User sender = userRepository.findByName(name);
        if (sender==null) {
            System.out.println("Sender '"+name+"' of this friend request is null");
            return "redirect:/myprofile";
        }

        if (actor.getFriendRequests().contains(sender)) {

            if (action.equals("accept")) {
                sender.getFriends().add(actor);
                actor.getFriends().add(sender);
                //Todo send accept message to sender

                actor.getFriendRequests().remove(sender);
                System.out.println(actor.getName() + " accepted friend request from " + sender.getName());

            } else if (action.equals("decline")) {
                actor.getFriendRequests().remove(sender);
                //Todo send decline message to sender

                System.out.println(actor.getName() + " declined friend request from " + sender.getName());

            } else {System.out.println("Action '"+action+"' was invalid");}
        } else {System.out.println("This friend request did not exist");}


        return "redirect:/myprofile";
    }
}
