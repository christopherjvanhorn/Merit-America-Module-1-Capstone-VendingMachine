package com.techelevator.item;

public class Candy extends Item {
    public Candy(String name, double price) {
        super(name, price);
    }
    @Override
    public String sound() {
        return "Munch Munch, Yum!";
    }
}
