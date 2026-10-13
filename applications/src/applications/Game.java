package applications;


public abstract class Game extends App implements Playable {

	public Game(String name, String description) {
		super(name, description);
		// TODO Auto-generated constructor stub
	}

	@Override
	public abstract void startGame();
	
	@Override 
	public abstract void rules();

}
