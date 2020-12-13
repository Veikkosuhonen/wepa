package com.github.vesuvesu.wepa.controller;

import com.github.vesuvesu.wepa.account.Account;
import com.github.vesuvesu.wepa.account.AccountRepository;
import com.github.vesuvesu.wepa.account.Registration;
import com.github.vesuvesu.wepa.user.User;
import com.github.vesuvesu.wepa.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class AccountController {

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @ModelAttribute
    private Registration getRegistration() {
        return new Registration();
    }

    @GetMapping("/register")
    public String register() {
        return "register";
    }

    @GetMapping("/accounts")
    public String list(Model model) {
        model.addAttribute("accounts", accountRepository.findAll());
        return "accounts";
    }

    @Transactional
    @PostMapping("/register")
    public String addAccount(@Valid @ModelAttribute Registration registration, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "register";
        }

        if (accountRepository.findByUsername(registration.getUsername()) != null) {
            bindingResult.addError(new FieldError("registration", "username", "The username '"+registration.getUsername()+"' is taken"));
            return "register";
        }

        if (userRepository.findByName(registration.getName()) != null) {
            bindingResult.addError(new FieldError("registration", "name", "The name '"+registration.getName()+"' is taken"));
            return "register";
        }

        Account a = new Account(registration.getUsername(), passwordEncoder.encode(registration.getPassword()));
        accountRepository.save(a);

        User u = new User(registration.getName());
        userRepository.save(u);

        a.setUser(u);

        return "redirect:/profile";
    }
}
