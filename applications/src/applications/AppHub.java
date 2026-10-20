package applications;

import java.util.*;

import java.util.Scanner;

import applications.games.numberguess.NumberGuessingGame;
import applications.utilities.calculator.Calculator;
import applications.utilities.notes.Notes;

public class AppHub {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		// available apps: Calculator, NumberGuessingGame, Notes
		// use launch method(full screen console for best experience)

		final String[] MAIN_MENU = { "Number Guessing Game", "Notes", "Calculator", "Exit" };
		printMenu(MAIN_MENU, input);

	}

	public static void printMenu(String[] menuArray, Scanner input) {
		boolean inMenu = true;

		while (inMenu) {

			for (int i = 0; i < menuArray.length; i++) {
				System.out.printf("%d. %s%n", i + 1, menuArray[i]);
			}

			// stores integer value
			int choice = Input.readInt(input, "\nChoose an option: ");

			if (choice < 1 || choice > menuArray.length) {
				System.out.println("Invalid option");
			} else {
				System.out.println();
				inMenu = handleChoice(choice, input);
			}
		}
	}

	public static boolean handleChoice(int choice, Scanner input) {
		switch (choice) {
		case 1:
			NumberGuessingGame ng = new NumberGuessingGame();
			ng.launch(input);
			return true;
		case 2:
			Notes notes = new Notes();
			notes.launch(input);
			return true;
		case 3:
			Calculator calc = new Calculator();
			calc.launch(input);
			return true;
//		
		case 4:
			System.out.println("Powered off.");
			return false;
		default:
			System.out.println("Invalid option. Please pick again");
			return true;
		}
	}

}