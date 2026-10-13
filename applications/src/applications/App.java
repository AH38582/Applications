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
		System.out.printf("Launching %s...%n", getName());
		System.out.printf("%s%n", getDescription());
		run(input);
		
	}
	
	protected void openApp() {
		
	}
	
	protected void closeApp() {
		
	}

	protected void run(Scanner input) {
	}
		

	
	

	

	
	
	

}