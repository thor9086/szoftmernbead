package com.example.topic2.controller;


import com.example.topic2.model.Drink;
//import com.example.topic2.model.DrinkPackage;
import com.example.topic2.model.DrinkPackage;
import com.example.topic2.model.Person;
import com.example.topic2.model.User;
import com.example.topic2.service.DrinkPackageService;
import com.example.topic2.service.DrinkService;
import com.example.topic2.service.RecommendationService;
import com.example.topic2.service.UserService;
import com.example.topic2.utils.UserUtils;
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
    private DrinkService drinkService;

    @Autowired
    private DrinkPackageService drinkPackageService;

    @Autowired
    private UserService userService;


    @GetMapping("/recommendForm")
    public String showRecommendForm(Model model) {
        model.addAttribute("person", new Person());
        model.addAttribute("user" , userService.getCurrentUserByName());
        return "recommendForm";
    }

    @GetMapping("/recommendResult")
    public String showRecommendResult(
            @RequestParam(value = "recommendedDrinks", required = false) String recommendedDrinks,
            Model model) {
        if (recommendedDrinks != null && !recommendedDrinks.isEmpty()) {
            // Az italok azonosítóinak feldolgozása
            List<Long> drinkIds = List.of(recommendedDrinks.split(","))
                    .stream()
                    .map(Long::valueOf)
                    .toList();
            List<Drink> drinks = drinkPackageService.getDrinksByIds(drinkIds);
            model.addAttribute("recommendedDrinks", drinks);
        }
        return "recommendResult";
    }


    @PostMapping("/recommendResult")
    public String showRecommendations(@ModelAttribute Person person, Model model) {
        model.addAttribute("user" , userService.getCurrentUserByName());
        List<Drink> recommendedDrinks1 = recommendationService.getRecommendations(person, drinkPackageService.createPackage());
        List<Drink> recommendedDrinks2 = recommendationService.getRecommendations(person, drinkPackageService.createPackage());
        List<Drink> recommendedDrinks3 = recommendationService.getRecommendations(person, drinkPackageService.createPackage());

        model.addAttribute("recommendedDrinks1", recommendedDrinks1);
        model.addAttribute("recommendedDrinks2", recommendedDrinks2);
        model.addAttribute("recommendedDrinks3", recommendedDrinks3);

//        List<DrinkPackage> recommendedPackages = List.of(
//                new DrinkPackage(recommendationService.getRecommendations(person, drinkPackageService.createPackage())),
//                new DrinkPackage(recommendationService.getRecommendations(person, drinkPackageService.createPackage())),
//                new DrinkPackage(recommendationService.getRecommendations(person, drinkPackageService.createPackage()))
//        );
//
//        model.addAttribute("recommendedPackages", recommendedPackages);

        return "recommendResult";
    }
}
