package applications;

import java.util.Scanner;

public class App {
	private String name;
	private String description;

	public App(String name, String description) {
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

	public void launch(Scanner input) {
		openApp();
		System.out.printf("%s%n", getDescription());
		run(input);
		closeApp();
	}
	
	protected void openApp() {
		System.out.printf("Opening %s...%n", getName());
	}
	
	protected void closeApp() {
		System.out.printf("Closing %s...", getName());
	}

	protected void run(Scanner input) {
	}
		

	
	

	

	
	
	

}