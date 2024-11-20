package com.example.topic2.controller;

import com.example.topic2.model.FavoriteDrink;
import com.example.topic2.model.Drink;
import com.example.topic2.model.User;
import com.example.topic2.repository.FavoriteDrinkRepository;
import com.example.topic2.repository.UserRepository;
import com.example.topic2.service.DrinkService;
import com.example.topic2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/favorites")
public class FavoriteDrinkController {

    @Autowired
    private DrinkService drinkService;

    @Autowired
    private FavoriteDrinkRepository favoriteDrinkRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @GetMapping("/drink-list")
    public String showAllDrinks(Model model) {
        model.addAttribute("allDrinks" , drinkService.getAllDrinks());
        return "drink-list";
    }

    @GetMapping("/fav-list")
    public String getFavoriteDrinks(Model model) {
        model.addAttribute("favoriteDrinks" , favoriteDrinkRepository.findAll());
        return "favorites";
    }

    @PostMapping("/drink-list")
    public ResponseEntity<String> addToFavorites(@RequestParam Long drinkId) {

        String userEmail = UserService.getCurrentUser();
        Optional<User> user = userRepository.findByEmail(userEmail);
        Optional<Drink> drink = drinkService.getDrinkId(drinkId);

        if (user.isPresent() && drink.isPresent()) {
            FavoriteDrink favoriteDrink = new FavoriteDrink();
            favoriteDrink.setUser(user.get());
            favoriteDrink.setDrink(drink.get());
            favoriteDrinkRepository.save(favoriteDrink);
            return ResponseEntity.ok("Drink added to favorites.");
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid user or drink ID.");
    }
}
