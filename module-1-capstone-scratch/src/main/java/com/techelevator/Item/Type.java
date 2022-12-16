package com.techelevator.Item;

import java.math.BigDecimal;

public class Type extends Item {
    public Type(String name, BigDecimal price) {
        super(name, price);
    }

    @Override
    public String sound() {
//        sounds for all types here, with if statements?
        return null;
    }
}
