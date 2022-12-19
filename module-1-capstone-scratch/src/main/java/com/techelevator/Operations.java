package com.techelevator;

import com.techelevator.item.Inventory;
import com.techelevator.item.Item;
import com.techelevator.view.Menu;

import java.util.Map;
import java.util.TreeMap;

public class Operations {

    Menu menu = new Menu(System.in, System.out);
    Inventory currentInventory = new Inventory();
    private Map<String, Item> itemMenuMap = new TreeMap<>(currentInventory.buildItemMenu());
    private double balance = 0.00;
    // private double remainingBalance;

    public Operations() {
    }

    public Inventory getCurrentInventory() {
        return currentInventory;
    }

    public Map<String, Item> getItemMenuMap() {
        return itemMenuMap;
    }

    public double getBalance() {
        return balance;
    }
    public void setBalance(double balance) {
        this.balance = balance;
    }

    public void addToBalance (double deposit) {
        double newBalance = getBalance() + deposit;
        setBalance(newBalance);
    }

    public void selectItemChoice () {
        getCurrentInventory().displayCurrentInventory();
        String userChoice = menu.itemSelectionFromUser();
        if (isValidChoice(userChoice)){
            if (validItemDetails(userChoice).getStock() > 0) {
                itemDispenser(validItemDetails(userChoice), userChoice);
            } else {
                System.out.println("Unfortunately the item you have selected is sold out.\n" +
                        "If you feel this message is an error, please contact Maintenance at ext. 212");
            }
        } else {
            System.out.println("*** " + userChoice + " is not a valid option ***");
        }
    }

    public boolean isValidChoice (String userChoice){
        if (itemMenuMap.containsKey(userChoice)){
            validItemDetails(userChoice);
            return true;
        }
        return false;
    }

    public Item validItemDetails(String userChoice){
        return this.itemMenuMap.get(userChoice);
    }

    // possiblely not touching itemMenuMap
    public void itemDispenser (Item userChoice, String itemKey){
        String itemName = userChoice.getName();
        String itemSound = userChoice.sound();
        double itemPrice = userChoice.getPrice();

        if (getBalance() >= itemPrice) {
            double remainingBalance = getBalance() - itemPrice;
            System.out.printf("\n%s %s\n%s $%.2f\n%s $%.2f\n%s\n", "Enjoy your", itemName, "Item Cost:", itemPrice, "Remaining Balance:", remainingBalance, itemSound);
            setBalance(remainingBalance);
            this.itemMenuMap.get(itemKey).reduceStock();
            System.out.println("Thank you for your purchase. Enjoy!");
        } else {
            System.out.println("Please insert more money to purchase this item.");
        }

    }

    public void change() {
        double change = getBalance() * 100;
        int quarter = 0;
        int nickel = 0;
        int dime = 0;

        while (change > 0) { //Runs a loop which determines how many of each coin is used by subtracting the values of the largest first and continuing until 0.
            if (change >= 25) {
                change -= 25;
                quarter++;
            } else if (change >= 10) {
                change -= 10;
                dime++;
            } else if (change >= 5) {
                change -= 5;
                nickel++;
            }
        }
        System.out.println("Change given:");
        System.out.printf("Number of Quarters: %3d  %n", quarter);
        System.out.printf("Number of Dimes: %6d  %n", dime);
        System.out.printf("Number of Nickels: %4d  %n", nickel);
    }
}
