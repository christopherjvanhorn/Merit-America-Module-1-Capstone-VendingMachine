package com.techelevator;

import com.techelevator.item.Item;
import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;

public class OperationsTest extends TestCase {
    private ByteArrayOutputStream output;
    @Before

    public void setup() {
        output = new ByteArrayOutputStream();
    }

    Operations operationsTestObject = new Operations();
    @Test
    public void testAddToBalance() {
        double money = 0;

        money = operationsTestObject.addToBalance(12);
        Assert.assertEquals(12.00, money);

        money = operationsTestObject.addToBalance(56);
        Assert.assertEquals(68.00, money);
    }


    @Test
    public void testIsValidChoice() {

        String validInput = "b3";
        Assert.assertEquals(true, operationsTestObject.isValidChoice(validInput));

        String invalidInput = "v3";
        Assert.assertEquals(false, operationsTestObject.isValidChoice(invalidInput));

        String nullInput = null;
        Assert.assertEquals(false, operationsTestObject.isValidChoice(nullInput));

        String emptyInput = "";
        Assert.assertEquals(false, operationsTestObject.isValidChoice(emptyInput));



    }

    @Test
    public void testItemDispenserOutput() {
        String testKey = "B1";
        Item testItem = operationsTestObject.getItemMenuMap().get(testKey);  //MoonPie at B1 cost 1.80 is Candy
        double testBalance = 20.00;
        operationsTestObject.setBalance(testBalance);
        int expectedStock = 4;

        operationsTestObject.itemDispenser(testKey, testItem);
        //testing output allows us to test item name, item price, remaining balance, item sound
        String expected = String.format("\n%s %s\n%s $%.2f\n%s $%.2f\n%s\n", "Enjoy your", "Moonpie", "Item Cost:", 1.80, "Remaining Balance:", 18.20, "Munch Munch, Yum!");
        String actual = operationsTestObject.itemDispenserOutput(testItem);
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testItemDispenserReduceStock(){
        String testKey = "B1";
        Item testItem = operationsTestObject.getItemMenuMap().get(testKey);  //MoonPie at B1 cost 1.80 is Candy
        operationsTestObject.itemDispenser(testKey, testItem);
        //test reduce stock
        int actualStock = operationsTestObject.getItemMenuMap().get(testKey).getStock();
        Assert.assertEquals(4,actualStock);


    }

    @Test
    public void testChangeNumberOfQuarters() {
        operationsTestObject.setBalance(5.00);
        int expected = 20;
        operationsTestObject.change();
        int actual = operationsTestObject.getNumberOfQuarters();
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testChangeNumberOfDimes() {
        operationsTestObject.setBalance(6.85);
        int expected = 1;
        operationsTestObject.change();
        int actual = operationsTestObject.getNumberOfDimes();
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testChangeNumberOfNickels() {

        operationsTestObject.setBalance(.15);
        int expected = 1;
        operationsTestObject.change();
        int actual = operationsTestObject.getNumberOfNickels();
        Assert.assertEquals(expected, actual);
    }

    public void testLog() {
        /*String actual = "";
        String expected = "this is a test";
        operationsTestObject.log(expected);
        File testLog = new File("Log.txt");
        try (Scanner scanner = new Scanner(testLog)) {
            while (scanner.hasNextLine()) {
                actual = scanner.nextLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
        Assert.assertEquals(expected, actual);*/


    }
}