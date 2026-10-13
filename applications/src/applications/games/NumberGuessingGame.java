package applications.games;

import java.util.Scanner;

import applications.Game;
import applications.HasMenu;
import applications.Input;

public class NumberGuessingGame extends Game implements HasMenu {
	// data members for menus
	private static final String[] MAIN_MENU = { "New game", "Stats", "How to Play", "Quit" };
	private static final String[] DIFFICULTY_MENU = { "Easy(1-10)", "Medium(1-50)", "Hard(1-1000)", "Custom", "Quit" };
	// data members for game
	private int min, max, secretNum;
	private int totalAttemptsOAT = 0;
	private int totalNumGames = 0;
	// data member for GameStats
	private GameStats gameStats;
	

	// default convenience constructor
	public NumberGuessingGame() {
		super("Number Guessing Game", "Guess the computer's number"); // to share description and name
		gameStats = new GameStats("src/applications/games/number_guessing_game_stat"); // decides what file will be tracking stats
		int[] stats = gameStats.loadStats(2); // calls loadStats and the number of stats to be loaded
		// assigns each stat an index
		totalAttemptsOAT = stats[0];
		totalNumGames = stats[1];
	}
	
	// Override methods
	@Override
	public void startGame() { // from Game class
		Scanner input = new Scanner(System.in);
		secretNum = secretNum(min, max);
		guessAndPrint(input, secretNum);

	}

	@Override
	public String[] getMenuOptions() { // from HasMenu interface
		return MAIN_MENU;
	}

	@Override
	public boolean handleChoice(int choice, Scanner input) { // from HasMenu interface
		switch (choice) {
		case 1: // New game
			boolean inMenu = true;

			// nested switch case that sets up the difficulty that user chooses
			while (inMenu) {
				String[] options = getDifficultyMenuOptions();
				// prints the DIFFICULTY_MENU  array
				for (int i = 0; i < options.length; i++) {
					System.out.printf("%d. %s%n", i + 1, options[i]);
				}

				// stores integer value
				choice = Input.readInt(input, "\nChoose a difficulty: ");

				switch (choice) { 
				case 1: // Easy
					min = 1;
					max = 10;
					startGame();
					inMenu = false;
					break;

				case 2: // Medium
					min = 1;
					max = 50;
					startGame();
					inMenu = false;
					break;

				case 3: // Hard
					min = 1;
					max = 1000;
					startGame();
					inMenu = false;
					break;

				case 4: // Custom
					min = Input.readInt(input, "Enter a min: ");
					max = Input.readInt(input, "Enter a max: ");

					while (min >= max) {
						System.out.println("Maximum must be greater than minimum.");
						min = Input.readInt(input, "Enter a min: ");
						max = Input.readInt(input, "Enter a max: \n");

					}
					startGame();
					inMenu = false;
					break;

				case 5: // Quit difficulty menu
					inMenu = false;
					break;

				default:
					System.out.println("Invalid option. Please pick a number");
				}

			}
			return true;
		case 2: // stats
			System.out.printf("%nTotal attempts overall: %d%n", totalAttemptsOAT);
			System.out.printf("Total number of games: %d%n%n", totalNumGames);
			return true;

		case 3: // how to play
			rules();
			return true;
		case 4: // quit
			System.out.println("Quit app");
			return false;

		default:
			System.out.println("Invalid option. Please pick a number");
		}
		return true;
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

			if (choice < 1 || choice > options.length) {
				System.out.println("Invalid option");
			} else {

				inMenu = handleChoice(choice, input);
			}
		}

	}

	private int secretNum(int min, int max) {
		// creates secret number within the user's chosen range
		return (int) (Math.random() * (max - min + 1)) + min;
	}

	private void guessAndPrint(Scanner input, int secretNum) {
		System.out.println("\nGame Start");
		int attempts = 0;
		int guess;

		// processes game and prints the results of the game
		do {
			guess = Input.readInt(input, "Enter a guess: ");
			attempts++;
			

			if (guess > secretNum) {
				System.out.printf("%n%d is too high! Try again.%n", guess);
			} else if (guess < secretNum) {
				System.out.printf("%n%d is too low! Try again.%n", guess);
			} else {
				System.out.printf("%nCongratulations! %d is the secret number.%n", guess);
				System.out.printf("You guessed %d time(s)!%n%n", attempts);
			}

		} while (guess != secretNum);
		
		totalAttemptsOAT += attempts;
		totalNumGames++;
		gameStats.saveStats(new int[] { totalAttemptsOAT, totalNumGames });
		
	}

	@Override
	public void rules() {
		System.out.printf("""
				%nHow to play:
				%s
				1. Pick a difficulty or create custom range
				2. # of games played, highest attempt and lowest attempt is recorded and saved
				3. Stats option shows stats(coming soon)%n%n
				""", super.getDescription());

	}

	public String[] getDifficultyMenuOptions() {
		return DIFFICULTY_MENU;
	}

}
