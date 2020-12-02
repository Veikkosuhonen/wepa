package com.github.vesuvesu.wepa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class SearchController {

    @Autowired
    private AccountRepository accountRepository;

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
        List<Account> results = accountRepository.findByNameContaining(entry, PageRequest.of(0, 10)).getContent();
        model.addAttribute("users", results.stream().map(account -> account.getName()).collect(Collectors.toList()));
        return "search";
    }
}
