package com.advanced_lab.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashboardController {
    @GetMapping("/login")
    public String login() {
        return "admin-welcome";
    }

}
