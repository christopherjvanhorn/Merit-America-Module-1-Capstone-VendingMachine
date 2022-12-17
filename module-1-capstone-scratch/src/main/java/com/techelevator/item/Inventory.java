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
    private Map<String, Item> itemInventory = new TreeMap<>(); // Maintain order <slotID, Item Object>
    private String filePath = "vendingmachine.csv";

    public Map<String, Item> itemMenu() {
        File inventoryFile = new File(filePath);
        try (Scanner scanner = new Scanner(inventoryFile)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                itemArray = line.split(Pattern.quote("|"));
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
        name = "";
        slotID = "";
        price = 0.00;
        int itemStock;

        for (Map.Entry<String, Item> item : itemInventory.entrySet()) {
            slotID = item.getKey();
            name = item.getValue().getName();
            price = item.getValue().getPrice();
            itemStock = item.getValue().getStock();

            //System.out.printf("%s %s %-19s %s $%,.2f  %s %d\n", slotID, " -- ", name, "-- ", price, "-- Stock Remaining:", itemStock);
            System.out.printf("%s %s %-19s $%,.2f  %s %d\n", slotID, "-", name, price, "Stock Remaining:", itemStock);
        }
    }

}
