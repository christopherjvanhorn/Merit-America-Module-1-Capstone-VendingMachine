package com.techelevator;

import com.techelevator.item.Inventory;
import com.techelevator.transaction.PurchaseMenu;
import com.techelevator.view.Menu;

public class VendingMachineCLI {

	private static final String MAIN_MENU_OPTION_DISPLAY_ITEMS = "Display Vending Machine Items";
	private static final String MAIN_MENU_OPTION_PURCHASE = "Purchase";
	private static final String MAIN_MENU_OPTION_EXIT = "Exit";
	private static final String[] MAIN_MENU_OPTIONS = { MAIN_MENU_OPTION_DISPLAY_ITEMS, MAIN_MENU_OPTION_PURCHASE, MAIN_MENU_OPTION_EXIT };


	private Menu menu;
	private Inventory showAllItems = new Inventory();
	private PurchaseMenu purchaseMenu = new PurchaseMenu();

	public VendingMachineCLI(Menu menu) {
		this.menu = menu;
	}

	public void run() {
		while (true) {
			String choice = (String) menu.getChoiceFromOptions(MAIN_MENU_OPTIONS);

			if (choice.equals(MAIN_MENU_OPTION_DISPLAY_ITEMS)) {
				// build item menu
				showAllItems.itemMenu();
				// display vending machine items
				showAllItems.displayCurrentInventory();
			} else if (choice.equals(MAIN_MENU_OPTION_PURCHASE)) {
				// show purchase menu
				purchaseMenu.displayPurchaseMenu();
				// user select option; options below
				// take money(feed)
				// select item
				// do purchase
			}
		}
	}

	public static void main(String[] args) {
		Menu menu = new Menu(System.in, System.out);
		VendingMachineCLI cli = new VendingMachineCLI(menu);
		cli.run();
	}
}
