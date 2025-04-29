package com.example.topic1.model;

public class Person {

    private int osszeg;
    private int mertek;
    private int suly;
    private int nem;

    public Person() {}

    public Person(int osszeg, int mertek, int suly, int nem) {
        this.osszeg = osszeg;
        this.mertek = mertek;
        this.suly = suly;
        this.nem = nem;
    }

    public int getOsszeg() {
        return osszeg;
    }

    public void setOsszeg(int osszeg) {
        this.osszeg = osszeg;
    }

    public int getMertek() {
        return mertek;
    }

    public void setMertek(int mertek) {
        this.mertek = mertek;
    }

    public int getSuly() {
        return suly;
    }

    public void setSuly(int suly) {
        this.suly = suly;
    }

    public int getNem() {
        return nem;
    }

    public void setNem(int nem) {
        this.nem = nem;
    }
}