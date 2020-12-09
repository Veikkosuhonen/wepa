package com.github.vesuvesu.wepa;

import com.github.vesuvesu.wepa.account.AccountRepository;
import com.github.vesuvesu.wepa.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private AccountRepository accountRepository;

    public User getUser() {
        return accountRepository.findByUsername(
                SecurityContextHolder
                        .getContext()
                        .getAuthentication()
                        .getName()).getUser();
    }
}
