package com.example.topic2.controller;

//import com.example.topic2.model.DrinkPackage;
//import com.example.topic2.model.Favourite;
//import com.example.topic2.service.FavouriteService;
//import com.example.topic2.utils.TempPackageStore;
import com.example.topic2.model.Drink;
import com.example.topic2.model.User;
import com.example.topic2.service.DrinkService;
import com.example.topic2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/favourite")
public class FavouriteController {

    @Autowired
    DrinkService drinkService;

    @Autowired
    UserService userService;

    @GetMapping("/drinklist")
    public String showFavouritePage(Model model){
        List<Drink> drinks = drinkService.getAllDrinks();
        String name = userService.getCurrentUser();
        model.addAttribute("drinklist" , drinks);
        model.addAttribute("user" , name);
        return "drinklist";
    }

//    @Autowired
//    private FavouriteService favouriteService;
//
//    @Autowired
//    private TempPackageStore tempPackageStore;
//
//    // Kedvencek mentése
//    @PostMapping("/add")
//    public String addFavourite(@RequestParam String packageId, @RequestParam String userEmail) {
//        // Átmeneti csomag alapján végrehajtott logika
//        DrinkPackage tempPackage = tempPackageStore.findPackageById(packageId);
//        favouriteService.saveTemporaryPackageAsFavourite(tempPackage, userEmail);
//
//        return "redirect:/recommendResult";
//    }
//
//    // Felhasználó kedvenc csomagjainak listázása
//    @GetMapping("/list")
//    public String getUserFavourites(Principal principal, Model model) {
//        String userEmail = principal.getName();
//        List<Favourite> favourites = favouriteService.getUserFavourites(userEmail);
//        model.addAttribute("userFavourites", favourites);
//        return "drinklist"; // Visszatérés a "kedvenc csomagok" oldalra
//    }
}