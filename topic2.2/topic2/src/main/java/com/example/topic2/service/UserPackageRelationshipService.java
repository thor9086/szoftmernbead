package com.example.topic2.service;

import com.example.topic2.model.Drink;
import com.example.topic2.model.DrinkPackage;
import com.example.topic2.model.UserPackageRelationship;
import com.example.topic2.model.User;
import com.example.topic2.repository.UserPackageRelationshipRepository;
import com.example.topic2.repository.DrinkPackageRepository;
import com.example.topic2.repository.UserRepository;
import com.example.topic2.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserPackageRelationshipService {



    @Autowired
    private DrinkPackageRepository drinkPackageRepository;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserPackageRelationshipRepository userPackageRelationshipRepository;



    public void saveUserPackageRelationship(Long packageId, String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        DrinkPackage packageEntity = drinkPackageRepository.findById(packageId)
                .orElseThrow(() -> new IllegalArgumentException("Package not found"));

        UserPackageRelationship userPackageRelationship = new UserPackageRelationship();
        userPackageRelationship.setUser(user);
        userPackageRelationship.setSavedDrinkPackage(packageEntity);
        userPackageRelationshipRepository.save(userPackageRelationship);
    }
    @Transactional
    public void deleteUserPackageRelationship(Long packageId, String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        DrinkPackage packageEntity = drinkPackageRepository.findById(packageId)
                .orElseThrow(() -> new IllegalArgumentException("Package not found"));

        Optional<UserPackageRelationship> relationship = userPackageRelationshipRepository
                .findByUserAndSavedDrinkPackage(user, packageEntity);

        if (relationship.isPresent()) {
            userPackageRelationshipRepository.delete(relationship.get());

        } else {
            throw new IllegalArgumentException("Relationship not found");
        }
    }

//    public List<DrinkPackage> getUserFavourites(String username) {
//        User user = userRepository.findByUsername(username)
//                .orElseThrow(() -> new IllegalArgumentException("Felhasználó nem található"));
//
//        return UserPackageRelationshipRepository.findByUserId(user);
//    }

    public List<Drink> getSavedDrinksForCurrentUser() {
        // Lekérjük a jelenlegi bejelentkezett felhasználó nevét
        String username = UserUtils.getCurrentUserByName();
        if (username != null) {
            // Kikeressük a felhasználót
            Optional<User> user = userRepository.findByUsername(username);
            if (user.isPresent()) {
                // A UserPackageRelationship-repository segítségével lekérjük az összes elmentett italcsomagot a felhasználóhoz
                List<UserPackageRelationship> relationships = userPackageRelationshipRepository.findByUser(user.get());

                List<Drink> savedDrinks = new ArrayList<>();
                // Iterálunk az elmentett italcsomagokon és összegyűjtjük az italokat
                for (UserPackageRelationship relationship : relationships) {
                    DrinkPackage savedDrinkPackage = relationship.getSavedDrinkPackage();
                    savedDrinks.addAll(savedDrinkPackage.getDrinks());
                }

                return savedDrinks;  // Visszatérünk az összes elmentett italral
            }
        }
        return new ArrayList<>();  // Ha nincs bejelentkezett felhasználó, vagy nem találunk italokat, visszaadunk egy üres listát
    }
}




