package com.techelevator;

import com.techelevator.transaction.Purchase;
import com.techelevator.view.Menu;

public class CLIMenus {
    private static final String MAIN_MENU_OPTION_DISPLAY_ITEMS = "Display Vending Machine Items";
    private static final String MAIN_MENU_OPTION_PURCHASE = "Purchase";
    private static final String MAIN_MENU_OPTION_EXIT = "Exit";
    private static final String[] MAIN_MENU_OPTIONS = { MAIN_MENU_OPTION_DISPLAY_ITEMS, MAIN_MENU_OPTION_PURCHASE, MAIN_MENU_OPTION_EXIT };
    private final String FEED_MONEY = "Feed Money";
    private final String SELECT_PRODUCT = "Select Product";
    private final String FINISH_TRANSACTION = "Finish Transaction";
    private final String[] PURCHASE_MENU_OPTIONS = {FEED_MONEY, SELECT_PRODUCT, FINISH_TRANSACTION};
    private final String ONE_DOLLAR = "$1.00";
    private final String FIVE_DOLLARS = "$5.00";
    private final String TEN_DOLLARS = "$10.00";
    private final String DONE_FEEDING_MONEY = "Return to the Purchase Menu";
    private final String[] FEED_MONEY_OPTIONS = {ONE_DOLLAR, FIVE_DOLLARS, TEN_DOLLARS, DONE_FEEDING_MONEY};

    //create objects to handle stuff
    private Menu menu;
    Purchase purchase = new Purchase();



    public CLIMenus(Menu menu) {
        this.menu = menu;
    }

    public void run() {
        while (true) {
            String choice = (String) menu.getChoiceFromOptions(MAIN_MENU_OPTIONS);

            if (choice.equals(MAIN_MENU_OPTION_DISPLAY_ITEMS)) { // build item menu & display vending machine items
                allItems.displayCurrentInventory();
            } else if (choice.equals(MAIN_MENU_OPTION_PURCHASE)) { // show purchase menu, where all purchase related tasks are handled
                purchase.displayPurchaseMenu(menu);
            } else if (choice.equals(MAIN_MENU_OPTION_EXIT)){ // say goodbye & break

            }
        }
    }

    public void displayPurchaseMenu(Menu menu) {
        while (true) {
            System.out.printf("\n%s $%.2f \n", "Current Money Provided: ", balance.getBalance());
            String purchaseMenuChoice = (String) menu.getChoiceFromOptions(PURCHASE_MENU_OPTIONS);
            if (purchaseMenuChoice.equals(FEED_MONEY)) {  // take money(feed)
                balance.displayBalanceMenu(menu);
            } else if (purchaseMenuChoice.equals(SELECT_PRODUCT)) {  // check if customer deposited money first
                if (balance.getBalance() > 0) {  //show list, ask select item, do purchase item, & do dispense item
                    allItems.displayCurrentInventory();
                    dispenseSelectedItem(menu.itemSelectionFromUser());
                } else {
                    System.out.println("\nYour current balance is zero. Please insert money before selecting this option.");
                }
            } else if (purchaseMenuChoice.equals(FINISH_TRANSACTION)) {  // give change & break

                break;
            }
        }
    }

    public void displayBalanceMenu (Menu menu) {
        while (true) {
            System.out.printf("\n%s $%.2f \n", "Current Money Provided: ", balance);
            String balanceChoice = (String) menu.getChoiceFromOptions(FEED_MONEY_OPTIONS);
            if (balanceChoice.equals(ONE_DOLLAR)) {
                balance += 1;
            } else if (balanceChoice.equals(FIVE_DOLLARS)) {
                balance += 5;
            } else if (balanceChoice.equals(TEN_DOLLARS)) {
                balance += 10;
            } else if (balanceChoice.equals(DONE_FEEDING_MONEY)) {
                break;
            }
        }
    }
}
