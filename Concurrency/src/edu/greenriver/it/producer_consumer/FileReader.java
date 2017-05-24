package edu.greenriver.it.producer_consumer;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FileReader extends Thread
{
	private String fileName;
	
	public FileReader(String fileName)
	{
		this.fileName = fileName;
	}
	
	@Override
	public void run()
	{
		try(Scanner reader = new Scanner(new FileInputStream(fileName)))
		{
			while(reader.hasNextLine())
			{
				String word = reader.nextLine();
				
				try 
				{
					SharedWordQueue.addWord(word);
				} 
				catch (InterruptedException e) 
				{
					//do nothing
				}
			}
		}
		catch(FileNotFoundException exc)
		{
			System.out.println("Error reading from file: " + exc.getMessage());
		}
	}
}
