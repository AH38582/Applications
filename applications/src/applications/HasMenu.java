package applications;
import java.util.Scanner;

public interface HasMenu {

	// returns menu options
	String[] getMenuOptions();
	
	// prompts menu choice
	boolean handleChoice(int choice, Scanner input);
}
