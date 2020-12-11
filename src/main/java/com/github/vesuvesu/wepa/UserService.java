package com.github.vesuvesu.wepa;

import com.github.vesuvesu.wepa.account.AccountRepository;
import com.github.vesuvesu.wepa.post.ImageObject;
import com.github.vesuvesu.wepa.post.ImageRepository;
import com.github.vesuvesu.wepa.user.User;
import com.github.vesuvesu.wepa.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private ImageRepository imageRepository;

    @Autowired
    private UserRepository userRepository;

    public User getUser() {
        return accountRepository.findByUsername(
                SecurityContextHolder
                        .getContext()
                        .getAuthentication()
                        .getName()).getUser();
    }


    public void setProfilePic(Long imgId) {
        User user = getUser();
        user.setProfilePic(imageRepository.getOne(imgId));
        user.setHasProfilePic(true);
        userRepository.save(user);
    }
}
