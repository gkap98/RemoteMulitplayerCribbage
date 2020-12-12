import java.net.*;
import java.io.*;
import java.util.*;

public class TCPServer {
	public static void main(String args[]) throws Exception {
		// can call a function tp print info about this server
		printIPInfo();

				ServerSocket welcomeSocket = new ServerSocket(55555);

		while(true) {
			Socket mySocket = welcomeSocket.accept();
			String clientIP = mySocket.getInetAddress().getHostAddress();
			System.out.println("The IP address of the client making a request is: " + clientIP);
			DataOutputStream outToClient = new DataOutputStream(mySocket.getOutputStream());
			BufferedReader inFromClient = new BufferedReader(new InputStreamReader(mySocket.getInputStream()));


			//read the request from the client
			//block until a request is made
			String requestFromClient = inFromClient.readLine();

			//print out the request to the screen
			System.out.println("Client Request: " + requestFromClient);

			//enforce a protocol
			if(requestFromClient.equalsIgnoreCase("Hello")) {
				//the request is for the date, send the current date back
				outToClient.writeBytes("Hi There");
			} else if(requestFromClient.equalsIgnoreCase("What's Up?")) {

				//write that string value to the socket
				outToClient.writeBytes("Nothin Much");
			} else {
				outToClient.writeBytes("Invalid Request!\n");
			}

			//close the socket
			mySocket.close();
		}

	}

	public static void printIPInfo() throws Exception {
		System.out.println("Welcome to the TCP Server!");
			//InetAddress
			String myIP = InetAddress.getLocalHost().getHostAddress();
			System.out.println("My IP address is " + myIP);

			String myHostName = InetAddress.getLocalHost().getHostName();
			System.out.println("My host name is " + myHostName);
	}
}