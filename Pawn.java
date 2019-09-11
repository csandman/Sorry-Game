public class Pawn {
	private int position;
	private String color;
	private int startPosition;

	public Pawn(String color, int startPosition) {
		this.color = color;
		this.startPosition = startPosition;
	}

	// setters
	public void setColor(String color) {
		this.color = color;
	}

	public void setPosition(int position) {
		this.position = position;
	}

	// getters
	public String getColor() {
		return color;
	}

	public int getPosition() {
		return position;
	}

	public int getStartPosition() {
		return startPosition;
	}

	public boolean isHome() {
		// if (this.getPosition == somehome)
		// return true;
		// else
		// return false;

		return false;
	}
}