package applications;


public abstract class Game extends App implements HasMenu {

	public Game(String name, String description) {
		super(name, description);
	}

	public abstract void rules();

	protected void onGameStart() {
		System.out.println("\n--- Game started ---");
	}
	
	protected void onGameOver(boolean playerWon) {
		System.out.println(playerWon ? "\nYou win!" : "\nGame over.");
	}
	
}
