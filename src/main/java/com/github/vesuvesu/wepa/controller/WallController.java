package com.github.vesuvesu.wepa.controller;

import com.github.vesuvesu.wepa.UserService;
import com.github.vesuvesu.wepa.post.Comment;
import com.github.vesuvesu.wepa.post.CommentRepository;
import com.github.vesuvesu.wepa.post.WallPost;
import com.github.vesuvesu.wepa.post.WallPostRepository;
import com.github.vesuvesu.wepa.user.User;
import com.github.vesuvesu.wepa.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;

@Controller
public class WallController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    WallPostRepository wallPostRepository;

    @Autowired
    CommentRepository commentRepository;

    @Autowired
    UserService userService;

    @Transactional
    @Secured("USER")
    @PostMapping("/users/{wallOwnerName}/wall")
    public String wallPost(@RequestParam String text, @PathVariable String wallOwnerName) {
        User wallOwner = userRepository.findByName(wallOwnerName);
        if (wallOwner == null) return "redirect:/users/"+wallOwnerName+"/wall";

        WallPost wallPost = new WallPost(text, wallOwner);
        wallPostRepository.save(wallPost);
        wallOwner.getWallPosts().add(wallPost);
        return "redirect:/users/"+wallOwnerName+"/wall";
    }

    @Transactional
    @Secured("USER")
    @PostMapping("/users/{wallOwnerName}/wall/{id}")
    public String commentWallPost(@RequestParam String text, @PathVariable String wallOwnerName, @PathVariable Long id) {
        WallPost wallPost = wallPostRepository.findByWallOwnerAndId(userRepository.findByName(wallOwnerName), id);
        if (wallPost == null) return "redirect:/users/"+wallOwnerName+"/wall";

        Comment comment = new Comment(userService.getUser().getName(), text, new Date());
        commentRepository.save(comment);
        wallPost.getComments().add(comment);

        return "redirect:/users/"+wallOwnerName+"/wall";
    }
}
