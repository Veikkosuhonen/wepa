package com.github.vesuvesu.wepa.service;

import com.github.vesuvesu.wepa.account.AccountRepository;
import com.github.vesuvesu.wepa.post.ImageObject;
import com.github.vesuvesu.wepa.post.ImageRepository;
import com.github.vesuvesu.wepa.post.Post;
import com.github.vesuvesu.wepa.post.PostRepository;
import com.github.vesuvesu.wepa.user.User;
import com.github.vesuvesu.wepa.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public User getUser() {
        return accountRepository.findByUsername(
                SecurityContextHolder
                        .getContext()
                        .getAuthentication()
                        .getName()).getUser();
    }

    @Transactional
    public User getUserByName(String username) {
        return userRepository.findByName(username);
    }

    @Transactional
    public void setProfilePic(Long postId) {
        User user = getUser();
        Post post = postRepository.getOne(postId);
        if (!post.getAuthor().equals(user)) return;
        user.setProfilePic(post.getImage());
        user.setHasProfilePic(true);
        userRepository.save(user);
    }
}
