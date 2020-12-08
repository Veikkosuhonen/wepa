package com.github.vesuvesu.wepa;

import com.github.vesuvesu.wepa.user.User;
import com.github.vesuvesu.wepa.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class SearchController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/search")
    public String searchPage(Model model) {
        model.addAttribute("users", new ArrayList<String>());
        return "search";
    }

    @PostMapping("/search")
    public String searchPage(@RequestParam String entry) {
        return "redirect:/search/" + entry;
    }

    @GetMapping("/search/{entry}")
    public String searchResults(@PathVariable String entry, Model model) {
        List<User> results = userRepository.findByNameContaining(entry);
        model.addAttribute("users", results);
        return "search";
    }
}
