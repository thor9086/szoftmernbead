package com.example.topic2.repository;

import com.example.topic2.model.DrinkPackage;
import com.example.topic2.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DrinkPackageRepository extends JpaRepository<DrinkPackage, Long> {

    List<DrinkPackage> findByUsers(User user);
}
