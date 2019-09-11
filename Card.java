//Sorry! Card Class

/*The Card constructor accepts one argument
which is number. The possible numbers
are integers 1-12. Also, in order to
represent the Sorry! card, we use the
number 0.This is an essential task.*/

//**Still need to get a file of Card images together for GUI portion

class Card {
	/**
	 * create main method card @param int this int can be from 1-12
	 */
	// take in int
	// check if int is in range[12] if not invalid.
	private final int value;

	public Card(int numValue) {
		value = numValue;
	}

	public int getValue() {
		return value;
	}

	public String getValueAsString() {
		// Return a String representing the card's value.
		// If the card's value is invalid, "??" is returned.
		switch (value) {
		// there are special rules for numbers 2,7,10,11, and 0--> sorry! card
		case 0:
			return "Sorry! Move any one pawn from Start to a square occupied by any opponent,\n sending that pawn back to its own Start.\n If there are no pawns on the player's Start,\n or no opponent's pawns on any squares,\n the turn is lost. If an enemy's pawn is swapped while\n it is in front of your HOME, your pawn is switched\n EXACTLY where your enemy's pawn is,\n not at your HOME.";
		case 1:
			return "Move a pawn from Start or move a pawn one space forward.";
		case 2:
			return "2, Drawing a two entitles the player to draw again at\n the end of his or her turn. If you cannot use two,\n you can still draw again.";
		case 3:
			return "Move a pawn three spaces forward.";
		case 4:
			return "Move a pawn four spaces backward.";
		case 5:
			return "Move a pawn five spaces forward.";
		case 7:
			return "Move one pawn seven spaces forward or split the \nseven spaces between two pawns \n(such as four spaces for one pawn and three for another). This makes it possible for two pawns\n to enter Home on the same turn, for example.\n The seven cannot be split into a six and one or \na five and two for the purposes of moving out of Start.\n The entire seven spaces must be used one way \nor the other or the turn is lost.\n You also may not move backwards with a split.";
		case 8:
			return "Move a pawn eight spaces forward.";
		case 10:
			return "Move a pawn 10 spaces forward or one space backward.\n If a player cannot go forward 10 spaces,\n then one pawn must go back one space.";
		case 11:
			return "Move 11 spaces forward or switch places with one opposing pawn.\n A player that cannot move 11 spaces is not forced \nto switch and instead can forfeit the turn.";
		case 12:
			return "Move a pawn 12 spaces forward.";
		default:
			return "invalid entry";
		}
	}

	/**
	 * imageFinder returns string of the card data. Use this to find the image that
	 * goes with the card...**we may end up not using this**
	 * 
	 * @return card.
	 */
	public String imageFinder() {
		switch (value) {
		case 0:
			return "0";
		case 1:
			return "1";
		case 2:
			return "2";
		case 3:
			return "3";
		case 4:
			return "4";
		case 5:
			return "5";
		case 7:
			return "7";
		case 8:
			return "8";
		case 10:
			return "10";
		case 11:
			return "11";
		case 12:
			return "12";
		default:
			return "invalid entry";
		}
	}

}
