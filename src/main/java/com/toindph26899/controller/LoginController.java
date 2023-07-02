package com.toindph26899.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {

    @GetMapping("/login-form")
    public String loginForm() {
        return "pages/login";
    }

    @GetMapping("/register")
    public String register() {
        return null;
    }

    @GetMapping("/access-denied")
    public String accessDenied() {
        return "pages/access-denied";
    }

}
