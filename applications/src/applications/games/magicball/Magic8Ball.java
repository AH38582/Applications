package applications.games.magicball;

import java.util.Random;
import java.util.Scanner;

import applications.Game;
import applications.HasMenu;
import applications.Input;

public class Magic8Ball extends Game implements HasMenu {

	private static final String[] MAIN_MENU = { "Ask question", "How to play", "Quit" };
	private String[] ANSWERS = { "Yes", "No", "Ask again later", "It is certain", "Reply hazy, try again",
			"Do not count on it", "It is decidedly so", "Better not tell you now", "My reply is no", "Without a doubt",
			"Cannot predict now", "My sources say no", "Yes definitely", "Concentrate and ask again", "Very doubtful" };
	private static final String[] SHAKE_OPTIONS = { "Shake 8-ball with confidence", "Shake 8-Ball with fear",
			"Toss 8-Ball in the air and catch it" };
	private Random rand = new Random();

	public Magic8Ball() {
		super("Magic 8-Ball", "Let the magic 8-ball help you with your decisions");
	}

	@Override
	public String[] getMenuOptions() {
		return MAIN_MENU;
	}

	@Override
	public boolean handleChoice(int choice, Scanner input) {
		switch (choice) {
		case 1:
			askQuestion(input);
			return true;
			
		case 2:
			rules();
			return true;

		case 3: // quit
			return false;

		default:
			System.out.println("Invalid option. Please pick a number");
		}
		return true;
	}

	@Override
	public void rules() {
		System.out.printf("""
				%nHow to play:
				%s
				1. Ask question(preferably a yes or no question), and the magic 8-Ball will share it's divination%n
				""", super.getDescription());

	}

	@Override
	protected void run(Scanner input) {
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

	private String[] getShakeOptions() {
		return SHAKE_OPTIONS;
	}

	private void askQuestion(Scanner input) {
		String question = Input.readString(input, "\nAsk a question: ");
		getDivination(question);
		System.out.printf("%nThe 8-Ball says: %S%n%n", getDivination(question));
	}
	
	private String getDivination(String question) {
		String choice = ANSWERS [rand.nextInt(ANSWERS.length)];
		return choice;
	}
}
