package com.techelevator.transaction;

import com.techelevator.VendingMachineCLI;
import com.techelevator.item.Inventory;
import com.techelevator.item.Item;
import com.techelevator.view.Menu;

import java.util.Map;




public class Purchase {

    Inventory inventoryObject = new Inventory();
    private Map<String, Item> allItems = inventoryObject.itemMenu();
    private Balance balance = new Balance();

    private double remainingBalance;



    public void dispenseSelectedItem (String selectedItemID) {
        //created variables for readability
        boolean isValidChoice = allItems.itemMenu().containsKey(selectedItemID);

        // check if choice is valid, item is in stock, & the customer has enough money inserted for item
        if (isValidChoice) {
            int inStock = allItems.itemMenu().get(selectedItemID).getStock();
            double currentBalance = balance.getBalance();
            double itemPrice = allItems.itemMenu().get(selectedItemID).getPrice();
            String itemName = allItems.itemMenu().get(selectedItemID).getName();
            String itemSound = allItems.itemMenu().get(selectedItemID).sound();
            remainingBalance = currentBalance - itemPrice;
            if (inStock > 0) {
                if (itemPrice <= currentBalance) {
                    // dispense item, charge customer's balance, adjust inventory, & thank them for their purchase
                    System.out.printf("\n%s %s\n%s $%.2f\n%s $%.2f\n%s\n", "Enjoy your", itemName, "Item Cost:", itemPrice, "Remaining Balance:", remainingBalance, itemSound);
                    balance.setBalance(remainingBalance);
                    allItems.itemMenu().get(selectedItemID).reduceStock();
                    System.out.println("Thank you for your purchase. Enjoy!");
                } else {
                    System.out.println("Please insert more money to purchase this item.");
                }
            } else {
                //give out of stock message
                System.out.println("Unfortunately the item you have selected is sold out.\n" +
                        "If you feel this message is an error, please contact Maintenance at ext. 212");
            }
        } else {
            System.out.println("*** " + selectedItemID + " is not a valid option ***");
        }

    }
}
