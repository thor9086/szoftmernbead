//package com.example.topic2.model;
//
//import jakarta.persistence.*;
//import lombok.AllArgsConstructor;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.Setter;
//
//@Entity
//@Table(name = "User_Package")
//@Getter
//@Setter
//@AllArgsConstructor
//@NoArgsConstructor
//public class UserPackageRelationship {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    @ManyToOne
//    @JoinColumn(name = "user_id", nullable = false)
//    private User user;
//
//    @ManyToOne
//    @JoinColumn(name = "package_id", nullable = false)
//    private DrinkPackage savedDrinkPackage;
//}
