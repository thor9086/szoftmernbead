package com.example.topic2.model;

public class Person {

    private int amount;
    private int measure;
    private int weight;
    private int gender;

    public Person() {}

    public Person(int osszeg, int mertek, int weight, int gender) {
        this.amount = osszeg;
        this.measure = mertek;
        this.weight = weight;
        this.gender = gender;
    }



    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getMeasure() {
        return measure;
    }

    public void setMeasure(int measure) {
        this.measure = measure;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "Person{" +
                "amount=" + amount +
                ", measure=" + measure +
                ", weight=" + weight +
                ", gender=" + gender +
                '}';
    }
}