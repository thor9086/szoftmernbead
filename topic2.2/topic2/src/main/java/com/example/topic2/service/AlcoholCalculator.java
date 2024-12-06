package com.example.topic2.service;

import com.example.topic2.model.Drink;
import com.example.topic2.model.Person;

public class AlcoholCalculator {

    // Widmark együtthatók
    private static final double MALE_RATIO = 0.73;
    private static final double FEMALE_RATIO = 0.66;

    // BAC határok különböző becsiccsenési szintekre
    private static final double BAC_SLIGHT = 0.05;
    private static final double BAC_MODERATE = 0.10;
    private static final double BAC_HEAVY = 0.15;

    public static double alcoholPersonCalc(Person person) {

        int gender = person.getGender();
        double weightKg = person.getWeight();
        int level = person.getMeasure();
        // Súly átváltása fontba (1 kg = 2.20462 font)
        double weightLbs = weightKg * 2.20462;
        double r = gender == 1 ? MALE_RATIO : FEMALE_RATIO;

        double targetBAC = 0;
        switch (level) {
            case 1:
                targetBAC = BAC_SLIGHT;
                break;
            case 2:
                targetBAC = BAC_MODERATE;
                break;
            case 3:
                targetBAC = BAC_HEAVY;
                break;
            default:
                // HIBA - itt az oldalra kell kiirni h "Érvénytelen..." nem sout-ra.
                System.out.println("Érvénytelen választás.");
                return 0;
        }
        // Szükséges alkoholmennyiség unciában a cél BAC eléréséhez (idő nullára tétele, mert becsiccsenés a cél)
        double requiredAlcoholOz = (targetBAC * weightLbs * r) / 5.14;
        // Átváltás grammra (1 uncia = 28.3495 g)
        double requiredAlcoholGrams = requiredAlcoholOz * 28.3495;

        return requiredAlcoholGrams;
    }

    public static double alcoholDrinkCalc(Drink drink) {

        double volumeLiters = drink.getVolume();
        double abv = drink.getAlcoholContent();
        double volumeMl = volumeLiters * 1000;
        double alcoholGrams = volumeMl * (abv / 100) * 0.789;

        return alcoholGrams;
    }
}
