package com.techelevator;

import com.techelevator.view.Menu;

public class VendingMachineCLI {
	public static void main(String[] args) {
		Menu menu = new Menu(System.in, System.out);
		CLIMenus cliMenus = new CLIMenus(menu);
		cliMenus.run();
	}
}
