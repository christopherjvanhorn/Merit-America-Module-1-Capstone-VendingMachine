package com.techelevator.transaction;

import com.techelevator.view.Menu;

import java.text.DecimalFormat;

public class PurchaseMenu {
    private final String FEED_MONEY = "Feed Money";
    private final String SELECT_PRODUCT = "Select Product";
    private final String FINISH_TRANSACTION = "Finish Transaction";
    private final String RETURN_TO_MAIN_MENU = "Return to the Main Menu";
    private final String[] PURCHASE_MENU_OPTIONS = {FEED_MONEY, SELECT_PRODUCT, FINISH_TRANSACTION, RETURN_TO_MAIN_MENU};

    private final String ONE_DOLLAR = "$1.00";
    private final String FIVE_DOLLARS = "$5.00";
    private final String TEN_DOLLARS = "$10.00";
    private final String DONE_FEEDING_MONEY = "Return to the Purchase Menu";
    private final String[] FEED_MONEY_OPTIONS = {ONE_DOLLAR, FIVE_DOLLARS, TEN_DOLLARS, DONE_FEEDING_MONEY};
    private boolean addMoreMoney;
    double balance = 0.00;
//    Menu menu = new Menu();
    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public void displayPurchaseMenu() {
        System.out.printf("%s %.2f \n \n", "Current Money Provided: $", getBalance());
 //       int purchaseMenuChoice = (int) menu.getChoiceFromOptions(PURCHASE_MENU_OPTIONS);


    }


}
