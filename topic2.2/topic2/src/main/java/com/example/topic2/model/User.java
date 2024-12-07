package com.example.topic2.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Setter
@Getter
@Table(name = "Users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @ManyToMany
    @JoinTable(
            name = "user_favourite_packages", // Kapcsolótábla neve
            joinColumns = @JoinColumn(name = "user_id"), // A `User` azonosítója
            inverseJoinColumns = @JoinColumn(name = "package_id") // A `DrinkPackage` azonosítója
    )
    private List<DrinkPackage> drinkPackages = new ArrayList<>();
}
