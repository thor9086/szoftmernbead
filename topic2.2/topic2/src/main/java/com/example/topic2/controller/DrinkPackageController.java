package com.example.topic2.test;

import com.example.topic2.model.DrinkPackage;
import com.example.topic2.model.User;
import com.example.topic2.service.DrinkPackageService;
import com.example.topic2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/favourite")
public class DrinkPackageController {

    @Autowired
    private UserService userService;

    @Autowired
    private DrinkPackageService drinkPackageService;

    // Kedvenc csomag hozzáadása
    @PostMapping("/add")
    public ResponseEntity<?> addFavourite(@RequestParam Long packageId, Authentication authentication) {
        String username = authentication.getName();
        User user = userService.findByUsername(username);

        Optional<DrinkPackage> drinkPackageOptional = drinkPackageService.findById(packageId);
        if (drinkPackageOptional.isEmpty()) {
            return ResponseEntity.badRequest().body("Csomag nem található");
        }

        DrinkPackage drinkPackage = drinkPackageOptional.get();
        user.getDrinkPackages().add(drinkPackage);
        userService.saveUser(user);

        return ResponseEntity.ok().body("{\"success\": true}");
    }

    // Kedvenc csomag eltávolítása
    @PostMapping("/remove")
    public ResponseEntity<?> removeFavourite(@RequestParam Long packageId, Authentication authentication) {
        String username = authentication.getName();
        User user = userService.findByUsername(username);

        Optional<DrinkPackage> drinkPackageOptional = drinkPackageService.findById(packageId);
        if (drinkPackageOptional.isEmpty()) {
            return ResponseEntity.badRequest().body("Csomag nem található");
        }

        DrinkPackage drinkPackage = drinkPackageOptional.get();
        user.getDrinkPackages().remove(drinkPackage);
        userService.saveUser(user);

        return ResponseEntity.ok().body("{\"success\": true}");
    }
}
