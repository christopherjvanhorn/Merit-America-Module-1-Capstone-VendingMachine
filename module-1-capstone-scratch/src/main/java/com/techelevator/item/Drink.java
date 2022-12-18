package com.techelevator.item;

public class Drink extends Item {
    public Drink(String name, double price) {
        super(name, price);
    }
    @Override
    public String sound() {
        return "Glug Glug, Yum!";
    }
}
