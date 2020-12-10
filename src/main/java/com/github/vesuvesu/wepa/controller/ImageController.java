package com.github.vesuvesu.wepa.controller;

import com.github.vesuvesu.wepa.UserService;
import com.github.vesuvesu.wepa.account.Account;
import com.github.vesuvesu.wepa.account.AccountRepository;
import com.github.vesuvesu.wepa.post.ImageObject;
import com.github.vesuvesu.wepa.post.ImageRepository;
import com.github.vesuvesu.wepa.post.Post;
import com.github.vesuvesu.wepa.post.PostRepository;
import com.github.vesuvesu.wepa.user.User;
import com.github.vesuvesu.wepa.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;

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

    @GetMapping(path = "/users/{name}/posts/{id}/image", produces = "image/png")
    @ResponseBody
    public byte[] getImage(@PathVariable String name, @PathVariable Long id) {
        User author = userRepository.findByName(name);
        return postRepository.findByAuthorAndId(author, id).getImage().getContent();
    }

    @GetMapping(path = "/users/{name}/image", produces = "image/png")
    @ResponseBody
    public byte[] getOneImage(@PathVariable String name) {
        User author = userRepository.findByName(name);
        return postRepository.findByAuthor(author).get(0).getImage().getContent();
    }

    @PostMapping("/myprofile/newpost")
    public String newPost(@RequestParam("caption") String caption, @RequestParam("file")MultipartFile file) throws IOException {
        User user = userService.getUser();

        ImageObject img = new ImageObject(file.getBytes());
        imageRepository.save(img);

        Post post = new Post(img, new Date(), caption, user, 0);
        postRepository.save(post);

        return "redirect:/myprofile";
    }
}
