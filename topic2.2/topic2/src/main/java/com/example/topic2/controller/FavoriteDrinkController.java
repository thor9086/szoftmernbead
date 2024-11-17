//package com.example.topic2.controller;
//
//import com.example.topic2.model.FavoriteDrink;
//import com.example.topic2.model.Drink;
//import com.example.topic2.model.User;
//import com.example.topic2.repository.FavoriteDrinkRepository;
//import com.example.topic2.repository.UserRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//public class FavoriteDrinkController {
//
//    @Autowired
//    private UserRepository userRepository;
//
//    @Autowired
//    private FavoriteDrinkRepository favoriteDrinkRepository;
//
//    @PostMapping("/saveFavoriteDrink")
//    public String saveFavoriteDrink(@RequestBody Drink drink) {
//        String email = SecurityContextHolder.getContext().getAuthentication().getName();
//        User user = userRepository.findByEmail(email)
//                .orElseThrow(() -> new RuntimeException("Felhasználó nem található"));
//
//        // Kedvenc ital hozzáadása
//        FavoriteDrink favoriteDrink = new FavoriteDrink();
//        favoriteDrink.setUser(user);
//        favoriteDrink.setDrink(drink);
//        favoriteDrinkRepository.save(favoriteDrink);
//
//        return "Ital elmentve";
//    }
//}
