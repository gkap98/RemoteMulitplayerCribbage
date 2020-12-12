import java.util.*;

public class Driver {
	public static void main(String args[]) {
		try
		{
			//create a deck
			DeckInterface d = new Deck();

			//deal 5 cards
			List < Card > hand = d.dealCards(5);

			//go through the cards and print each one
			for(Card card : hand)
			{
				System.out.println(card.toString());
			}
		}
		catch(DeckException ex)
		{
			ex.printStackTrace();
		}
	}
}