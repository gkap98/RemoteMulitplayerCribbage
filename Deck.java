import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck implements DeckInterface {
	//holds a collection of 52 cards
	private List < Card > cards;

	public Deck() {
		//create the list of cards
		cards = new ArrayList < Card >();

		//fill the list
		createCards();

		//shuffle the cards
		Collections.shuffle(cards);
	}

	@Override
	public List < Card > dealCards(int numRequestedCards) throws DeckException {
		//create a list of cards to return to the user
		List < Card > handOfCards = new ArrayList < Card >();

		//if there are enough cards to support the request
		if(numRequestedCards <= cards.size()) {
			//remove a card from the deck and add it to the hand
			for(int i = 0;i < numRequestedCards;i++) {
				//get the card at the end of the list
				handOfCards.add(cards.remove(cards.size() - 1));
			}
		}
		else {
			throw new DeckException("Not enough cards to deal out");
		}

		return handOfCards;
	}

	private void createCards() {
		try {
			//go through all the numeric values from 2 - ACE
			for(int numValue = 2;numValue <= Card.ACE_VALUE;numValue++) {
				//create a card for each suit
				cards.add(new Card(numValue, Card.HEARTS));
				cards.add(new Card(numValue, Card.DIAMONDS));
				cards.add(new Card(numValue, Card.CLUBS));
				cards.add(new Card(numValue, Card.SPADES));
			}
		}
		catch(CardException ex) {
			ex.printStackTrace();
		}
	}
}