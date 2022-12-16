package com.techelevator.Item;

public class Candy extends Item {
    public Candy(String name, double price) {
        super(name, price);
    }

    @Override
    public String sound() {
//        sounds for all types here, with if statements?
        return "Munch Munch, Yum!";
    }
}
