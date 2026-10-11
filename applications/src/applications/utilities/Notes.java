package applications.utilities;

import java.util.Scanner;
import java.util.ArrayList;

import applications.App;
import applications.HasMenu;
import applications.Input;

public class Notes extends App implements HasMenu {

	private ArrayList<String> notes = new ArrayList<String>();
	private static final String[] MENU = { "New note", "List note", "View note", "Delete note" };

	public Notes() {
		super("Notes", "Create, access, and delete notes");
	}



	@Override
	public String[] getMenuOptions() {
		return MENU;
	}

	@Override
	public boolean handleChoice(int choice, Scanner input) {
		switch (choice) {
		case 1:
			addNote(input);
			return true;
		case 2:
			listNotes();
			return true;
		case 3:
			viewNote(input);
			return true;
		case 4:
			deleteNote(input);
			return true;
		default:
			return false;
		}
	}

	@Override
	protected void run(Scanner input) {
		boolean inMenu = true;

		while (inMenu) {
			String[] options = getMenuOptions();

			// prints menu
			for (int i = 0; i < options.length; i++) {
				System.out.printf("%d. %s%n%n", i + 1, options[i]);

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

	private void addNote(Scanner input) {
		String text = Input.readString(input, "Enter note text: ");

		notes.add(text);
		System.out.println("Note added!\n");
	}

	private void listNotes() {
		if (notes.isEmpty()) {
			System.out.println("No notes added here...\n");
			return;
		}

		for (int i = 0; i < notes.size(); i++) {
			System.out.printf("%d. %s%n", i + 1, notes.get(i));
		}
	}

	private void viewNote(Scanner input) {
		if (notes.isEmpty()) {
			System.out.println("No notes to view here...\n");
			return;
		}

		listNotes();
		int index = Input.readInt(input, "Enter note number: ") - 1;
		if (index < 0 || index >= notes.size()) {
			System.out.println("Invalid note number");
			return;
		}

		System.out.printf("%n %s", notes.get(index));
	}

	private void deleteNote(Scanner input) {
		if (notes.isEmpty()) {
			System.out.println("No notes to delete here...\n");
			return;
		}

		listNotes();
		int index = Input.readInt(input, "Enter note number to delete: ") - 1;
		if (index < 0 || index >= notes.size()) {
			System.out.println("Invalid note number");
			return;
		}
		notes.remove(index);
		System.out.println("Note deleted!");
	}

}
