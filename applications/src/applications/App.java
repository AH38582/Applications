package applications;

import java.util.Scanner;

public abstract class App {
	private String name;
	private String description;

	public App() {
		setName("Unnamed");
		setDescription("Undescribed");
	}

	public App(String name, String description) {
		this(); // -> App()
		setName(name);
		setDescription(description);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	/**
	 * The lifecycle of an App object (Opens -> Runs -> Closes)
	 * @param input used for choosing from menu
	 */
	public void launch(Scanner input) {
		onOpen();
		System.out.printf("%s%n%n", getDescription());
		run(input);
		onClose();
	}

	protected void onOpen() {
		System.out.printf("Opening %s...%n", getName());
	}

	protected void onClose() {
		System.out.printf("Closing %s...%n%n", getName());
	}

	@Override
	public String toString() {
		return String.format("%s: %s", getName(), getDescription());
	}

	protected abstract void run(Scanner input);

}