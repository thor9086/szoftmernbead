package com.example.topic2.model;

public class Person {

    private Integer amount;
    private Integer measure;
    private Integer weight;
    private Integer gender;

    public Person() {}

    public Person(Integer amount, Integer measure, Integer weight, Integer gender) {
        this.amount = amount;
        this.measure = measure;
        this.weight = weight;
        this.gender = gender;
    }



    public Integer getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public Integer getMeasure() {
        return measure;
    }

    public void setMeasure(int measure) {
        this.measure = measure;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public Integer getGender() {
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