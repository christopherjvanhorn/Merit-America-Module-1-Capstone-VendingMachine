package com.techelevator;

import com.techelevator.item.Inventory;
import com.techelevator.item.Item;
import com.techelevator.view.Menu;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.TreeMap;

public class Operations {

    Menu menu = new Menu(System.in, System.out);
    Inventory currentInventory = new Inventory();
    private Map<String, Item> itemMenuMap = new TreeMap<>(currentInventory.buildItemMenu());
    private double balance = 0.00;
    // private double remainingBalance;
    private String logMessage;

    private int numberOfQuarters;
    private int numberOfDimes;
    private int numberOfNickels;

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
    public int getNumberOfQuarters() {
        return numberOfQuarters;
    }

    public void setNumberOfQuarters(int numberOfQuarters) {
        this.numberOfQuarters = numberOfQuarters;
    }

    public int getNumberOfDimes() {
        return numberOfDimes;
    }

    public void setNumberOfDimes(int numberOfDimes) {
        this.numberOfDimes = numberOfDimes;
    }

    public int getNumberOfNickels() {
        return numberOfNickels;
    }

    public void setNumberOfNickels(int numberOfNickels) {
        this.numberOfNickels = numberOfNickels;
    }

    public double addToBalance(double deposit) {

        double newBalance = getBalance() + deposit;
        setBalance(newBalance);
        logMessage = String.format("%s $%.2f $%.2f", "FEED MONEY:", deposit, newBalance);
        log(logMessage);

        return getBalance();
    }

    public void selectItemChoice() {
        getCurrentInventory().displayCurrentInventory();
        String userChoice = menu.itemSelectionFromUser();
        if (isValidChoice(userChoice)) {
            if (validItemDetails(userChoice).getStock() > 0) {
                itemDispenser(userChoice, validItemDetails(userChoice));
            } else {
                System.out.println("Unfortunately the item you have selected is sold out.\n" +
                        "If you feel this message is an error, please contact Maintenance at ext. 212");
            }
        } else {
            System.out.println("*** " + userChoice + " is not a valid option ***");
        }
    }

    public boolean isValidChoice(String userChoice) {
        if (itemMenuMap.containsKey(userChoice)) {
            validItemDetails(userChoice);
            return true;
        }
        return false;
    }

    public Item validItemDetails(String userChoice) {
        return this.itemMenuMap.get(userChoice);
    }

    // possiblely not touching itemMenuMap
    public int itemDispenser(String itemKey, Item userChoice) {
        String itemName = userChoice.getName();
        String itemSound = userChoice.sound();
        double itemPrice = userChoice.getPrice();

        if (getBalance() >= itemPrice) {
            double remainingBalance = getBalance() - itemPrice;
            String output = String.format("\n%s %s\n%s $%.2f\n%s $%.2f\n%s\n", "Enjoy your", itemName, "Item Cost:", itemPrice, "Remaining Balance:", remainingBalance, itemSound);
            System.out.print(output);
            setBalance(remainingBalance);
            this.itemMenuMap.get(itemKey).reduceStock();
            logMessage = String.format("%s %s $%.2f $%.2f", itemName, itemKey, itemPrice, getBalance());
            log(logMessage);
            System.out.println("Thank you for your purchase. Enjoy!");

        } else {
            System.out.println("Please insert more money to purchase this item.");
        }

        return this.itemMenuMap.get(itemKey).getStock();
    }

    //todo below method created to test above method output, ensure to update below if you update above
    public String itemDispenserOutput(Item userChoice) {
        String itemName = userChoice.getName();
        String itemSound = userChoice.sound();
        double itemPrice = userChoice.getPrice();
        String output = "";
        // if (getBalance() >= itemPrice) {
        double remainingBalance = getBalance();
        output = String.format("\n%s %s\n%s $%.2f\n%s $%.2f\n%s\n", "Enjoy your", itemName, "Item Cost:", itemPrice, "Remaining Balance:", remainingBalance, itemSound);
        return output;
//            setBalance(remainingBalance);
//            this.itemMenuMap.get(itemKey).reduceStock();
//            logMessage = String.format("%s %s $%.2f $%.2f", itemName , itemKey, itemPrice, getBalance());
//            log(logMessage);
//            System.out.println("Thank you for your purchase. Enjoy!");

        //  } else {
        //     System.out.println("Please insert more money to purchase this item.");
    //}
    //return output;
    }

    public void change() {
        int change = (int) getBalance() * 100; //had to cast to int to prevent infinite loop caused due to number great than zero but less than 1
        double initialMoney = getBalance();
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
        setBalance(0);
        setNumberOfQuarters(quarter);
        setNumberOfDimes(dime);
        setNumberOfNickels(nickel);
        logMessage = String.format("%s $%.2f $%.2f", "GIVE CHANGE:", initialMoney, getBalance());
        log(logMessage);
        System.out.println("Change given:");
        System.out.printf("Number of Quarters: %3d  %n", quarter);
        System.out.printf("Number of Dimes: %6d  %n", dime);
        System.out.printf("Number of Nickels: %4d  %n", nickel);
    }


    public void log(String message) {
        //Displays date and time of transaction in 12 hour format with AM & PM
        SimpleDateFormat dateTimeFormat = new SimpleDateFormat("MM/dd/yyyy 'at' hh:mm:ss aa");

        String dateTimeString = dateTimeFormat.format(new Date());
        try (PrintWriter logger = new PrintWriter(new FileOutputStream("Log.txt", true))) {
            logger.write(dateTimeString + " " + message + "\n");

        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

}