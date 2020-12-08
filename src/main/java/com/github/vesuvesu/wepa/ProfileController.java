package com.github.vesuvesu.wepa;

import com.github.vesuvesu.wepa.account.AccountRepository;
import com.github.vesuvesu.wepa.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProfileController {

    @Autowired
    private AccountRepository accountRepository;

    @Secured("USER")
    @GetMapping("/myprofile")
    public String userView(Model model) {

        User user = accountRepository.findByUsername(SecurityContextHolder.getContext()
                .getAuthentication().getName()).getUser();

        model.addAttribute("user", user);
        return "myprofile";
    }
}
