//package com.example.topic2.service;
//
//import com.example.topic2.model.DrinkPackage;
//import com.example.topic2.model.Favourite;
//import com.example.topic2.model.User;
//import com.example.topic2.repository.FavouriteRepository;
//import com.example.topic2.repository.DrinkPackageRepository;
//import com.example.topic2.repository.UserRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service
//public class FavouriteService {
//
//    @Autowired
//    private FavouriteRepository favouriteRepository;
//
//    @Autowired
//    private DrinkPackageRepository drinkPackageRepository;
//
//    @Autowired
//    private UserRepository userRepository;
//
////    public void saveFavourite(Long packageId, String userEmail) {
////        User user = userRepository.findByEmail(userEmail)
////                .orElseThrow(() -> new IllegalArgumentException("User not found"));
////        DrinkPackage packageEntity = drinkPackageRepository.findById(packageId)
////                .orElseThrow(() -> new IllegalArgumentException("Package not found"));
////
////        Favourite favourite = new Favourite();
////        favourite.setUser(user);
////        favourite.setSavedDrinkPackage(packageEntity);
////        favouriteRepository.save(favourite);
////    }
//
//    public void saveTemporaryPackageAsFavourite(DrinkPackage tempPackage, String userEmail) {
//        User user = userRepository.findByEmail(userEmail)
//                .orElseThrow(() -> new IllegalArgumentException("User not found"));
//
//        Favourite favourite = new Favourite();
//        favourite.setUser(user);
//        favourite.setSavedDrinkPackage(tempPackage); // Ezt mentheted adatbázisban, ha szükséges
//
//        favouriteRepository.save(favourite);
//    }
//
//    public List<Favourite> getUserFavourites(String userEmail) {
//        User user = userRepository.findByEmail(userEmail)
//                .orElseThrow(() -> new IllegalArgumentException("Felhasználó nem található"));
//        return favouriteRepository.findByUser(user);
//    }
//}
//
//
//
