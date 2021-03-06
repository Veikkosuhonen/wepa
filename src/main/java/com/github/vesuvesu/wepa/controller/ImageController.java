package com.github.vesuvesu.wepa.controller;

import com.github.vesuvesu.wepa.service.UserService;
import com.github.vesuvesu.wepa.post.ImageObject;
import com.github.vesuvesu.wepa.post.ImageRepository;
import com.github.vesuvesu.wepa.post.Post;
import com.github.vesuvesu.wepa.post.PostRepository;
import com.github.vesuvesu.wepa.user.User;
import com.github.vesuvesu.wepa.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
public class ImageController {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private ImageRepository imageRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @GetMapping(path = "/users/{name}/images/{id}", produces = "image/png")
    @ResponseBody
    public byte[] getImage(@PathVariable String name, @PathVariable Long id) {
        User author = userRepository.findByName(name);
        return imageRepository.getOne(id).getContent();
    }

    @GetMapping(path = "/users/{name}/image", produces = "image/png")
    @ResponseBody
    public byte[] getOneImage(@PathVariable String name) {
        User author = userRepository.findByName(name);
        return postRepository.findByAuthor(author).get(0).getImage().getContent();
    }
}
