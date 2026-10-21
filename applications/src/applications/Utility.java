package applications;

public abstract class Utility extends App implements HasMenu {

	public Utility(String name, String description) {
		super(name, description);

	}

	protected abstract void clearAllData();

	protected abstract void printAllData();

}
