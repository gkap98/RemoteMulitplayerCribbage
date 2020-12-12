public class Card {
	// some constants for cards
	// suits
	public final static String HEARTS = "Hearts";
	public final static String DIAMONDS = "Diamonds";
	public final static String CLUBS = "Clubs";
	public final static String SPADES = "Spades";

	// face card names
	public final static String JACK = "Jack";
	public final static String QUEEN = "Queen";
	public final static String KING = "King";
	public final static String ACE = "Ace";

	// value of face cards
	public final static int JACK_VALUE = 11;
	public final static int QUEEN_VALUE = 12;
	public final static int KING_VALUE = 13;
	public final static int ACE_VALUE = 14;

	// each card has a numeric value and a suit
	private int numericValue;
	private String suit;

	public Card(int n, String s) throws CardException {
		// set the card values
		setNumericValue(n);
		setSuit(s);
	}

	public int getNumericValue() {
		return numericValue;
	}

	public void setNumericValue(int n) throws CardException {
		// do a range check
		if (n >= 2 && n <= Card.ACE_VALUE) {
			numericValue = n;
		} else {
			throw new CardException("Invalid numeric value");
		}
	}

	public String getSuit() {
		return suit;
	}

	public void setSuit(String s) throws CardException {
		// do a suit check
		if (s.equals(Card.HEARTS) || s.equals(Card.DIAMONDS) || s.equals(Card.CLUBS) || s.equals(Card.SPADES)) {
			suit = s;
		} else {
			throw new CardException("Invalid suit");
		}
	}

	@Override
	public String toString() {
		// print the card
		StringBuilder builder = new StringBuilder();

		if (getNumericValue() <= 10) {
			builder.append(Integer.toString(getNumericValue()));
		} else if (getNumericValue() == Card.JACK_VALUE) {
			builder.append(Card.JACK);
		} else if (getNumericValue() == Card.QUEEN_VALUE) {
			builder.append(Card.QUEEN);
		} else if (getNumericValue() == Card.KING_VALUE) {
			builder.append(Card.KING);
		} else if (getNumericValue() == Card.ACE_VALUE) {
			builder.append(Card.ACE);
		}

		builder.append(" of ");
		builder.append(getSuit());

		return builder.toString();
	}
}