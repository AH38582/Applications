package applications;

import java.util.Scanner;

public interface HasMenu {

	// returns menu options
	String[] getMenuOptions();

	
	/**
	 * Handles the user's menu choice.
	 * @return true to keep the menu open, false to exit it
	 */
	boolean handleChoice(int choice, Scanner input);
}
