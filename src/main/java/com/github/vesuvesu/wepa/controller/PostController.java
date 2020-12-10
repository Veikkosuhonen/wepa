package com.github.vesuvesu.wepa.controller;

import com.github.vesuvesu.wepa.post.Post;
import com.github.vesuvesu.wepa.post.PostRepository;
import com.github.vesuvesu.wepa.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class PostController {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;

    @Secured("USER")
    @GetMapping("/newpost")
    public String newPost() {
        return "newpost";
    }

    @GetMapping("/users/{username}/posts/{id}")
    public String getPost(@PathVariable String username, @PathVariable Long id, Model model) {
        Post post = postRepository.findByAuthorAndId(
                userRepository.findByName(username),
                id
        );
        model.addAttribute("post", post);
        return "post";
    }
}
