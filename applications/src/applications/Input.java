package applications;

import java.util.Scanner;

public final class Input {

	// created to read scanner inputs and store them in values

	public static int readInt(Scanner input, String prompt) {
		while (true) {
			System.out.print(prompt);
			if (input.hasNextInt()) {
				int value = input.nextInt();
				input.nextLine();
				return value;
			}
			System.out.println("Invalid input. Please enter a whole number.");
			input.nextLine();
		}
	}

	public static double readDouble(Scanner input, String prompt) {
		while (true) {
			System.out.print(prompt);
			if (input.hasNextDouble()) {
				double value = input.nextDouble();
				input.nextLine();
				return value;
			}
			System.out.println("Invalid input. Please enter a floating type.");
			input.nextLine();
		}
	}

	public static String readString(Scanner input, String prompt) {
		while (true) {
			System.out.print(prompt);
			String value = input.nextLine().trim();

			if (!value.isEmpty()) {
				return value;
			}

			System.out.println("Invalid input. Please enter a non empty string.");
		}
	}
}