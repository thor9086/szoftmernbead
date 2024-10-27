package com.example.topic1.controller;

import com.example.topic1.model.Italok;
import com.example.topic1.model.Person;
import com.example.topic1.service.RecommendationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/recommend")
public class RecommendationController {

    @Autowired
    private RecommendationService recommendationService;

    // Italok létrehozása (példaként)
    private List<Italok> createSampleDrinks() {
        List<Italok> italokList = new ArrayList<>();
        italokList.add(new Italok("Tatra", 1050, 72, 0.05, true));
        italokList.add(new Italok("Jagermeister", 800, 35, 0.05, true));
        italokList.add(new Italok("Beer", 550, 5, 0.5, false));
        italokList.add(new Italok("Viz", 400, 0, 0.5, false));
        italokList.add(new Italok("Bor", 700, 11, 0.5, false));
        return italokList;
    }

    @GetMapping
    public String showRecommendationsForm(Model model) {
        model.addAttribute("person", new Person());
        return "recommendForm";
    }

    @PostMapping
    public String getRecommendations(@ModelAttribute Person person, Model model) {
        List<Italok> recommendedDrinks = recommendationService.getRecommendations(person, createSampleDrinks());
        model.addAttribute("recommendedDrinks", recommendedDrinks);
        return "recommendResult";
    }
}
