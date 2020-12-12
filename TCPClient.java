import java.net.*;  //the package for networking code
import java.io.*;   //the package for input/output, similar to iostream

public class TCPClient
{
	public static void main(String args[]) throws Exception
	{
		BufferedReader inFromKeyboard = new BufferedReader(new InputStreamReader(System.in));

		System.out.println("Welcome to the TCP Client!");
		System.out.println("Enter the IP address of the machine to connect to: ");
		String IPToConnectTo = inFromKeyboard.readLine();

		System.out.println("Enter the port number of the application to connect to: ");
		String PortToConnectTo = inFromKeyboard.readLine();
		int port = Integer.parseInt(PortToConnectTo);

		Socket clientSocket = new Socket(IPToConnectTo, port);
		DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
		BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

		System.out.println("Enter in a request to the server: ");
		String request = inFromKeyboard.readLine();
		outToServer.writeBytes(request + "\n");
		String responseFromServer = inFromServer.readLine();

		System.out.println("Server Response: " + responseFromServer);

		// Close
		clientSocket.close();
	}
}