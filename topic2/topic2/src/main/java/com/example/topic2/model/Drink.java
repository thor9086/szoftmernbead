package com.example.topic2.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Drink {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private double alcoholContent;
    private double price;
    private double volume;
    private boolean isShortDrink;

    public Drink() {}

    public Drink(String name, double alcoholContent, double price, double volume, boolean isShortDrink) {
        this.name = name;
        this.alcoholContent = alcoholContent;
        this.price = price;
        this.volume = volume;
        this.isShortDrink = isShortDrink;
    }

    public Drink(Long id, String name, double alcoholContent, double price, double volume, boolean isShortDrink) {
        this.id = id;
        this.name = name;
        this.alcoholContent = alcoholContent;
        this.price = price;
        this.volume = volume;
        this.isShortDrink = isShortDrink;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getAlcoholContent() {
        return alcoholContent;
    }

    public double getVolume() {
        return volume;
    }

    public double getPrice() {
        return price;
    }

    public boolean isShortDrink() {
        return isShortDrink;
    }


}
