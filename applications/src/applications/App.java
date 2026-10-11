package applications;

import java.util.Scanner;

public abstract class App implements Launchable {
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

	@Override
	public void launch(Scanner input) {
		System.out.printf("Launching %s...%n%n", name);
		run(input);
		
	}

	protected abstract void run(Scanner input);
	

	

	
	
	

}