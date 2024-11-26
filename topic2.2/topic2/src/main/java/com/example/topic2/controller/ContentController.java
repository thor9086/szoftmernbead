package com.example.topic2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ContentController {

    @GetMapping("/login")
    public String login(@RequestParam(value = "logout", required = false) String logout, Model model){

        if (logout != null) {
            model.addAttribute("message", "Sikeresen kijelentkeztél.");
        }

        return "login";
    }

    @GetMapping("/register")
    public String signup(){
        return "register";
    }
}