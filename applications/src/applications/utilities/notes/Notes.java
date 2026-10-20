package applications.utilities.notes;

import java.util.Scanner;
import java.util.ArrayList;

import applications.HasMenu;
import applications.Input;
import applications.Storable;
import applications.Utility;
import applications.utilities.UtilityStorage;

public class Notes extends Utility implements HasMenu {

	private ArrayList<String> notes = new ArrayList<String>();
	private static final String[] MENU = { "New note", "List all notes", "View note", "Delete note", "Clear all notes",
			"Quit" };
	private Storable<ArrayList<String>> storage;

	public Notes() {
		super("Notes", "Create, access, and delete notes");
		storage = new UtilityStorage("src/applications/utilities/notes/notes_storage");
		notes = storage.load();
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
		case 5:
			clearAllNotes(input);
			return true;
		case 6:
			return false;
		default:
			System.out.println("Invalid option. Please pick again\n");
			return true;
		}
	}

	@Override
	protected void run(Scanner input) {
		boolean inMenu = true;

		while (inMenu) {
			String[] options = getMenuOptions();

			// prints menu
			for (int i = 0; i < options.length; i++) {
				System.out.printf("%d. %s%n", i + 1, options[i]);
			}

			// stores integer value
			int choice = Input.readInt(input, "\nChoose an option: ");

			inMenu = handleChoice(choice, input);

		}
	}

	private void addNote(Scanner input) {
		String text = Input.readString(input, "Enter note text: ");

		notes.add(text);
		storage.save(notes);
		System.out.println("Note added!\n");
	}

	private void listNotes() {
		if (notes.isEmpty()) {
			System.out.println("No notes added here...\n");
			return;
		}
		System.out.println();
		for (int i = 0; i < notes.size(); i++) {
			System.out.printf("%d. %s%n", i + 1, notes.get(i));
		}
		System.out.println();
	}

	private void viewNote(Scanner input) {
		if (notes.isEmpty()) {
			System.out.println("No notes to view here...\n");
			return;
		}

		listNotes();
		int index = Input.readInt(input, "Enter note number: ") - 1;
		if (index < 0 || index >= notes.size()) {
			System.out.println("Invalid note number\n");
			return;
		}

		System.out.printf("%n%s%n%n", notes.get(index));
	}

	private void deleteNote(Scanner input) {
		if (notes.isEmpty()) {
			System.out.println("No notes to delete here...\n");
			return;

		}

		listNotes();
		int index = Input.readInt(input, "Enter note number to delete: ") - 1;

		if (index < 0 || index >= notes.size()) {
			System.out.println("Invalid note number\n");
			return;
		}

		System.out.print("Are you sure you want to delete this note? (y/n):");
		String response = input.nextLine().trim().toLowerCase();

		if (response.equals("y") || response.equals("yes")) {
			System.out.println("Note cleared.\n");
			notes.remove(index);
			storage.save(notes);
			System.out.println("Note deleted!\n");
		} else {
			System.out.println("Clear note cancelled\n");
			return;
		}

	}

	private void clearAllNotes(Scanner input) {
		if (notes.isEmpty()) {
			System.out.println("Notes is already empty\n");
			return;
		}

		System.out.print("Are you sure you want to clear all notes? (y/n):");
		String response = input.nextLine().trim().toLowerCase();

		if (response.equals("y") || response.equals("yes")) {
			System.out.println("All notes cleared.\n");
			notes.clear();
			storage.save(notes);
		} else {
			System.out.println("Clear all notes cancelled\n");
			return;
		}
	}

}
