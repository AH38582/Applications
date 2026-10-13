package applications;

import java.util.*;

import java.util.Scanner;

import applications.games.NumberGuessingGame;
import applications.utilities.Calculator;
import applications.utilities.Notes;

public class AppHub {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		// available apps: Calculator, NumberGuessingGame, Notes
		// use launch method(full screen console for best experience)

		NumberGuessingGame n = new NumberGuessingGame();
		n.launch(input);
		

	}

}