package com.example.topic2.controller;

import com.example.topic2.model.Drink;
import com.example.topic2.model.DrinkPackage;
import com.example.topic2.model.Person;
import com.example.topic2.model.User;
import com.example.topic2.repository.DrinkPackageRepository;
import com.example.topic2.repository.DrinkRepository;
import com.example.topic2.repository.UserRepository;
import com.example.topic2.service.DrinkPackageService;
import com.example.topic2.service.UserPackageRelationshipService;
import com.example.topic2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.lang.module.ModuleFinder;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/favourite")
public class DrinkPackageController {

    @Autowired
    private DrinkPackageService drinkPackageService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private DrinkPackageRepository drinkPackageRepository;

    @Autowired
    private UserPackageRelationshipService userPackageRelationshipService;

    @Autowired
    private UserService userService;

    @GetMapping("/list")
    public String showFavourites(Model model) {
        User user = userRepository.findById(userService.getCurrentUserId()).orElse(null);

        if (user != null) {
            // A felhasználó kedvenc csomagjainak lekérése
            List<Drink> favouriteDrinks = userPackageRelationshipService.getSavedDrinksForCurrentUser();

            // A kedvenc italok hozzáadása a modellhez
            model.addAttribute("favouriteDrinks", favouriteDrinks);
        }

        return "favourite";
    }

    @PostMapping("/add")
    public String addToFavourites(@RequestParam("recommendedDrinks") List<Long> drinkIds, Model model) {
        List<Drink> selectedDrinks = drinkPackageService.getDrinksByIds(drinkIds);
        if (!selectedDrinks.isEmpty()) {
            DrinkPackage drinkPackage = new DrinkPackage();
            drinkPackage.setDrinks(selectedDrinks);
            drinkPackageRepository.save(drinkPackage);

            userPackageRelationshipService.saveUserPackageRelationship(drinkPackage.getId(), UserService.getCurrentUserByName());
        }

//        String params = drinkIds.stream()
//                .map(String::valueOf)
//                .reduce((a, b) -> a + "," + b)
//                .orElse("");
        return "redirect:/recommend/recommendResult";
    }
}
