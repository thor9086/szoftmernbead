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
import java.util.stream.Collectors;

@Controller
@RequestMapping("/list")
public class DrinkListController {

    @Autowired
    private DrinkService drinkService;

    @Autowired
    private UserService userService;

    @GetMapping("/drinklist")
    public String showFavouritePage(Model model) {
        List<Drink> drinks = drinkService.getAllDrinks();
        String name = userService.getCurrentUserByName();

        List<Drink> beerCider = drinks.stream()
                .filter(drink -> drink.getAlcoholContent() <= 6)
                .collect(Collectors.toList());

        List<Drink> cocktails = drinks.stream()
                .filter(drink -> drink.getAlcoholContent() > 6 && drink.getAlcoholContent() <= 20)
                .collect(Collectors.toList());

        List<Drink> liquors = drinks.stream()
                .filter(drink -> drink.getAlcoholContent() > 20)
                .collect(Collectors.toList());

        model.addAttribute("beerCider", beerCider);
        model.addAttribute("cocktails", cocktails);
        model.addAttribute("liquors", liquors);
        model.addAttribute("user", name);

        return "drinklist";
    }
}
