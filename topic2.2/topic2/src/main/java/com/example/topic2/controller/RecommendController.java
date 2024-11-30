package com.example.topic2.controller;


import com.example.topic2.model.Drink;
//import com.example.topic2.model.DrinkPackage;
import com.example.topic2.model.Person;
import com.example.topic2.service.DrinkPackageService;
import com.example.topic2.service.DrinkService;
import com.example.topic2.service.RecommendationService;
import com.example.topic2.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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

//    private List<Drink> createSampleDrinks() {
//        List<Drink> drinks = drinkService.getAllDrinks();
//        return drinks;
//    }


    @GetMapping("/recommendForm")
    public String showRecommendForm(Model model) {
        model.addAttribute("person", new Person());
        model.addAttribute("user" , UserUtils.getCurrentUserByName());
        return "recommendForm";
    }



    // A POST metódus a beérkező formadatok kezelésére
    @PostMapping("/recommendResult")
    public String getRecommendations(@ModelAttribute Person person, Model model) {
        model.addAttribute("user" , UserUtils.getCurrentUserByName());
        List<Drink> recommendedDrinks1 = recommendationService.getRecommendations(person, drinkPackageService.createPackage());
        List<Drink> recommendedDrinks2 = recommendationService.getRecommendations(person, drinkPackageService.createPackage());
        List<Drink> recommendedDrinks3 = recommendationService.getRecommendations(person, drinkPackageService.createPackage());

        model.addAttribute("recommendedDrinks1", recommendedDrinks1);
        model.addAttribute("recommendedDrinks2", recommendedDrinks2);
        model.addAttribute("recommendedDrinks3", recommendedDrinks3);


//        // Példacsomagok létrehozása
//        List<DrinkPackage> recommendedPackages = List.of(
//                new DrinkPackage("Csomag 1", recommendationService.getRecommendations(person, createSampleDrinks())),
//                new DrinkPackage("Csomag 2", recommendationService.getRecommendations(person, createSampleDrinks())),
//                new DrinkPackage("Csomag 3", recommendationService.getRecommendations(person, createSampleDrinks()))
//        );
//
//        model.addAttribute("recommendedPackages", drinkPackageService.createRecommendPackages(person));

        return "recommendResult";
    }
}
