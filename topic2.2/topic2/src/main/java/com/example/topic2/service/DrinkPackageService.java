//package com.example.topic2.service;
//
//import com.example.topic2.model.Drink;
//import com.example.topic2.model.DrinkPackage;
//import com.example.topic2.repository.DrinkRepository;
//import com.example.topic2.repository.DrinkPackageRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service
//public class DrinkPackageService {
//
//    @Autowired
//    private DrinkPackageRepository drinkPackageRepository;
//
//    @Autowired
//    private DrinkRepository drinkRepository;
//
//    public DrinkPackage createPackage(String name, List<Long> drinkIds) {
//        DrinkPackage packageEntity = new DrinkPackage();
//        packageEntity.setName(name);
//        List<Drink> drinks = drinkRepository.findAllById(drinkIds);
//        packageEntity.setDrinks(drinks);
//        return drinkPackageRepository.save(packageEntity);
//    }
//
//    public List<Drink> getDrinksByPackage(Long packageId) {
//        // Csomag lekérdezése az adatbázisból
//        DrinkPackage packageEntity = drinkPackageRepository.findById(packageId)
//                .orElseThrow(() -> new IllegalArgumentException("Package not found"));
//        // A csomaghoz tartozó italok visszaadása
//        return packageEntity.getDrinks(); // Helyesen a drinks mezőt hívjuk meg
//    }
//
//}
//
