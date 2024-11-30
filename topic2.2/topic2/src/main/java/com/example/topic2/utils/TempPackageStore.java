//package com.example.topic2.utils;
//
//import com.example.topic2.model.DrinkPackage;
//import org.springframework.stereotype.Component;
//
//import java.util.HashMap;
//import java.util.Map;
//
//@Component
//public class TempPackageStore {
//
//    private Map<String, DrinkPackage> tempPackages = new HashMap<>();
//
//    public void addPackage(DrinkPackage drinkPackage) {
//        tempPackages.put(drinkPackage.getTempId(), drinkPackage);
//    }
//
//    public DrinkPackage findPackageById(String tempId) {
//        return tempPackages.get(tempId);
//    }
//}
//
