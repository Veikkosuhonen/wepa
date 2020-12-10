package com.github.vesuvesu.wepa.controller;

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
    private UserRepository userRepository;

    @Autowired
    private FriendRequestRepository friendRequestRepository;

    @Autowired
    private UserService userService;

    @Transactional
    @Secured("USER")
    @PostMapping("/users/{name}/friend")
    public String friendRequest(@PathVariable String name) {
        System.out.println("Preparing friend request for " + name);

        User sender = userService.getUser();

        User receiver = userRepository.findByName(name);

        if (receiver == null || sender == null) {
            System.out.println("something went wrong when sending a request to " + name);
            //Todo error message
            return "redirect:/search";
        }

        //Add request to receivers list if it contains none from the sender
        if (receiver.getIncomingFriendRequests().stream().noneMatch(r -> sender.equals(userRepository))) {

            FriendRequest request = new FriendRequest(sender.getName(), receiver.getName(), new Date(), FriendRequestStatus.PENDING);
            friendRequestRepository.save(request);

            sender.getSentFriendRequests().add(request);
            receiver.getIncomingFriendRequests().add(request);
        } else {
            System.out.println("request not added since " + name + " already had a request from user");
        }

        return "redirect:/search";
    }


    @Transactional
    @Secured("USER")
    @PostMapping("/users/{name}/friend/{action}")
    public String resolveFriendRequest(@PathVariable String name, @PathVariable String action) {
        User actor = userService.getUser();

        User sender = userRepository.findByName(name);
        if (sender==null) {
            System.out.println("Sender '"+name+"' of this friend request is null");
            //todo 404
            return "redirect:/myprofile";
        }

        FriendRequest request = actor.getIncomingFriendRequests()
                .stream()
                .filter(r -> r.getSenderName().equals(sender.getName()))
                .findFirst().get();

        if (request != null) {

            if (action.equals("accept")) {
                actor.getIncomingFriendRequests().remove(request);
                request.setStatus(FriendRequestStatus.ACCEPTED);

                sender.addFriend(actor);
                actor.addFriend(sender);
                //Todo send accept message to sender

                System.out.println(actor.getName() + " accepted friend request from " + sender.getName());

            } else if (action.equals("decline")) {
                actor.getIncomingFriendRequests().remove(request);
                request.setStatus(FriendRequestStatus.DECLINED);

                //Todo send decline message to sender

                System.out.println(actor.getName() + " declined friend request from " + sender.getName());

            } else {
                System.out.println("Action '"+action+"' was invalid");
                //todo 404
            }
        } else {
            System.out.println("This friend request did not exist");
            //todo 404
        }

        return "redirect:/myprofile";
    }
}