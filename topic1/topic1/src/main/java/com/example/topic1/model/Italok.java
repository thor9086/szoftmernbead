package com.example.topic1.model;

public class Italok {

    //private int id;
    private String name;
    private int price;
    private int alcohol;
    private double length;
    private boolean lengthBool;


    public Italok(String name, int price, int alcohol, double length, boolean lengthBool) {
        this.name = name;
        this.price = price;
        this.alcohol = alcohol;
        this.length = length;
        this.lengthBool = lengthBool;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getAlcohol() {
        return alcohol;
    }

    public void setAlcohol(int alcohol) {
        this.alcohol = alcohol;
    }

    public double getLength() {
        return length;
    }

    public boolean isLengthBool() {
        return lengthBool;
    }
}
