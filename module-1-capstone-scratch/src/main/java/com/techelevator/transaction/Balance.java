package com.techelevator.transaction;

import com.techelevator.view.Menu;

public class Balance {
    private final String ONE_DOLLAR = "$1.00";
    private final String FIVE_DOLLARS = "$5.00";
    private final String TEN_DOLLARS = "$10.00";
    private final String DONE_FEEDING_MONEY = "Return to the Purchase Menu";
    private final String[] FEED_MONEY_OPTIONS = {ONE_DOLLAR, FIVE_DOLLARS, TEN_DOLLARS, DONE_FEEDING_MONEY};
    private double balance = 0.00;

    public double getBalance() {
        return balance;
    }
    public void setBalance(double balance) {
        this.balance = balance;
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
