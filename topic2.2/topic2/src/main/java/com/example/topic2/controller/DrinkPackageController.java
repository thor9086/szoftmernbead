package com.example.topic2.controller;

import com.example.topic2.model.Drink;
import com.example.topic2.model.DrinkPackage;
import com.example.topic2.repository.DrinkPackageRepository;
import com.example.topic2.service.DrinkPackageService;
import com.example.topic2.service.UserPackageRelationshipService;
import com.example.topic2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/favourite")
public class DrinkPackageController {

    @Autowired
    private DrinkPackageService drinkPackageService;

    @Autowired
    private DrinkPackageRepository drinkPackageRepository;

    @Autowired
    private UserPackageRelationshipService userPackageRelationshipService;


    @PostMapping("/add")
    public ResponseEntity<String> addToFavourites(@RequestBody Map<String, List<Long>> request) {
        List<Long> drinkIds = request.get("recommendedDrinks"); // A listázott italként kapott ID-k
        if (drinkIds != null && !drinkIds.isEmpty()) {
            List<Drink> selectedDrinks = drinkPackageService.getDrinksByIds(drinkIds); // Több ital lekérése az ID-k alapján
            if (!selectedDrinks.isEmpty()) {
                DrinkPackage drinkPackage = new DrinkPackage();
                drinkPackage.setDrinks(selectedDrinks); // Az összes ital hozzáadása a csomaghoz
                drinkPackageRepository.save(drinkPackage); // Az italcsomag mentése

                userPackageRelationshipService.saveUserPackageRelationship(drinkPackage.getId(), UserService.getCurrentUserByName());
                return ResponseEntity.ok("Drinks added to favourites.");
            }
        }
        return ResponseEntity.status(400).body("No drinks selected or invalid drink IDs.");
    }

}
