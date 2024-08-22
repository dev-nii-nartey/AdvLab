package com.advanced_lab.controller;

import com.advanced_lab.security.XssUtils;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthorizedController {

    @GetMapping("/authorized")
    public String authorized(Authentication authentication, Model model) {
        boolean isAdmin = authentication.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"));
        model.addAttribute("isAdmin", isAdmin);
        return "authorized";
    }

    @GetMapping("/authorized/admin")
    public String authorizedAdmin(Authentication authentication) {
        if (authentication.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"))) {
            return "authorized-admin";
        } else {
            return "redirect:/authorized?error";
        }
    }

    @PostMapping("/authorized/userMessage")
    public String userMessage(@RequestParam String userInput, Model model) {
        String encodedMessage = XssUtils.encodeHtml(userInput);
        model.addAttribute("originalMessage", userInput);
        model.addAttribute("encodedMessage", encodedMessage);
        return "user-message";
    }


    @GetMapping("/user/welcome")
    public String userWelcome(Model model) {
        model.addAttribute("message", "Welcome! You are a Customer");
        return "user-welcome";
    }

    @GetMapping("/admin/welcome")
    public String adminWelcome(Model model) {
        model.addAttribute("message", "Welcome! You are an Admin");
        return "admin-welcome";
    }
}