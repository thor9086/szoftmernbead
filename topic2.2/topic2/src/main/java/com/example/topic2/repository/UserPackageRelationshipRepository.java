//package com.example.topic2.repository;
//
//import com.example.topic2.model.DrinkPackage;
//import com.example.topic2.model.UserPackageRelationship;
//import com.example.topic2.model.User;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.stereotype.Repository;
//
//import java.util.List;
//import java.util.Optional;
//
//@Repository
//public interface UserPackageRelationshipRepository extends JpaRepository<UserPackageRelationship, Long> {
//    List<UserPackageRelationship> findByUserId(Long id);
//    Optional<UserPackageRelationship> findByUserAndSavedDrinkPackage(User user, DrinkPackage drinkPackage);
//
//}
