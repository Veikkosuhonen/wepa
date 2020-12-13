package com.github.vesuvesu.wepa.controller;

import com.github.vesuvesu.wepa.service.PostService;
import com.github.vesuvesu.wepa.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PostController {

    @Autowired
    private PostService postService;

    @Autowired
    private UserService userService;

    @Secured("USER")
    @GetMapping("/newpost")
    public String newPost() {
        return "newpost";
    }


    @GetMapping("/users/{username}/posts/{id}")
    public String getPost(@PathVariable String username, @PathVariable Long id, Model model) {
        model.addAttribute("post", postService.getPost(username, id));
        return "post";
    }


    @Secured("USER")
    @PostMapping("/users/{username}/posts/{id}/like")
    public String likePost(@PathVariable String username, @PathVariable Long id, @RequestParam boolean fullview) {

        postService.likePost(username, id);
        return fullview ? "redirect:/users/"+username+"/posts/"+id : "redirect:/profile#album";
    }

    @Secured("USER")
    @PostMapping("/users/{username}/posts/{id}/comment")
    public String commentPost(@PathVariable String username, @PathVariable Long id, @RequestParam String text, @RequestParam boolean fullview) {

        postService.comment(username, text, id);
        return fullview ? "redirect:/users/"+username+"/posts/"+id : "redirect:/profile#album";
    }
}
