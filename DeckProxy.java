import java.util.*;
import java.net.*;
import java.io.*;
import javax.swing.*;

public class DeckProxy implements DeckInterface {
	@Override
	public List<Card> dealCards(int numberOfCards) throws DeckException {
		// TODO Auto-generated method stub
		List<Card> retVal = new ArrayList<Card>();
		String IPToConnectTo;
		int port = 53140;
		String request;

		IPToConnectTo = JOptionPane.showInputDialog(null, "Enter in the IP Address of the Game: ");

		try {
			// Create Socket
			Socket clientSocket = new Socket(IPToConnectTo, port);

			DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());

			// Can use inFromServer to read in to the socket
			BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

			// Create the request
			request = "dealCards: " + numberOfCards;

			// Print the request
			System.out.println("Request: " + request);

			outToServer.writeBytes(request + "\n");

			// Wait for a response from the server

			String responseFromServer = inFromServer.readLine();

			if (responseFromServer.equals("Invalid Request")) {
				throw new DeckException("Invalid deck request");
			}

			String[] cardStrings = responseFromServer.split(":");

			for (String cardString : cardStrings) {
				String[] cardParts = cardString.split(" of ");
				int numericValue;
				String suit = cardParts[1];

				if (cardParts[0].equals("Jack")) {
					numericValue = 11;
				} else if (cardParts[0].equals("Queen")) {
					numericValue = 12;
				} else if (cardParts[0].equals("King")) {
					numericValue = 13;
				} else if (cardParts[0].equals("Ace")) {
					numericValue = 14;
				} else {
					numericValue = Integer.parseInt(cardParts[0]);
				}

				Card c = new Card(numericValue, suit);
				retVal.add(c);
			}

			// close the socket after we are done with it
			clientSocket.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return retVal;
	}
}