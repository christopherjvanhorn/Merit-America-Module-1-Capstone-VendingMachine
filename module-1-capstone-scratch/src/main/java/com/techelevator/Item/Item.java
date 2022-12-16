package com.techelevator.Item;

// Parent class for all items in vending machine, child classes will extend this class and override the sound method
public abstract class Item {
    private String name;
    private double price;
    private int stock = 5;


    public Item(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public abstract String sound();

}
