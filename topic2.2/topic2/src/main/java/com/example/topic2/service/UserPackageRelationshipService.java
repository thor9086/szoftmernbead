package com.example.topic2.service;

import com.example.topic2.model.DrinkPackage;
import com.example.topic2.model.UserPackageRelationship;
import com.example.topic2.model.User;
import com.example.topic2.repository.UserPackageRelationshipRepository;
import com.example.topic2.repository.DrinkPackageRepository;
import com.example.topic2.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserPackageRelationshipService {

    @Autowired
    private UserPackageRelationshipRepository userPackageRelationshipRepository;

    @Autowired
    private DrinkPackageRepository drinkPackageRepository;

    @Autowired
    private UserRepository userRepository;

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


//    public List<DrinkPackage> getUserFavourites(String username) {
//        User user = userRepository.findByUsername(username)
//                .orElseThrow(() -> new IllegalArgumentException("Felhasználó nem található"));
//
//        return UserPackageRelationshipRepository.findByUserId(user);
//    }
}



