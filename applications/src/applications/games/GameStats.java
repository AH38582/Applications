package applications.games;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

import applications.Storable;

public class GameStats implements Storable<ArrayList<Integer>> {
	/// data member for file
	private File file;

	// constructor for where instance can be customized with file name
	public GameStats(String fileName) {
		setFile(new File(fileName));
	}

	// setter method
	public void setFile(File file) {
		this.file = file;
	}

	@Override
	public void save(ArrayList<Integer> stats) {
		try (PrintWriter output = new PrintWriter(file)) {
			for (int stat : stats) {
				output.println(stat);
			}
		} catch (FileNotFoundException e) {
			System.out.println("Unable to save file");
		}

	}

	@Override
	public ArrayList<Integer> load() {
		ArrayList<Integer> stats = new ArrayList<Integer>();
		try (Scanner input = new Scanner(file)) {
			while (input.hasNextInt()) {
				stats.add(input.nextInt());
			}

		} catch (FileNotFoundException e) {
			System.out.println("No saved stats found");
		}
		return stats;
	}

}
