package com.example.topic2.controller;

import com.example.topic2.model.User;
import com.example.topic2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public String showLoginPage() {
        return "login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String email, @RequestParam String password, Model model) {
        return userService.findByEmail(email)
                .filter(user -> user.getPassword().equals(password))
                .map(user -> "redirect:/recommend")
                .orElse("login");
    }

    @GetMapping("/register")
    public String showRegistrationPage() {
        return "register";
    }

    @PostMapping("/register")
    public String register(@RequestParam String email, @RequestParam String password) {
        userService.saveUser(new User(email, password));
        return "redirect:/login";
    }
}
