package com.example.topic2;

import com.example.topic2.model.Drink;
import com.example.topic2.model.Person;
import com.example.topic2.service.AlcoholCalculator;
import com.example.topic2.service.RecommendationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class RecommendationServiceTest {

    @Mock
    private AlcoholCalculator alcoholCalculator;

    @InjectMocks
    private RecommendationService recommendationService;

    private Person person;
    private Drink drink1, drink2, drink3;

    @BeforeEach
    public void setUp() {
        // Tesztitalok és személy inicializálása
        person = new Person(500, 2, 70, 1); // 500 forint, moderate drink, 70 kg, férfi
        drink1 = new Drink();
        drink1.setId(1L);
        drink1.setName("Beer");
        drink1.setAlcoholContent(5.0);
        drink1.setPrice(200);

        drink2 = new Drink();
        drink2.setId(2L);
        drink2.setName("Wine");
        drink2.setAlcoholContent(12.0);
        drink2.setPrice(300);

        drink3 = new Drink();
        drink3.setId(3L);
        drink3.setName("Whiskey");
        drink3.setAlcoholContent(40.0);
        drink3.setPrice(500);
    }

    @Test
    public void testGetRecommendations() {
        List<Drink> drinkList = Arrays.asList(drink1, drink2, drink3);
        List<Drink> recommendations = recommendationService.getRecommendations(person, drinkList);

        assertNotNull(recommendations);
        assertTrue(recommendations.size() > 0, "Ajánlott italok listája nem lehet üres.");
    }
}

