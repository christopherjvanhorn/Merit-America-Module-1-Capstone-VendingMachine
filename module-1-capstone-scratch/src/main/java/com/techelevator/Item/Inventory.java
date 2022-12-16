package com.techelevator.Item;

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


            }

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}
