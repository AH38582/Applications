package applications.utilities.calculator;

import java.util.ArrayList;
import java.util.Scanner;

import applications.HasMenu;
import applications.Input;
import applications.Storable;
import applications.Utility;
import applications.utilities.UtilityStorage;

public class Calculator extends Utility implements HasMenu {
	private static final String[] MAIN_MENU = { "Calculate", "Math", "History", "Clear history", "Quit" };
	private static final String[] MATH_MENU = { "Is N prime?", "gcd(a, b)", "lcm(a,b)", "Exit" };

	private static final int MAX_HISTORY = 30;
	private Storable<ArrayList<String>> storage;
	private ArrayList<String> history = new ArrayList<String>();

	public Calculator() {
		super("Calculator", "Performs basic arithmetic");
		storage = new UtilityStorage("src/applications/utilities/calculator/calc");
		history = storage.load();
	}

	@Override
	public void run(Scanner input) {
		boolean inMenu = true;

		while (inMenu) {
			String[] options = getMenuOptions();

			for (int i = 0; i < options.length; i++) {
				System.out.printf("%d. %s%n", i + 1, options[i]);
			}

			// stores integer value
			int choice = Input.readInt(input, "\nChoose an option: ");

			inMenu = handleChoice(choice, input);
		}
	}

	@Override
	public String[] getMenuOptions() {
		return MAIN_MENU;
	}

	@Override
	public boolean handleChoice(int choice, Scanner input) {
		switch (choice) {
		case 1:
			calculate(input);
			return true;
		case 2:
			boolean inMenu = true;

			while (inMenu) {
				String[] options = getMathMenuOptions();
				System.out.println();
				for (int i = 0; i < options.length; i++) {
					System.out.printf("%d. %s%n", i + 1, options[i]);
				}

				// stores integer value
				int mathMenuChoice = Input.readInt(input, "\nChoose an option: ");

				inMenu = handleMathMenu(mathMenuChoice, input);
			}
			System.out.println();
			return true;
		case 3:
			printAllData();
			return true;
		case 4:
			System.out.println();
			if (history.isEmpty()) {
				System.out.println("History is already empty");
				return true;
			}

			System.out.print("Are you sure you want to clear all history? (y/n):");
			String response = input.nextLine().trim().toLowerCase();

			if (response.equals("y") || response.equals("yes")) {
				clearAllData();
			} else {
				System.out.println("Clear history cancelled\n");
			}
			return true;
		case 5:
			return false;
		default:
			System.out.println("Invalid option. Please pick again\n");
			return true;
		}
	}

	@Override
	protected void clearAllData() {
		history.clear();
		storage.save(history);
	}

	@Override
	protected void printAllData() {

		if (history.isEmpty()) {
			System.out.println("No history yet.\n");
			return;
		}
		System.out.println("\n====== History ======");
		System.out.println("History capacity is 30; exceeding that removes the oldest entry");
		for (int i = 0; i < history.size(); i++) {
			System.out.printf("%n%d. %s%n", i + 1, history.get(i));
		}
		System.out.println();
	}

	public String[] getMathMenuOptions() {
		return MATH_MENU;
	}

	private boolean handleMathMenu(int choice, Scanner input) {
		switch (choice) {
		case 1:
			printIsPrime(input);
			return true;
		case 2:
			printGCD(input);
			return true;
		case 3:
			printLCM(input);
			return true;
		case 4:
			return false;
		default:
			System.out.println("Invalid option. Please pick again\n");
			return true;
		}
	}

	private void calculate(Scanner input) {
		System.out.println();
		// boolean that will be used for loop
		boolean isOn = true;

		// loop for error retries
		while (isOn) {

			// create variables
			double n1, n2;
			char op;

			n1 = Input.readDouble(input, "Enter your first value: "); // stores first value

			String opString = Input.readString(input, "Enter your operator (+, -, *, /): ");
			op = opString.charAt(0); // stores operator value

			n2 = Input.readDouble(input, "Enter your second value: "); // stores first value
			System.out.println();

			// switch statement that maps each selected operator
			switch (op) {
			case '+':
				addAndPrint(n1, n2); // call add();
				isOn = false;
				break;

			case '-':
				subtractAndPrint(n1, n2); // call subtract();
				isOn = false;
				break;

			case '*':
				multiplyAndPrint(n1, n2); // call multiply();
				isOn = false;
				break;

			case '/':
				// if statement that accounts for ArithmeticException
				if (n2 == 0) {
					System.out.println("\nCalculation Incomplete: Cannot divide by zero! Please enter numbers.\n");

				} else {
					divideAndPrint(n1, n2); // call divide();
					isOn = false;

				}
				break;
			default:
				// prints error message
				System.out.println("\nCalculation Incomplete: Invalid operator found! Please try again.\n");
			}

			System.out.println();
		}
	}

	// method that adds the two values and prints results
	private void addAndPrint(double n1, double n2) {
		double result = n1 + n2;
		System.out.printf("%.2f + %.2f = %.2f%n", n1, n2, result);
		saveHistory(String.format("%.2f + %.2f = %.2f", n1, n2, result));
	}

	// method that subtracts the two values and prints results
	private void subtractAndPrint(double n1, double n2) {
		double result = n1 - n2;
		System.out.printf("%.2f - %.2f = %.2f%n", n1, n2, result);
		saveHistory(String.format("%.2f - %.2f = %.2f", n1, n2, result));
	}

	// method that multiplies the two values and prints results
	private void multiplyAndPrint(double n1, double n2) {
		double result = n1 * n2;
		System.out.printf("%.2f * %.2f = %.2f%n", n1, n2, result);
		saveHistory(String.format("%.2f * %.2f = %.2f", n1, n2, result));
	}

	// method that divides the two values and prints results
	private void divideAndPrint(double n1, double n2) {
		double result = n1 / n2;
		System.out.printf("%.2f / %.2f = %.2f%n", n1, n2, result);
		saveHistory(String.format("%.2f / %.2f = %.2f", n1, n2, result));
	}

	private void saveHistory(String entry) {
		if (history.contains(entry)) {
			return;
		}

		history.add(entry);
		if (history.size() > MAX_HISTORY) {
			history.remove(0);
		}
		storage.save(history);
	}

	private void printIsPrime(Scanner input) {
		int choice = Input.readInt(input, "\nEnter a number: ");
		boolean isPrime = isPrime(choice);

		if (isPrime) {
			System.out.printf("%n%d is a prime!%n", choice);
			saveHistory(String.format("%d is a prime!", choice));
		} else {
			System.out.printf("%n%d is not a prime!%n", choice);
			saveHistory(String.format("%d is not a prime!", choice));
		}
	}

	private void printGCD(Scanner input) {
		int num1 = Input.readInt(input, "\nEnter first number: ");
		int num2 = Input.readInt(input, "Enter second number: ");

		System.out.printf("%ngcd(%d, %d) is %d%n", num1, num2, gcd(num1, num2));
		saveHistory(String.format("gcd(%d, %d) is %d", num1, num2, gcd(num1, num2)));
	}

	private void printLCM(Scanner input) {
		int num1 = Input.readInt(input, "\nEnter first number: ");
		int num2 = Input.readInt(input, "Enter second number: ");

		System.out.printf("%nlcm(%d, %d) is %d%n", num1, num2, lcm(num1, num2));
		saveHistory(String.format("lcm(%d, %d) is %d", num1, num2, lcm(num1, num2)));
	}

	private boolean isPrime(int n) {
		if (n < 2) {
			return false;
		}
		if (n == 2 || n == 3) {
			return true;
		}
		if (n % 2 == 0 || n % 3 == 0) {
			return false;
		}

		for (int i = 5; i <= n / i; i += 6) {
			if (n % i == 0 || n % (i + 2) == 0) {
				return false;
			}
		}
		return true;
	}

	private int gcd(int a, int b) {
		while (b != 0) {
			int temp = b;
			b = a % b;
			a = temp;
		}
		return Math.abs(a);
	}

	private int lcm(int a, int b) {
		if (a == 0 || b == 0) {
			return 0;
		}
		return Math.abs(a / gcd(a, b) * b);
	}

}
