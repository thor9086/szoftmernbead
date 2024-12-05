package com.example.topic2;

import com.example.topic2.model.Drink;
import com.example.topic2.repository.DrinkRepository;
import com.example.topic2.service.DrinkService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class DrinkServiceTest {

    @Mock
    private DrinkRepository drinkRepository;

    @InjectMocks
    private DrinkService drinkService;

    public DrinkServiceTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllDrinks() {
        List<Drink> drinks = Arrays.asList(
                new Drink("Drink1", 5.0, 10.0, 0.5, false),
                new Drink("Drink2", 7.0, 15.0, 0.7, true)
        );

        when(drinkRepository.findAll()).thenReturn(drinks);

        List<Drink> result = drinkService.getAllDrinks();
        assertEquals(2, result.size());
        assertEquals("Drink1", result.get(0).getName());
        assertEquals("Drink2", result.get(1).getName());
    }

    @Test
    public void testGetDrinkById() {
        Drink drink = new Drink("Drink1", 5.0, 10.0, 0.5, false);

        when(drinkRepository.findById(1L)).thenReturn(Optional.of(drink));

        Optional<Drink> result = drinkService.getDrinkId(1L);
        assertEquals(true, result.isPresent());
        assertEquals("Drink1", result.get().getName());
    }
}

