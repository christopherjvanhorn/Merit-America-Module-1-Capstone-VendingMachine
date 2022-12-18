package com.techelevator.item;

public class Gum extends Item {
    public Gum(String name, double price) {
        super(name, price);
    }
    @Override
    public String sound() {
        return  "Chew Chew, Yum!";
    }
}
