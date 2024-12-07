//package com.example.topic2.service;
//
//import com.example.topic2.model.Drink;
//import com.example.topic2.model.DrinkPackage;
//import com.example.topic2.model.UserPackageRelationship;
//import com.example.topic2.model.User;
//import com.example.topic2.repository.UserPackageRelationshipRepository;
//import com.example.topic2.repository.DrinkPackageRepository;
//import com.example.topic2.repository.UserRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//import java.util.stream.Collectors;
//
//import org.springframework.transaction.annotation.Transactional;
//
//@Service
//public class UserPackageRelationshipService {
//
//
//    @Autowired
//    private DrinkPackageRepository drinkPackageRepository;
//
//    @Autowired
//    private UserRepository userRepository;
//
//    @Autowired
//    private UserPackageRelationshipRepository userPackageRelationshipRepository;
//
//    @Autowired
//    private UserService userService;
//
//    public void saveUserPackageRelationship(Long packageId, String username) {
//        User user = userRepository.findByUsername(username)
//                .orElseThrow(() -> new IllegalArgumentException("User not found"));
//        DrinkPackage packageEntity = drinkPackageRepository.findById(packageId)
//                .orElseThrow(() -> new IllegalArgumentException("Package not found"));
//
//        UserPackageRelationship userPackageRelationship = new UserPackageRelationship();
//        userPackageRelationship.setUser(user);
//        userPackageRelationship.setSavedDrinkPackage(packageEntity);
//        userPackageRelationshipRepository.save(userPackageRelationship);
//    }
//
//    @Transactional
//    public void deleteUserPackageRelationship(Long packageId, String username) {
//        User user = userRepository.findByUsername(username)
//                .orElseThrow(() -> new IllegalArgumentException("User not found"));
//        DrinkPackage packageEntity = drinkPackageRepository.findById(packageId)
//                .orElseThrow(() -> new IllegalArgumentException("Package not found"));
//
//        Optional<UserPackageRelationship> relationship = userPackageRelationshipRepository
//                .findByUserAndSavedDrinkPackage(user, packageEntity);
//
//        if (relationship.isPresent()) {
//            userPackageRelationshipRepository.delete(relationship.get());
//
//        } else {
//            throw new IllegalArgumentException("Relationship not found");
//        }
//    }
//
//    public List<Drink> getSavedDrinksForCurrentUser() {
//        String username = UserService.getCurrentUserByName();
//        Long id = userService.getCurrentUserId();
//        if (username != null) {
//            Optional<User> user = userRepository.findByUsername(username);
//            if (user.isPresent()) {
//                List<UserPackageRelationship> relationships = userPackageRelationshipRepository.findByUserId(id);
//
//                List<Drink> savedDrinks = new ArrayList<>();
//                for (UserPackageRelationship relationship : relationships) {
//                    DrinkPackage savedDrinkPackage = relationship.getSavedDrinkPackage();
//                    savedDrinks.addAll(savedDrinkPackage.getDrinks());
//                }
//
//                return savedDrinks;
//            }
//        }
//        return new ArrayList<>();
//
////        Long currentUserId = userService.getCurrentUserId(); // Ezt feltételezem, hogy helyesen működik
////        List<UserPackageRelationship> relationships = userPackageRelationshipRepository.findByUserId(currentUserId);
////
////        return relationships.stream()
////                .flatMap(rel -> rel.getSavedDrinkPackage().getDrinks().stream())
////                .collect(Collectors.toList());
//    }
//}