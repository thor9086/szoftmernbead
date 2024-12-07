package com.example.topic2.service;

import com.example.topic2.model.Drink;
import com.example.topic2.model.DrinkPackage;
import com.example.topic2.model.User;
import com.example.topic2.repository.DrinkRepository;
import com.example.topic2.repository.DrinkPackageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public List<Drink> getDrinksByIds(List<Long> drinkIds) {
        return drinkRepository.findAllById(drinkIds);
    }

    public Optional<DrinkPackage> findById(Long id) {
        return drinkPackageRepository.findById(id);
    }

    public DrinkPackage savePackage(List<Drink> listOfDrinks) {
        DrinkPackage packageEntity = new DrinkPackage();
        packageEntity.setDrinks(listOfDrinks);
        return drinkPackageRepository.save(packageEntity);
    }

    public List<DrinkPackage> getDrinkPackagesByUser(User user) {
        return drinkPackageRepository.findByUsers(user);
    }

}

