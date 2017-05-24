package edu.greenriver.it.sockets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class EchoClient 
{
	public static void main(String args[])
	{
		try(Socket server = new Socket(InetAddress.getByName("localhost"),
				EchoServer.PORT))
		{
			BufferedReader readFromServer = new BufferedReader(new InputStreamReader(
					server.getInputStream()));
			PrintWriter sendToServer = new PrintWriter(server.getOutputStream(), true);
			
			//send a message to the server
			sendToServer.println("Hello server, how are you today?");
			
			System.out.println(readFromServer.readLine());
			
//			String line = null;
//			while((line = readFromServer.readLine()) != null)
//			{
//				System.out.println(line);
//			}
		} 
		catch (UnknownHostException e) 
		{
			e.printStackTrace();
		} 
		catch (IOException e) 
		{
			System.out.println("Client socket error: " + e.getMessage());
		}
	}
}
