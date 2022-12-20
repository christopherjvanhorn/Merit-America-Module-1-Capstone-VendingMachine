package com.techelevator.item;

import junit.framework.TestCase;
import org.junit.Assert;

public class ItemTest extends TestCase {

    public void testReduceStock() {

        Item testCandy = new Candy("Gummy", .50);

        testCandy.reduceStock();
        Assert.assertEquals(4, testCandy.getStock());
        testCandy.reduceStock();
        Assert.assertEquals(3, testCandy.getStock());
        testCandy.reduceStock();
        Assert.assertEquals(2, testCandy.getStock());
        testCandy.reduceStock();
        Assert.assertEquals(1, testCandy.getStock());
        testCandy.reduceStock();
        Assert.assertEquals(0, testCandy.getStock());
        testCandy.reduceStock();
        Assert.assertEquals(0, testCandy.getStock());


    }

    public void testSound() {

        Item testCandy = new Candy("Chocolate", 1.00);
        Item testChips = new Chip("Lays", 2.00);
        Item testDrink = new Drink("Pepsi", 3.00);
        Item testGum = new Gum("Mint", 1.00);


        Assert.assertEquals("Munch Munch, Yum!", testCandy.sound());
        Assert.assertEquals("Glug Glug, Yum!", testDrink.sound());
        Assert.assertEquals("Crunch Crunch, Yum!", testChips.sound());
        Assert.assertEquals("Chew Chew, Yum!", testGum.sound());


    }
}