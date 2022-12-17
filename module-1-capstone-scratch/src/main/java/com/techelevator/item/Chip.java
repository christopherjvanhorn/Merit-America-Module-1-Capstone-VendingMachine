package com.techelevator.item;

public class Chip extends Item {
    public Chip(String name, double price) {
        super(name, price);
    }

    @Override
    public String sound() {
//        sounds for all types here, with if statements?
        return "Crunch Crunch, Yum!";
    }
}
