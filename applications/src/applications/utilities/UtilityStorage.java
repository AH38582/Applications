package applications.utilities;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

import applications.Storable;

public class UtilityStorage implements Storable<ArrayList<String>> {
	// date member for File object
	private File file;

	public UtilityStorage(String fileName) {
		setFile(new File(fileName));
	}

	public void setFile(File file) {
		this.file = file;
	}

	@Override
	public void save(ArrayList<String> data) {
		try (PrintWriter output = new PrintWriter(file)) {
			for (String datum : data) {
				output.println(datum);
			}

		} catch (FileNotFoundException e) {
			System.out.println("Unable to save file");
		}

	}

	@Override
	public ArrayList<String> load() {
		ArrayList<String> data = new ArrayList<String>();
		try (Scanner input = new Scanner(file)) {
			while (input.hasNextLine()) {
				String line = input.nextLine().trim();
				if (!line.isEmpty()) {
					data.add(line);
				}
			}

		} catch (FileNotFoundException e) {
			System.out.println("No saved stats found");
		}

		return data;
	}

	

}
