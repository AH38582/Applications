package applications.games;

import java.util.ArrayList;
import java.util.Scanner;

import applications.Game;
import applications.HasMenu;
import applications.Input;
import applications.Storable;

public class NumberGuessingGame extends Game implements HasMenu {
	// constants for menus
	private static final String[] MAIN_MENU = { "New game", "Stats", "How to Play", "Quit" };
	private static final String[] DIFFICULTY_MENU = { "Easy(1-10)", "Medium(1-50)", "Hard(1-1000)", "Custom", "Quit" };

	// constants for min, max, and load() method settings
	private static final int EASY_MIN = 1, EASY_MAX = 10;
	private static final int MEDIUM_MIN = 1, MEDIUM_MAX = 50;
	private static final int HARD_MIN = 1, HARD_MAX = 1000;
	private static final int TOTAL_ATTEMPTS_INDEX = 0;
	private static final int TOTAL_GAMES_INDEX = 1;
	private static final int EASY_ATTEMPTS_INDEX = 2;
	private static final int EASY_GAMES_INDEX = 3;
	private static final int MEDIUM_ATTEMPTS_INDEX = 4;
	private static final int MEDIUM_GAMES_INDEX = 5;
	private static final int HARD_ATTEMPTS_INDEX = 6;
	private static final int HARD_GAMES_INDEX = 7;
	private static final int CUSTOM_ATTEMPTS_INDEX = 8;
	private static final int CUSTOM_GAMES_INDEX = 9;
	private static final int BEST_GAME_INDEX = 10;
	private static final int BEST_E_GAME_INDEX = 11;
	private static final int BEST_M_GAME_INDEX = 12;
	private static final int BEST_H_GAME_INDEX = 13;
	private static final int BEST_C_GAME_INDEX = 14;

	// data members for game
	private int min, max;
	private String currentDifficulty;
	private int totalAttempts = 0, totalNumGames = 0, bestGame = 0, easyBestGame = 0, mediumBestGame = 0,
			hardBestGame = 0, customBestGame = 0;
	private int easyGames = 0, easyAttempts = 0, mediumGames = 0, mediumAttempts = 0, hardGames = 0, hardAttempts = 0,
			customAttempts = 0, customGames = 0;

	// data member for stats
	private Storable<ArrayList<Integer>> storage;

	// default convenience constructor
	public NumberGuessingGame() {
		super("Number Guessing Game", "Guess the computer's number"); // to share description and name
		storage = new GameStats("src/applications/games/number_guessing_game_stat"); // invokes GameStats for file
																						// String
		loadStats(); // loads stats from save file
	}

	public void startGame(Scanner input) { // overloaded to allow Scanner parameter
		int secretNum = generateSecretNumber(min, max);
		playRound(input, secretNum);
	}

	// Override methods
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
				// prints the DIFFICULTY_MENU array
				for (int i = 0; i < options.length; i++) {
					System.out.printf("%d. %s%n", i + 1, options[i]);
				}

				// stores integer value
				int diffChoice = Input.readInt(input, "\nChoose a difficulty: ");
				inMenu = selectDifficulty(diffChoice, input);
			}
			return true;
		case 2: // stats
			double averageAttempts = calculateAverage(totalAttempts, totalNumGames);

			System.out.printf("""
					%n======Overall Stats======
					Games played: %d
					Total guesses: %d
					Average guesses per game: %.2f
					Best game: %s%n
					""", totalNumGames, totalAttempts, averageAttempts,
					bestGame == 0 ? "No games played yet" : bestGame + " attempts");
			printDiffStats("Easy", easyGames, easyAttempts, easyBestGame);
			printDiffStats("Medium", mediumGames, mediumAttempts, mediumBestGame);
			printDiffStats("Hard", hardGames, hardAttempts, hardBestGame);
			printDiffStats("Custom", customGames, customAttempts, customBestGame);
			return true;

		case 3: // how to play
			rules();
			return true;
		case 4: // quit
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

	private int generateSecretNumber(int min, int max) {
		// creates secret number within the user's chosen range
		return (int) (Math.random() * (max - min + 1)) + min;
	}

	private void playRound(Scanner input, int secretNum) {
		System.out.println("\nGame Start");
		int attempts = 0;
		int guess;

		// processes game and prints the results of the game
		do {
			guess = Input.readInt(input, "Enter a guess between " + min + " and " + max + ": ");
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

		totalAttempts += attempts;
		totalNumGames++;
		updateDifficulty(attempts);
		updateBestGame(attempts);
		saveStats();
	}

	@Override
	public void rules() {
		System.out.printf("""
				%nHow to play:
				%s
				1. Pick a difficulty or create custom range and start guessing the computer's randomly picked number
				2. Overall total guesses and # of games played are recorded in Stats%n
				""", super.getDescription());

	}

	public String[] getDifficultyMenuOptions() {
		return DIFFICULTY_MENU;
	}
	
	private void selectCustomRange(Scanner input) {
		min = Input.readInt(input, "Enter a min: ");
		max = Input.readInt(input, "Enter a max: ");

		while (min >= max) {
			System.out.println("Maximum must be greater than minimum.");
			min = Input.readInt(input, "Enter a min: ");
			max = Input.readInt(input, "Enter a max: \n");

		}
	}

	private boolean selectDifficulty(int choice, Scanner input) {
		switch (choice) {
		case 1: // Easy
			min = EASY_MIN;
			max = EASY_MAX;
			currentDifficulty = "easy";
			break;

		case 2: // Medium
			min = MEDIUM_MIN;
			max = MEDIUM_MAX;
			currentDifficulty = "medium";
			break;

		case 3: // Hard
			min = HARD_MIN;
			max = HARD_MAX;
			currentDifficulty = "hard";
			break;

		case 4: // Custom
			selectCustomRange(input);
			currentDifficulty = "custom";
			break;
		case 5: // quits difficulty menu
			return false;
		default:
			System.out.println("Invalid option. Please pick a number");
			return true;
		}

		startGame(input);
		return false;

	}

	private void printDiffStats(String difficulty, int games, int attempts, int best) {
		double avg = calculateAverage(attempts, games);

		System.out.printf("""
				%n======%s Stats======
				Games played: %d
				Total guesses: %d
				Average guesses per game: %.2f
				Best game: %s%n
				""", difficulty, games, attempts, avg, best == 0 ? "No games played yet" : best + " attempts");
	}

	private double calculateAverage(int attempts, int games) {
		if (games == 0) {
			return 0;
		}

		return (double) attempts / games;
	}

	private void updateDifficulty(int attempts) {
		switch (currentDifficulty) {
		case "easy":
			easyGames++;
			easyAttempts += attempts;
			break;
		case "medium":
			mediumGames++;
			mediumAttempts += attempts;
			break;
		case "hard":
			hardGames++;
			hardAttempts += attempts;
			break;
		case "custom":
			customGames++;
			customAttempts += attempts;
			break;
		}
	}

	private void updateBestGame(int attempts) {
		if (bestGame == 0 || attempts < bestGame) {
			bestGame = attempts;
		}

		switch (currentDifficulty) {
		case "easy":
			if (easyBestGame == 0 || attempts < easyBestGame) {
				easyBestGame = attempts;
			}
			break;
		case "medium":
			if (mediumBestGame == 0 || attempts < mediumBestGame) {
				mediumBestGame = attempts;
			}
			break;
		case "hard":
			if (hardBestGame == 0 || attempts < hardBestGame) {
				hardBestGame = attempts;
			}
			break;
		case "custom":
			if (customBestGame == 0 || attempts < customBestGame) {
				customBestGame = attempts;
			}
			break;
		}

	}

	private void saveStats() {
		ArrayList<Integer> updatedStats = new ArrayList<Integer>();
		updatedStats.add(totalAttempts);
		updatedStats.add(totalNumGames);
		updatedStats.add(easyAttempts);
		updatedStats.add(easyGames);
		updatedStats.add(mediumAttempts);
		updatedStats.add(mediumGames);
		updatedStats.add(hardAttempts);
		updatedStats.add(hardGames);
		updatedStats.add(customAttempts);
		updatedStats.add(customGames);
		updatedStats.add(bestGame);
		updatedStats.add(easyBestGame);
		updatedStats.add(mediumBestGame);
		updatedStats.add(hardBestGame);
		updatedStats.add(customBestGame);
		
		storage.save(updatedStats);
	}

	private void loadStats() {
		ArrayList<Integer> stats = storage.load();

		if (stats.size() >= 15) {
			totalAttempts = stats.get(TOTAL_ATTEMPTS_INDEX);
			totalNumGames = stats.get(TOTAL_GAMES_INDEX);
			easyAttempts = stats.get(EASY_ATTEMPTS_INDEX);
			easyGames = stats.get(EASY_GAMES_INDEX);
			mediumAttempts = stats.get(MEDIUM_ATTEMPTS_INDEX);
			mediumGames = stats.get(MEDIUM_GAMES_INDEX);
			hardAttempts = stats.get(HARD_ATTEMPTS_INDEX);
			hardGames = stats.get(HARD_GAMES_INDEX);
			customAttempts = stats.get(CUSTOM_ATTEMPTS_INDEX);
			customGames = stats.get(CUSTOM_GAMES_INDEX);
			bestGame = stats.get(BEST_GAME_INDEX);
			easyBestGame = stats.get(BEST_E_GAME_INDEX);
			mediumBestGame = stats.get(BEST_M_GAME_INDEX);
			hardBestGame = stats.get(BEST_H_GAME_INDEX);
			customBestGame = stats.get(BEST_C_GAME_INDEX);
		}
	}

	@Override
	public void startGame() {
		// TODO Auto-generated method stub
		
	}

}
