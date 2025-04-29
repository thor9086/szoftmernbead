package com.example.topic1.service;

import com.example.topic1.model.Italok;
import com.example.topic1.model.Person;
import com.example.topic1.model.AlcoholCalculator;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class RecommendationService {

    public List<Italok> getRecommendations(Person person, List<Italok> italokList) {
        List<Italok> recommended = new ArrayList<>();
        double targetAlcohol = AlcoholCalculator.alcoholPersonCalc(person);
        double currentAlcohol = 0;
        int budgetLeft = person.getOsszeg();
        Random random = new Random();

        while (currentAlcohol < targetAlcohol && budgetLeft > 0) {
            List<Italok> affordableDrinks = new ArrayList<>();
            for (Italok ital : italokList) {
                if (ital.getPrice() <= budgetLeft) {
                    affordableDrinks.add(ital);
                }
            }

            if (!affordableDrinks.isEmpty()) {
                Italok selectedDrink = affordableDrinks.get(random.nextInt(affordableDrinks.size()));
                recommended.add(selectedDrink);
                currentAlcohol += AlcoholCalculator.alcoholDrinkCalc(selectedDrink);
                budgetLeft -= selectedDrink.getPrice();
            } else {
                break;
            }
        }

        return recommended;
    }
}

