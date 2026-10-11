package applications.utilities;

import java.util.InputMismatchException;
import java.util.Scanner;

import applications.App;

public class Calculator extends App {

	public Calculator() {
		super("Calculator", "Performs basic arithmetic");
	}

	@Override
	public void run(Scanner input) {
		// boolean that will be used for loop
		boolean isOn = true;

		// loop for error retries
		while (isOn) {

			try {
				// create variables
				double n1, n2;
				char op;

				System.out.println("Calculator");
				System.out.print("Enter your first value: ");
				n1 = input.nextDouble(); // stores first value

				System.out.print("Enter your operator (+, -, *, /): ");
				op = input.next().charAt(0); // stores operator value

				System.out.print("Enter your second value: ");
				n2 = input.nextDouble(); // stores second value

				// switch statement that maps each selected operator
				switch (op) {
				case '+':
					addAndPrint(n1, n2); // call add();
					isOn = false; // isOn disabled, loop stops
					break;

				case '-':
					subtractAndPrint(n1, n2); // call subtract();
					isOn = false; // isOn disabled, loop stops
					break;

				case '*':
					multiplyAndPrint(n1, n2); // call multiply();
					isOn = false; // isOn disabled, loop stops
					break;

				case '/':
					// if statement that accounts for ArithmeticException
					if (n2 == 0) {
						throw new ArithmeticException(
								"\nCalculation Incomplete: Cannot divide by zero! Please enter numbers.\n");

					} else {
						divideAndPrint(n1, n2); // call divide();
						isOn = false; // isOn disabled, loop stops
						break;

					}

				default:
					// prints error message
					System.out.println("\nCalculation Incomplete: Invalid operator found! Please try again.\n");
					break;
				}

			} catch (ArithmeticException e) {
				System.out.println(e.getMessage()); // prints error message
				input.nextLine();

			} catch (InputMismatchException e) {
				System.out.println("\nInvalid input! Please enter numbers.\n"); // prints error message
				input.nextLine();
			}
		}

	}

	// method that adds the two values and prints results
	private void addAndPrint(double n1, double n2) {
		double result = n1 + n2;
		System.out.printf("%.2f + %.2f = %.2f", n1, n2, result);
	}

	// method that subtracts the two values and prints results
	private void subtractAndPrint(double n1, double n2) {
		double result = n1 - n2;
		System.out.printf("%.2f - %.2f = %.2f", n1, n2, result);
	}

	// method that multiplies the two values and prints results
	private void multiplyAndPrint(double n1, double n2) {
		double result = n1 * n2;
		System.out.printf("%.2f * %.2f = %.2f", n1, n2, result);
	}

	// method that divides the two values and prints results
	private void divideAndPrint(double n1, double n2) {
		double result = n1 / n2;
		System.out.printf("%.2f / %.2f = %.2f", n1, n2, result);
	}
}