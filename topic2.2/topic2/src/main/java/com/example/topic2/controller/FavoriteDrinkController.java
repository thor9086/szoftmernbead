package com.example.topic2.controller;

import com.example.topic2.model.FavoriteDrink;
import com.example.topic2.model.Drink;
import com.example.topic2.model.User;
import com.example.topic2.repository.FavoriteDrinkRepository;
import com.example.topic2.repository.UserRepository;
import com.example.topic2.service.DrinkService;
import com.example.topic2.service.FavoriteDrinkService;
import com.example.topic2.service.UserService;
import com.example.topic2.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/favorites")
public class FavoriteDrinkController {

    @Autowired
    private FavoriteDrinkService favoriteDrinkService;

    @PostMapping("/add")
    public ResponseEntity<String> addFavorite(@RequestParam Long drinkId, Principal principal) {
        String userEmail = principal.getName(); // A bejelentkezett felhasználó email-je
        favoriteDrinkService.addFavoriteDrink(userEmail, drinkId);
        return ResponseEntity.ok("Drink added to favorites");
    }

    @GetMapping("/list")
    public ResponseEntity<List<FavoriteDrink>> getFavorites(Principal principal) {
        String userEmail = principal.getName();
        List<FavoriteDrink> favorites = favoriteDrinkService.getFavoriteDrinks(userEmail);
        return ResponseEntity.ok(favorites);
    }
}


//    @PostMapping("/drinklist")
//    public ResponseEntity<String> addToFavorites(@RequestParam Long drinkId) {
//
//        String userEmail = UserService.getCurrentUser();
//        Optional<User> user = userRepository.findByEmail(userEmail);
//        Optional<Drink> drink = drinkService.getDrinkId(drinkId);
//
//        if (user.isPresent() && drink.isPresent()) {
//            FavoriteDrink favoriteDrink = new FavoriteDrink();
//            favoriteDrink.setUser(user.get());
//            favoriteDrink.setDrink(drink.get());
//            favoriteDrinkRepository.save(favoriteDrink);
//            return ResponseEntity.ok("Drink added to favorites.");
//        }
//
//        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid user or drink ID.");
//    }

