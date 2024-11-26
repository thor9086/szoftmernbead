package com.example.topic2.repository;

import com.example.topic2.model.FavoriteDrink;
import com.example.topic2.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FavoriteDrinkRepository extends JpaRepository<FavoriteDrink, Long> {
    List<FavoriteDrink> findByUser(User user);
}
