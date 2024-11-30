package com.example.topic2.controller;

import com.example.topic2.model.Drink;
import com.example.topic2.service.DrinkService;
import com.example.topic2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/list")
public class DrinkListController {

    @Autowired
    DrinkService drinkService;

    @Autowired
    UserService userService;

    @GetMapping("/drinklist")
    public String showFavouritePage(Model model){
        List<Drink> drinks = drinkService.getAllDrinks();
        String name = userService.getCurrentUser();
        model.addAttribute("drinklist" , drinks);
        model.addAttribute("user" , name);
        return "drinklist";
    }
}
