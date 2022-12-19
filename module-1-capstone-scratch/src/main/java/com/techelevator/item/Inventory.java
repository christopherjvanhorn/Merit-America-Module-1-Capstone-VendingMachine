package com.techelevator.item;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.regex.Pattern;

// Reads the contents of a CSV file and builds the inventory of the vending machine
public class Inventory {
    private String name = "";
    private double price = 0.00;
    private String slotID = "";
    private String type = "";
    private String[] itemArray;
    Map<String, Item> itemInventory = new TreeMap<>(); // Maintain order <slotID, Item Object>

    //if you get "file does not exist" error, change the below string value to the Absolute Path on your device.
    private String filePath = "C:\\Users\\escxb\\Desktop\\java-projects\\capstone-1-scratchpaper\\module-1-capstone-scratch\\vendingmachine.csv";

    public Map<String, Item> buildItemMenu() {
        File inventoryFile = new File(filePath);
        try (Scanner scanner = new Scanner(inventoryFile)) {
            while (scanner.hasNextLine()) {
                itemArray = scanner.nextLine().split(Pattern.quote("|"));
                slotID = itemArray[0];
                name = itemArray[1];
                price = Double.parseDouble(itemArray[2]);
                type = itemArray[3];

                if (type.equals("Chip")) {
                    itemInventory.put(slotID, new Chip(name, price));
                }
                if (type.equals("Candy")) {
                    itemInventory.put(slotID, new Candy(name, price));
                }
                if (type.equals("Drink")) {
                    itemInventory.put(slotID, new Drink(name, price));
                }
                if (type.equals("Gum")) {
                    itemInventory.put(slotID, new Gum(name, price));
                }
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e.getMessage());
        }
        return itemInventory;
    }

    public void displayCurrentInventory() {
        int itemStock;
        System.out.println();

        for (Map.Entry<String, Item> item : itemInventory.entrySet()) {
            slotID = item.getKey();
            name = item.getValue().getName();
            price = item.getValue().getPrice();
            itemStock = item.getValue().getStock();
            if (itemStock > 0) {
                System.out.printf("%s %s %-19s $%.2f %s %d\n", slotID, "-", name, price, "- Stock Remaining:", itemStock);
            } else {
                System.out.printf("%s %s %-19s $%.2f %s\n", slotID, "-", name, price, "- SOLD OUT");
            }
        }
    }
}
