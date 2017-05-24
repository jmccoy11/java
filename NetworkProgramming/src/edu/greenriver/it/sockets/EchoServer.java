package edu.greenriver.it.sockets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer 
{
	//reserve a port
	public static final int PORT = 4000;
	
	public static void main(String args[])
	{
		//we are using a try-with-resources and this is guaranted
		//to close our server socket when we're done.
		try(ServerSocket server = new ServerSocket(PORT)) //try-with-resources
		{
			System.out.println("The server is starting...");
			
			while(true)
			{
				//wait until a client makes a connection
				Socket client = server.accept();
				
				PrintWriter sendToClient = new PrintWriter(client.getOutputStream(), true);
				BufferedReader readFromClient = new BufferedReader(
						new InputStreamReader(client.getInputStream()));
				
				//read in content from the client and send it back!
				String line = null;
				while((line = readFromClient.readLine()) != null)
				{
					sendToClient.print("echo: " + line);
				}
				
				client.close();
			}
			
		} 
		catch (IOException e) 
		{
			System.out.println("Error with server socket: " + e.getMessage());
		}
	}	
}
