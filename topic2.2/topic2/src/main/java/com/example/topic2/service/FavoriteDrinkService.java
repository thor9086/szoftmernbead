package com.example.topic2.service;

import com.example.topic2.model.Drink;
import com.example.topic2.model.FavoriteDrink;
import com.example.topic2.model.User;
import com.example.topic2.repository.FavoriteDrinkRepository;
import com.example.topic2.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FavoriteDrinkService {

    @Autowired
    private FavoriteDrinkRepository favoriteDrinkRepository;

    @Autowired
    private UserRepository userRepository;

    public void addFavorite(User user, Drink drink) {
        FavoriteDrink favorite = new FavoriteDrink();
        favorite.setUser(user);
        favorite.setDrink(drink);
        favoriteDrinkRepository.save(favorite);
    }


}

