package com.techelevator;

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
    Operations operations = new Operations();

    public CLIMenus(Menu menu) {
        this.menu = menu;
    }

    public void run() {
        while (true) {
            String choice = (String) menu.getChoiceFromOptions(MAIN_MENU_OPTIONS);

            if (choice.equals(MAIN_MENU_OPTION_DISPLAY_ITEMS)) { // build item menu & display vending machine items
                operations.getCurrentInventory().displayCurrentInventory();
            } else if (choice.equals(MAIN_MENU_OPTION_PURCHASE)) { // show purchase menu, where all purchase related tasks are handled
                displayPurchaseMenu(menu);
            } else if (choice.equals(MAIN_MENU_OPTION_EXIT)){ // say goodbye & break
                System.out.println("Thank you for visiting our Vending Machine application, we hope to see you soon!");
                break;
            }
        }
    }

    public void displayPurchaseMenu(Menu menu) {
        while (true) {
            System.out.printf("\n%s $%.2f \n", "Current Money Provided: ", operations.getBalance());

            String purchaseMenuChoice = (String) menu.getChoiceFromOptions(PURCHASE_MENU_OPTIONS);

            if (purchaseMenuChoice.equals(FEED_MONEY)) {                // take money(feed)
                displayDepositMenu(menu);
            } else if (purchaseMenuChoice.equals(SELECT_PRODUCT)) {     // check if customer deposited money first
                if (operations.getBalance() > 0) {                      //show list, ask select item, do purchase item, & do dispense item
                    operations.selectItemChoice();
                } else {
                    System.out.println("\nYour current balance is zero. Please insert money before selecting this option.");
                }
            } else if (purchaseMenuChoice.equals(FINISH_TRANSACTION)) {  // give change & break
                // give change, when method created XD
                break;
            }
        }
    }

    public void displayDepositMenu (Menu menu) {
        while (true) {
            System.out.printf("\n%s $%.2f \n", "Current Money Provided: ", operations.getBalance());

            String depositChoice = (String) menu.getChoiceFromOptions(FEED_MONEY_OPTIONS);

            if (depositChoice.equals(ONE_DOLLAR)) {
                operations.addToBalance(1);
            } else if (depositChoice.equals(FIVE_DOLLARS)) {
                operations.addToBalance(5);
            } else if (depositChoice.equals(TEN_DOLLARS)) {
                operations.addToBalance(10);
            } else if (depositChoice.equals(DONE_FEEDING_MONEY)) {
                break;
            }
        }
    }





}
