package com.example.topic2.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "Drink")
@Getter
@Setter
public class Drink {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private double price;
    private double alcoholContent;
    private double volume;
    private boolean isShortDrink;


    @ManyToMany(mappedBy = "drinks")
//    @JoinTable(
//            name = "package_drink", // Kapcsolótábla neve
//            joinColumns = @JoinColumn(name = "drink_id"), // Ital azonosítója
//            inverseJoinColumns = @JoinColumn(name = "package_id") // Csomag azonosítója
//    )
    private List<DrinkPackage> drinkPackages = new ArrayList<>();

    public Drink() {}

    public Drink(String name, double price, double alcoholContent, double volume, boolean isShortDrink) {
        this.name = name;
        this.alcoholContent = alcoholContent;
        this.price = price;
        this.volume = volume;
        this.isShortDrink = isShortDrink;
    }

    public Drink(Long id, String name,  double price, double alcoholContent, double volume, boolean isShortDrink) {
        this.id = id;
        this.name = name;
        this.alcoholContent = alcoholContent;
        this.price = price;
        this.volume = volume;
        this.isShortDrink = isShortDrink;
    }
}
