//package com.example.topic2.controller;
//
//import com.example.topic2.model.Drink;
//import com.example.topic2.model.DrinkPackage;
//import com.example.topic2.service.DrinkPackageService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/packages")
//public class DrinkPackageController {
//
//    @Autowired
//    private DrinkPackageService drinkPackageService;
//
//    @PostMapping("/create")
//    public ResponseEntity<String> createPackage(
//            @RequestParam String name,
//            @RequestParam List<Long> drinkIds) {
//        DrinkPackage newPackage = drinkPackageService.createPackage(name, drinkIds);
//        return ResponseEntity.ok("Package created with ID: " + newPackage.getTempId());
//    }
//
//    @GetMapping("/{id}/drinks")
//    public ResponseEntity<List<Drink>> getPackageDrinks(@PathVariable Long id) {
//        List<Drink> drinks = drinkPackageService.getDrinksByPackage(id);
//        return ResponseEntity.ok(drinks);
//    }
//}
