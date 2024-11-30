package com.example.topic2.service;

import com.example.topic2.model.Drink;
import com.example.topic2.model.DrinkPackage;
import com.example.topic2.model.Person;
import com.example.topic2.repository.DrinkRepository;
import com.example.topic2.repository.DrinkPackageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DrinkPackageService {

    @Autowired
    private DrinkPackageRepository drinkPackageRepository;

    @Autowired
    private DrinkRepository drinkRepository;

    @Autowired
    private DrinkService drinkService;

    @Autowired
    private RecommendationService recommendationService;


    public List<Drink> createPackage() {
        List<Drink> drinks = drinkService.getAllDrinks();
        return drinks;
    }

    public List<DrinkPackage> createRecommendPackages(Person person) {

        List<DrinkPackage> recommendedPackages = List.of(
                new DrinkPackage(recommendationService.getRecommendations(person, createPackage())),
                new DrinkPackage(recommendationService.getRecommendations(person, createPackage())),
                new DrinkPackage(recommendationService.getRecommendations(person, createPackage()))
        );

        return recommendedPackages;
    }

    public List<Drink> getDrinksByIds(List<Long> drinkIds) {
        return drinkRepository.findAllById(drinkIds);
    }

    public DrinkPackage savePackage(List<Drink> listOfDrinks) {
        DrinkPackage packageEntity = new DrinkPackage();
        packageEntity.setDrinks(listOfDrinks);
        return drinkPackageRepository.save(packageEntity);
    }

    public List<Drink> getDrinksByPackage(Long packageId) {
        // Csomag lekérdezése az adatbázisból
        DrinkPackage packageEntity = drinkPackageRepository.findById(packageId)
                .orElseThrow(() -> new IllegalArgumentException("Package not found"));
        // A csomaghoz tartozó italok visszaadása
        return packageEntity.getDrinks(); // Helyesen a drinks mezőt hívjuk meg
    }

}

