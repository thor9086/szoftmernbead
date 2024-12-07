package com.example.topic2.controller;

import com.example.topic2.model.Drink;
import com.example.topic2.model.User;
import com.example.topic2.repository.UserRepository;
//import com.example.topic2.service.UserPackageRelationshipService;
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

//    @Autowired
//    private UserPackageRelationshipService userPackageRelationshipService;


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

//    @GetMapping("/favourite/list")
//    public String showFavourites(@RequestParam List<Long> packageIds, Model model) {
//        User user = userRepository.findById(userService.getCurrentUserId()).orElse(null);
//
//        if (user != null) {
//            List<Drink> favouriteDrinks = userPackageRelationshipService.getSavedDrinksForCurrentUser();
//            System.out.println("Favourite Drinks: " + favouriteDrinks);
//            model.addAttribute("favouriteDrinks", favouriteDrinks);
//        }
//
//        return "favourite";
//    }
}