package com.example.topic2.service;

import com.example.topic2.model.Drink;
import com.example.topic2.model.FavoriteDrink;
import com.example.topic2.model.User;
import com.example.topic2.repository.DrinkRepository;
import com.example.topic2.repository.FavoriteDrinkRepository;
import com.example.topic2.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FavoriteDrinkService {

    @Autowired
    private FavoriteDrinkRepository favoriteDrinkRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private DrinkRepository drinkRepository;

    public void addFavoriteDrink(String userEmail, Long drinkId) {
        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        Drink drink = drinkRepository.findById(drinkId)
                .orElseThrow(() -> new IllegalArgumentException("Drink not found"));

        FavoriteDrink favoriteDrink = new FavoriteDrink();
        favoriteDrink.setUser(user);
        favoriteDrink.setDrink(drink);

        favoriteDrinkRepository.save(favoriteDrink);
    }

    public List<FavoriteDrink> getFavoriteDrinks(String userEmail) {
        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return favoriteDrinkRepository.findByUser(user);
    }
}


