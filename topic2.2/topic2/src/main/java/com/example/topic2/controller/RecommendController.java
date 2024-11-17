package com.example.topic2.controller;


import com.example.topic2.model.Drink;
import com.example.topic2.model.Person;
import com.example.topic2.service.AlcoholCalculator;
import com.example.topic2.service.DrinkService;
import com.example.topic2.service.RecommendationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/recommend")
public class RecommendController {

    @Autowired
    private RecommendationService recommendationService;

    @Autowired
    private DrinkService drinkService;

    private List<Drink> createSampleDrinks() {
        List<Drink> drinks = drinkService.getAllDrinks();
        return drinks;

//        List<Drink> italokList = new ArrayList<>();
//        italokList.add(new Drink("Tatra", 1050, 72, 0.05, true));
//        italokList.add(new Drink("Jagermeister", 800, 35, 0.05, true));
//        italokList.add(new Drink("Beer", 550, 5, 0.5, false));
//        italokList.add(new Drink("Viz", 400, 0, 0.5, false));
//        italokList.add(new Drink("Bor", 700, 11, 0.5, false));
//
//        return italokList;
    }


    @GetMapping("/recommendForm")
    public String showRecommendForm(Model model) {
        model.addAttribute("person", new Person());
        return "recommendForm";
    }

    // A POST metódus a beérkező formadatok kezelésére
    @PostMapping("/recommendResult")
    public String getRecommendations(@ModelAttribute Person person, Model model) {
        List<Drink> recommendedDrinks = recommendationService.getRecommendations(person, createSampleDrinks());
        System.out.println(recommendedDrinks);
        model.addAttribute("recommendedDrinks", recommendedDrinks);
        model.addAttribute("requiredAlcohol" , AlcoholCalculator.alcoholPersonCalc(person));
        return "recommendResult";
    }
}
