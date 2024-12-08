package com.example.topic2.service;

import com.example.topic2.model.Drink;
import com.example.topic2.repository.DrinkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class DrinkService {

    @Autowired
    private DrinkRepository drinkRepository;

    public List<Drink> getAllDrinks() {
        return drinkRepository.findAll();
    }

    public Optional<Drink> getDrinkId(Long id) {
        return drinkRepository.findById(id);
    }

}