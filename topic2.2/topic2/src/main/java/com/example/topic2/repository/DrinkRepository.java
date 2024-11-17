package com.example.topic2.repository;

import com.example.topic2.model.Drink;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface DrinkRepository extends JpaRepository<Drink, Long> {
    List<Drink> findByIsShortDrink(boolean isShortDrink);
}