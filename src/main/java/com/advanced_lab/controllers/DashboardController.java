package com.advanced_lab.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashboardController {
    @GetMapping("/login")
    public String login() {
        return "admin-welcome";  // This assumes you have a login.html in your templates directory
    }

}
