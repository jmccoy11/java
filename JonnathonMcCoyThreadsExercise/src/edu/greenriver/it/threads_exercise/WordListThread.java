package edu.greenriver.it.threads_exercise;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class WordListThread extends Thread
{
	private String filename;
	
	public WordListThread(String filename)
	{
		this.filename = filename;
	}
	
	@Override
	public void run()
	{
		Scanner reader = null;
		
		try 
		{
			reader = new Scanner(new File(filename));
		} 
		catch (FileNotFoundException exc) 
		{
			System.out.println("File not found: " + exc.getMessage());
		}
		
		while(reader.hasNextLine())
		{
			SharedData.add(reader.nextLine());
		}
		
		reader.close();
	}
}
