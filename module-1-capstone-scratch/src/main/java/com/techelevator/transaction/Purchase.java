package com.techelevator.transaction;

import com.techelevator.VendingMachineCLI;
import com.techelevator.view.Menu;

import static com.techelevator.VendingMachineCLI.allItems;


public class Purchase {
    private final String FEED_MONEY = "Feed Money";
    private final String SELECT_PRODUCT = "Select Product";
    private final String FINISH_TRANSACTION = "Finish Transaction";
    private final String[] PURCHASE_MENU_OPTIONS = {FEED_MONEY, SELECT_PRODUCT, FINISH_TRANSACTION};

    private Balance balance = new Balance();

    private double remainingBalance;

    public void displayPurchaseMenu(Menu menu) {
        while (true) {
            System.out.printf("\n%s $%.2f \n", "Current Money Provided: ", balance.getBalance());
            String purchaseMenuChoice = (String) menu.getChoiceFromOptions(PURCHASE_MENU_OPTIONS);
            if (purchaseMenuChoice.equals(FEED_MONEY)) {
                // take money(feed)
                balance.displayBalanceMenu(menu);
            } else if (purchaseMenuChoice.equals(SELECT_PRODUCT)) {
                // check if customer deposited money first
                if (balance.getBalance() > 0) {
                    //show list, ask select item, do purchase item, & do dispense item
                    allItems.displayCurrentInventory();
                    dispenseSelectedItem(menu.itemSelectionFromUser());
                } else {
                    System.out.println("\nYour current balance is zero. Please insert money before selecting this option.");
                }
            } else if (purchaseMenuChoice.equals(FINISH_TRANSACTION)) {
                // give change
                // break;
            }
        }
    }

    public void dispenseSelectedItem (String selectedItemID) {
        //created variables for readability
        boolean isValidChoice = allItems.itemMenu().containsKey(selectedItemID);
        int inStock = allItems.itemMenu().get(selectedItemID).getStock();
        double currentBalance = balance.getBalance();
        double itemPrice = allItems.itemMenu().get(selectedItemID).getPrice();
        String itemName = allItems.itemMenu().get(selectedItemID).getName();
        String itemSound = allItems.itemMenu().get(selectedItemID).sound();
        remainingBalance = currentBalance - itemPrice;

        // check if choice is valid, item is in stock, & the customer has enough money inserted for item
        if (isValidChoice) {
            if (inStock > 0) {
                if (itemPrice <= currentBalance) {
                    // dispense item, charge customer's balance, adjust inventory, & thank them for their purchase
                    System.out.printf("\n%s %s\n%s $%.2f\n%s $%.2f\n%s\n", "Enjoy your", itemName, "Item Cost:", itemPrice, "Remaining Balance:", remainingBalance, itemSound);
                    balance.setBalance(remainingBalance);
                    allItems.itemMenu().get(selectedItemID).setStock(inStock-1);
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
