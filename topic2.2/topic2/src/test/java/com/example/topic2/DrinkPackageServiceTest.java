package com.example.topic2.service;

import com.example.topic2.model.Drink;
import com.example.topic2.model.DrinkPackage;
import com.example.topic2.repository.DrinkPackageRepository;
import com.example.topic2.repository.DrinkRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class DrinkPackageServiceTest {

    @Mock
    private DrinkPackageRepository drinkPackageRepository;

    @Mock
    private DrinkRepository drinkRepository;

    @InjectMocks
    private DrinkPackageService drinkPackageService;

    private Drink drink1, drink2;
    private DrinkPackage drinkPackage;

    @BeforeEach
    public void setUp() {
        // Teszt italok és csomag inicializálása
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

        drinkPackage = new DrinkPackage();
        drinkPackage.setId(1L);
        drinkPackage.setDrinks(Arrays.asList(drink1, drink2));
    }

    @Test
    public void testSavePackage() {
        when(drinkPackageRepository.save(any(DrinkPackage.class))).thenReturn(drinkPackage);

        List<Drink> drinks = Arrays.asList(drink1, drink2);
        DrinkPackage savedPackage = drinkPackageService.savePackage(drinks);

        assertNotNull(savedPackage);
        assertEquals(2, savedPackage.getDrinks().size());
        verify(drinkPackageRepository, times(1)).save(any(DrinkPackage.class));
    }

    @Test
    public void testFindById() {
        when(drinkPackageRepository.findById(1L)).thenReturn(Optional.of(drinkPackage));

        Optional<DrinkPackage> foundPackage = drinkPackageService.findById(1L);

        assertTrue(foundPackage.isPresent());
        assertEquals(1L, foundPackage.get().getId());
    }

    @Test
    public void testGetDrinksByIds() {
        when(drinkRepository.findAllById(Arrays.asList(1L, 2L))).thenReturn(Arrays.asList(drink1, drink2));

        List<Drink> drinks = drinkPackageService.getDrinksByIds(Arrays.asList(1L, 2L));

        assertNotNull(drinks);
        assertEquals(2, drinks.size());
    }
}

