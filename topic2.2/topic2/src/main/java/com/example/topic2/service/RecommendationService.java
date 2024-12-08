package com.example.topic2.service;

import com.example.topic2.model.Drink;
import com.example.topic2.model.Person;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class RecommendationService {

    public List<Drink> getRecommendations(Person person, List<Drink> italokList) {

        List<Drink> slightDrinks = new ArrayList<>();
        List<Drink> moderateDrinks = new ArrayList<>();
        List<Drink> strongDrinks = new ArrayList<>();

        for(Drink ital : italokList) {
            if(!ital.isShortDrink() && ital.getAlcoholContent() < 6) {
                slightDrinks.add(ital);
            } else if(ital.getAlcoholContent() >= 5 && ital.getAlcoholContent() <= 40 ){
                moderateDrinks.add(ital);
            } else if(ital.isShortDrink() && ital.getAlcoholContent() > 6) {
                strongDrinks.add(ital);
            }
        }

        List<Drink> recommended = new ArrayList<>();
        Integer strength = person.getMeasure();

        if(strength == 1){
            recommended = getDrinks(slightDrinks, person);

        } else if (strength == 2) {
            recommended = getDrinks(moderateDrinks, person);
        } else if (strength == 3) {
            recommended = getDrinks(strongDrinks, person);
        }

        return recommended;
    }

    private List<Drink> getDrinks(List<Drink> drinks, Person person) {

        double targetAlcohol = AlcoholCalculator.alcoholPersonCalc(person);
        double currentAlcohol = 0;
        int budgetLeft = person.getAmount();
        Random random = new Random();

        List<Drink> recommended = new ArrayList<>();
        while (currentAlcohol < targetAlcohol && budgetLeft > 0) {
            List<Drink> affordableDrinks = new ArrayList<>();
            for (Drink ital : drinks) {
                if (ital.getPrice() <= budgetLeft) {
                    affordableDrinks.add(ital);
                }
            }
            if (!affordableDrinks.isEmpty()) {
                Drink selectedDrink = affordableDrinks.get(random.nextInt(affordableDrinks.size()));
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

