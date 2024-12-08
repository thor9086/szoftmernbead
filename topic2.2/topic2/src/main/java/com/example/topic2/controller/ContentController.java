package com.example.topic2.controller;

import com.example.topic2.model.DrinkPackage;
import com.example.topic2.model.User;
import com.example.topic2.repository.UserRepository;
import com.example.topic2.service.DrinkPackageService;
import com.example.topic2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class ContentController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private DrinkPackageService drinkPackageService;


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

    @GetMapping("")
    public String showHomepage(){
        return "index";
    }


    @GetMapping("/user/drink-packages")
    public String showUserDrinkPackages(Model model) {
        Long userId = userService.getCurrentUserId();
        User user = userService.getUserById(userId);

        if (user != null) {
            List<DrinkPackage> drinkPackages = drinkPackageService.getDrinkPackagesByUser(user);
            model.addAttribute("drinkPackages", drinkPackages);
        }

        return "favourite";
    }
}