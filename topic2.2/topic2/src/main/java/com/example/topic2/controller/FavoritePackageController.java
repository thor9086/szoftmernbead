//package com.example.topic2.controller;
//
//import com.example.topic2.model.FavoritePackage;
//import com.example.topic2.model.Package;
//import com.example.topic2.model.User;
//import com.example.topic2.repository.FavoritePackageRepository;
//import com.example.topic2.repository.UserRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//public class FavoritePackageController {
//
//    @Autowired
//    private UserRepository userRepository;
//
//    @Autowired
//    private FavoritePackageRepository favoritePackageRepository;
//
//    @PostMapping("/saveFavoritePackage")
//    public String saveFavoritePackage(@RequestBody Package pgk) {
//        String email = SecurityContextHolder.getContext().getAuthentication().getName();
//        User user = userRepository.findByEmail(email)
//                .orElseThrow(() -> new RuntimeException("Felhasználó nem található"));
//
//        // Kedvenc csomag hozzáadása
//        FavoritePackage favoritePackage = new FavoritePackage();
//        favoritePackage.setUser(user);
//        favoritePackage.setPackage(pkg);
//        favoritePackageRepository.save(favoritePackage);
//
//        return "Csomag elmentve";
//    }
//}
