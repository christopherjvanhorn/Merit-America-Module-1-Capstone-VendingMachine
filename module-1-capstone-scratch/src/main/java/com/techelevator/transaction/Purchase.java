package com.techelevator.transaction;

import com.techelevator.VendingMachineCLI;
import com.techelevator.view.Menu;

import static com.techelevator.VendingMachineCLI.allItems;


public class Purchase {
    private final String FEED_MONEY = "Feed Money";
    private final String SELECT_PRODUCT = "Select Product";
    private final String FINISH_TRANSACTION = "Finish Transaction";
    private final String[] PURCHASE_MENU_OPTIONS = {FEED_MONEY, SELECT_PRODUCT, FINISH_TRANSACTION};

    private Balance balance;


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
                    System.out.println("Your current balance is zero. Please insert money before selecting this option.");
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


        if (isValidChoice) {
            if (inStock > 0) {
                // check if the customer has enough money inserted for item
                if (itemPrice <= currentBalance) {
                    // dispense item, charge customer's balance, & thank them for their purchase
                    ;

                    System.out.println("Thank you for your purchase. Enjoy!");
                } else {
                    System.out.println("Please insert more money to purchase this item.");
                }
            } else if (inStock == 0) {
                //give out of stock message
                System.out.println("Unfortunately the item you have selected is sold out.\n" +
                        "If you feel this message is an error, please contact Maintenance at ext. 212");
                // if customer enters invalid selection
            }
        } else {
            System.out.println("*** " + selectedItemID + " is not a valid option ***");
        }

    }
}
