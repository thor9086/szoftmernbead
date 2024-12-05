package com.example.topic2.controller;

import com.example.topic2.model.Drink;
import com.example.topic2.model.DrinkPackage;
import com.example.topic2.repository.DrinkPackageRepository;
import com.example.topic2.service.DrinkPackageService;
import com.example.topic2.service.UserPackageRelationshipService;
import com.example.topic2.service.UserService;
import jakarta.persistence.PostUpdate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;
import org.w3c.dom.events.Event;

import java.util.HashMap;
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
    public ResponseEntity<Map<String, Object>> addFavourite(@RequestParam Long packageId) {
        Map<String, Object> response = new HashMap<>();
        try {
            DrinkPackage drinkPackage = drinkPackageService.getDrinksByPackage(packageId);
            userPackageRelationshipService.saveUserPackageRelationship(drinkPackage.getId(), UserService.getCurrentUserByName());


            response.put("success", true);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", "Failed to add favourite");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }


}
