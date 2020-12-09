package com.github.vesuvesu.wepa.controller;

import com.github.vesuvesu.wepa.account.Account;
import com.github.vesuvesu.wepa.account.AccountRepository;
import com.github.vesuvesu.wepa.user.User;
import com.github.vesuvesu.wepa.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AccountController {

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @GetMapping("/accounts")
    public String list(Model model) {
        model.addAttribute("accounts", accountRepository.findAll());
        return "accounts";
    }

    @Transactional
    @PostMapping("/accounts")
    public String addAccount(@RequestParam String username, @RequestParam String password, @RequestParam String name) {
        if (accountRepository.findByUsername(username) != null) {
            return "redirect:/accounts";
        }

        Account a = new Account(username, passwordEncoder.encode(password));
        accountRepository.save(a);

        User u = new User(name);
        userRepository.save(u);

        a.setUser(u);

        return "redirect:/accounts";
    }
}
