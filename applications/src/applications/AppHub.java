package applications;
import java.util.*;

import java.util.Scanner;

import applications.games.NumberGuessingGame;
import applications.utilities.Calculator;
import applications.utilities.Notes;

public class AppHub {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		Calculator calculator = new Calculator();
		
		NumberGuessingGame n = new NumberGuessingGame();
		Notes note = new Notes();		
		calculator.launch(input);

	}

}