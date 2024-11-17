//package com.example.topic2.model;
//
//import jakarta.persistence.*;
//import lombok.AllArgsConstructor;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.Setter;
//
//@Entity
//@Table(name = "Package")
//@Getter
//@Setter
//@AllArgsConstructor
//@NoArgsConstructor
//public class FavoritePackage {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    @ManyToOne
//    @JoinColumn(name = "user_id")
//    private User user; // Felhasználó, akihez a csomag tartozik
//
//    @ManyToOne
//    @JoinColumn(name = "package_id")
//    private Package pkg; // Kedvenc csomag
//
//}
