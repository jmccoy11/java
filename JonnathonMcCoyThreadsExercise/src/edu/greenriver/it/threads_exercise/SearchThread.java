/* 
 * Jonnathon McCoy
 * February 17, 2017
 * 
 * Package: edu.greenriver.it.threads_exercise
 * Class: SearchThread.java
 * 
 * A class that extends from the Thread class to search for a String within a file.
 */

package edu.greenriver.it.threads_exercise;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * A class that extends from the Thread class to search for a String within a file.
 * 
 * @author Jonnathon McCoy
 * @version 1.0
 */
public class SearchThread extends Thread
{
	private String filename;
	private String term;
	
	/**
	 * Parameterized constructor for the SearchThread.
	 * 
	 * @param filename - String - name of file.
	 * @param term - String - Desired word to search for
	 */
	public SearchThread(String filename, String term)
	{
		this.filename = filename;
		this.term = term;
	}//SearchThread constructor
	
	@Override
	/**
	 * Opens a file and compares the data to the term.
	 * 
	 * If the term is found, it will break out of the loop.
	 */
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
		}//try/catch
		
		while(reader.hasNext())
		{
			String compare = reader.next();
			
			if(compare.equalsIgnoreCase(term))
			{
				System.out.println("\"" + term + "\" found in " + filename);
				break;
			}
		}//while reader.hasNext()
		
		reader.close();
	}//run()
}//SearchThread.java
