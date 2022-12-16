package com.techelevator.Item;

public class Gum extends Item {
    public Gum(String name, double price) {
        super(name, price);
    }

    @Override
    public String sound() {
//        sounds for all types here, with if statements?
        return  "Chew Chew, Yum!";
    }
}
