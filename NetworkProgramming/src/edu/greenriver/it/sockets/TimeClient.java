package edu.greenriver.it.sockets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class TimeClient 
{
	public static void main(String args[])
	{
		try(Socket server = new Socket(InetAddress.getByName("localhost"),
				TimeServer.PORT))
		{
			BufferedReader readFromServer = new BufferedReader(new InputStreamReader(
					server.getInputStream()));
			
			String line = null;
			while((line = readFromServer.readLine()) != null)
			{
				System.out.println(line);
			}
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
