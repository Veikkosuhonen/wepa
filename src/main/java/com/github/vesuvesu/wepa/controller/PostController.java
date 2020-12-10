package com.github.vesuvesu.wepa.controller;

import com.github.vesuvesu.wepa.PostService;
import com.github.vesuvesu.wepa.UserService;
import com.github.vesuvesu.wepa.post.Post;
import com.github.vesuvesu.wepa.post.PostRepository;
import com.github.vesuvesu.wepa.user.UserRepository;
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
    @GetMapping("/myalbum")
    public String getAlbum(Model model) {
        model.addAttribute("user", userService.getUser());
        return "myalbum";
    }


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
        return fullview ? "redirect:/users/"+username+"/posts/"+id : "redirect:/myalbum";
    }
}
