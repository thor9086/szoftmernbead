//package com.example.topic2.model;
//
//import jakarta.persistence.*;
//import lombok.AllArgsConstructor;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.Setter;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.UUID;
//
//@Entity
//@Table(name = "Package")
//@Getter
//@Setter
//@AllArgsConstructor
//public class DrinkPackage {
//
////    @Id
////    @GeneratedValue(strategy = GenerationType.IDENTITY)
////    private Long id;
//
//
//
//    private String tempId; // Ideiglenes azonosító
//    private String name;
//
//    public DrinkPackage() {
//        this.tempId = UUID.randomUUID().toString(); // Egyedi azonosító generálása
//    }
//
//
//    @ManyToMany
//    @JoinTable(
//            name = "package_drink",
//            joinColumns = @JoinColumn(name = "package_id"),
//            inverseJoinColumns = @JoinColumn(name = "drink_id")
//    )
//    private List<Drink> drinks = new ArrayList<>();
//
//
//
//    public DrinkPackage(String name, List<Drink> drinks) {
//        this.name = name;
//        this.drinks = drinks;
//    }
//}
//
