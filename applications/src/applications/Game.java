package applications;


public abstract class Game extends App implements HasMenu {

	public Game(String name, String description) {
		super(name, description);
	}

	public abstract void rules();

	
}
