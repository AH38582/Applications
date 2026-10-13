package applications.games;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class GameStats {
	/// data member for file
	private File file;


	// constructor for where instance can be customized
	public GameStats(String fileName) {
		setFile(new File(fileName));
	}
	
	public void setFile(File file) {
		this.file = file;
	}

	// stores stats in array and record them into file
	public void saveStats(int[] stats) {
		try (PrintWriter output = new PrintWriter(file)) {
			for (int stat : stats) {
				output.println(stat);
			}
		} catch (FileNotFoundException e) {
			System.out.println("Unable to save file");
		}
	}

	// takes file and prints array that is in file to console
	public int[] loadStats(int numOfStats) {
		int[] stats = new int[numOfStats];
		try (Scanner input = new Scanner(file)) {
			for (int i = 0; i < stats.length; i++) {
				if (input.hasNextInt()) {
					stats[i] = input.nextInt();
				}
			}
			
		} catch (FileNotFoundException e) {
			System.out.println("No saved stats found");
		}
		return stats;

	}
}
