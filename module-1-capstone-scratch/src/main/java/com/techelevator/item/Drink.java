package com.techelevator.item;

public class Drink extends Item {
    public Drink(String name, double price) {
        super(name, price);
    }

    @Override
    public String sound() {
//        sounds for all types here, with if statements?
        return "Glug Glug, Yum!";
    }
}
