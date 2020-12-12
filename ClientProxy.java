import java.net.*;
import java.io.*;
import java.util.*;

public class ClientProxy {
	public static void main(String[] args) {
		try {
			// Create a deck to reference
			DeckInterface d = new Deck();

			int portNumber = 53140;

			ServerSocket welcomeSocket = new ServerSocket(portNumber);

			while(true) {
				// Bliock the server until connection is created
				Socket serverSocket = welcomeSocket.accept();

				// Like you said, you can use outToClient to write to the socket
				DataOutputStream outToClient = new DataOutputStream(serverSocket.getOutputStream());

				BufferedReader inFromClient = new BufferedReader(new InputStreamReader(serverSocket.getInputStream()));

				String requestFromClient = inFromClient.readLine();

				System.out.println("Client Request: " + requestFromClient);

				String[] tokens = requestFromClient.split(" - ");

				if (tokens.length == 2 && tokens[0].equalsIgnoreCase("dealCards")) {
					try {
						// Get number of cards needed
						int numRequestedCareds = Integer.parseInt(tokens[1]);

						// ask the deck for cards
						List < Card > cards = d.dealCards(numRequestedCareds);

						StringBuilder builder = new StringBuilder();

						for (Card card : cards) {
							builder.append(card.toString());
							builder.append(" - ");
						}

						// Remove extra syntax
						builder.replace(builder.length() - 1, builder.length(), "");

						outToClient.writeBytes(builder.toString() + "\n");
					} catch (Exception e) {
						outToClient.writeBytes("Invalid Requests \n");
					}
				} else {
					// Invalid message
					outToClient.writeBytes("Invalid Request to client \n");
				}

				// Close the socket
				serverSocket.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}