package com.techelevator;

import com.techelevator.item.Inventory;
import com.techelevator.transaction.Purchase;
import com.techelevator.view.Menu;

public class VendingMachineCLI {

	private static final String MAIN_MENU_OPTION_DISPLAY_ITEMS = "Display Vending Machine Items";
	private static final String MAIN_MENU_OPTION_PURCHASE = "Purchase";
	private static final String MAIN_MENU_OPTION_EXIT = "Exit";
	private static final String[] MAIN_MENU_OPTIONS = { MAIN_MENU_OPTION_DISPLAY_ITEMS, MAIN_MENU_OPTION_PURCHASE, MAIN_MENU_OPTION_EXIT };


	private Menu menu;
	public static Inventory allItems = new Inventory();
	private Purchase purchase = new Purchase();

	public VendingMachineCLI(Menu menu) {
		this.menu = menu;
	}

	public void run() {
		allItems.itemMenu();
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

	public static void main(String[] args) {
		Menu menu = new Menu(System.in, System.out);
		VendingMachineCLI cli = new VendingMachineCLI(menu);
		cli.run();
	}
}
