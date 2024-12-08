package com.example.topic2.controller;


import com.example.topic2.model.Drink;
import com.example.topic2.model.DrinkPackage;
import com.example.topic2.model.Person;
import com.example.topic2.service.DrinkPackageService;
import com.example.topic2.service.DrinkService;
import com.example.topic2.service.RecommendationService;
import com.example.topic2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/recommend")
public class RecommendController {

    @Autowired
    private RecommendationService recommendationService;

    @Autowired
    private DrinkPackageService drinkPackageService;

    @Autowired
    private UserService userService;

    @GetMapping("/recommendForm")
    public String showRecommendForm(Model model) {
        model.addAttribute("person", new Person());
        model.addAttribute("user" , "Üdvözöllek " + userService.getCurrentUserByName() + "!");
        model.addAttribute("isLoggedIn", userService.isLoggedIn());
        return "recommendForm";
    }

    @GetMapping("/recommendResult")
    public String showRecommendResult(
            @RequestParam(value = "recommendedDrinks", required = false) String recommendedDrinks,
            Model model) {
        if (recommendedDrinks != null && !recommendedDrinks.isEmpty()) {
            List<Long> drinkIds = List.of(recommendedDrinks.split(","))
                    .stream()
                    .map(Long::valueOf)
                    .toList();
            List<Drink> drinks = drinkPackageService.getDrinksByIds(drinkIds);
            model.addAttribute("recommendedDrinks", drinks);
        }

        model.addAttribute("isLoggedIn", userService.isLoggedIn());


        return "recommendResult";
    }


    @PostMapping("/recommendResult")
    public String showRecommendations(@ModelAttribute Person person, Model model) {

        model.addAttribute("user" , userService.getCurrentUserByName());

        List<Drink> recommendedDrinks1 = recommendationService.getRecommendations(person, drinkPackageService.createPackage());
        List<Drink> recommendedDrinks2 = recommendationService.getRecommendations(person, drinkPackageService.createPackage());
        List<Drink> recommendedDrinks3 = recommendationService.getRecommendations(person, drinkPackageService.createPackage());

        DrinkPackage package1 = drinkPackageService.savePackage(recommendedDrinks1);
        DrinkPackage package2 = drinkPackageService.savePackage(recommendedDrinks2);
        DrinkPackage package3 = drinkPackageService.savePackage(recommendedDrinks3);

        model.addAttribute("recommendedDrinks1", recommendedDrinks1);
        model.addAttribute("recommendedDrinks2", recommendedDrinks2);
        model.addAttribute("recommendedDrinks3", recommendedDrinks3);

        model.addAttribute("package1", package1);
        model.addAttribute("package2", package2);
        model.addAttribute("package3", package3);

        model.addAttribute("isLoggedIn", userService.isLoggedIn());

        return "recommendResult";
    }
}
