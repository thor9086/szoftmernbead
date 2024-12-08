package com.example.topic2;

import com.example.topic2.model.Drink;
import com.example.topic2.model.Person;
import com.example.topic2.service.AlcoholCalculator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AlcoholCalculatorTest {

    @Test
    public void testAlcoholPersonCalc_Male() {
        Person person = new Person(500, 2, 70, 1); // 500 forint, moderate drink, 70 kg, férfi
        double alcoholRequired = AlcoholCalculator.alcoholPersonCalc(person);

        assertTrue(alcoholRequired > 0, "Alkohol mennyisége nem lehet nulla.");
    }

    @Test
    public void testAlcoholPersonCalc_Female() {
        Person person = new Person(500, 2, 60, 0); // 500 forint, moderate drink, 60 kg, nő
        double alcoholRequired = AlcoholCalculator.alcoholPersonCalc(person);

        assertTrue(alcoholRequired > 0, "Alkohol mennyisége nem lehet nulla.");
    }

    @Test
    public void testAlcoholDrinkCalc() {
        Drink drink = new Drink();
        drink.setVolume(0.5); // 0.5 liter
        drink.setAlcoholContent(5.0); // 5% alkohol
        double alcoholGrams = AlcoholCalculator.alcoholDrinkCalc(drink);

        assertTrue(alcoholGrams > 0, "Alkohol mennyisége nem lehet nulla.");
    }
}
