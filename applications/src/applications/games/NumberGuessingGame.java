package applications.games;

import java.util.Scanner;

import applications.App;

public class NumberGuessingGame extends App {

	public NumberGuessingGame() {
		super("Guessing Game", "Guess the computer's number");
	}

	@Override
	public void launch(Scanner input) {
		input = new Scanner(System.in);
		int guess, max, min, secretNum;

		secretNum = startGame(input);
		guessAndPrint(input, secretNum);

	}

	public static int startGame(Scanner input) {
		int min, max;
		System.out.println("Guessing Game");

		System.out.print("Enter min number:");
		min = input.nextInt();

		System.out.print("Enter max number:");
		max = input.nextInt();

		while (max <= min) {
			System.out.println("Maximum must be greater than minimum");
			System.out.print("Enter min number:");
			min = input.nextInt();

			System.out.print("Enter max number:");
			max = input.nextInt();
		}

		return (int) (Math.random() * (max - min + 1)) + min;
	}

	public static void guessAndPrint(Scanner input, int secretNum) {
		System.out.println("\nGame Start");
		int attempts = 0;
		int guess;

		do {
			System.out.print("Enter a guess: ");
			guess = input.nextInt();
			attempts++;

			if (guess > secretNum) {
				System.out.printf("%d is too high! Try again.%n", guess);
			} else if (guess < secretNum) {
				System.out.printf("%d is too low! Try again.%n", guess);
			} else {
				System.out.printf("Congratulations! %d is the secret number.%n", guess);
				System.out.printf("You guessed %d time(s)!", attempts);
			}

		} while (guess != secretNum);
	}

}
