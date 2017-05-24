/*
 * Jonnathon McCoy
 * 04/28/2016
 * SearchHistory.java
 * 
 * Creates an object to handle writing and reading
 * from the searchhistory.txt file.
 */

package BabyNames;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * Creates an object to handle writing and reading
 * to/from the searchhistory.txt file.
 * 
 * @author Jonnathon McCoy
 * @version 1.0
 */
public class SearchHistory 
{
	private final String OUTPUT_FILE;
	
	/**
	 * Creates a SearchHistory object to handle
	 * writing and reading the searchhistory.txt
	 * file.
	 * 
	 * @param file passes the file name to the
	 * searchHistory object instead of creating.  
	 * a final variable to store. This code is 
	 * intended for potential re-use in upcoming 
	 * projects.
	 */
	public SearchHistory(String file)
	{
		this.OUTPUT_FILE = file;
	}//SearchHistory constructor
	
	/**
	 * Writes the name to searchhistory.txt
	 * 
	 * @param name Name of the person searched
	 * for
	 */
	public void writeToFile(String name)
	{
		PrintWriter writer = null;
		
		try
		{
			writer = new PrintWriter(new FileOutputStream(
					OUTPUT_FILE,true));
			
			writer.println("You searched for " + name.toLowerCase());
		}
		catch (FileNotFoundException ex)
		{
			System.out.println("Error encountered when attempting "
					+ "to open " + OUTPUT_FILE + ex.getMessage());
		}
		finally
		{
			if(writer != null)
			{
				writer.close();
			}
		}
	}//writeToFile()
	
	/**
	 * Print Search History from searchhistory.txt
	 * to the console
	 */
	public void printSearchHistory()
	{
		Scanner reader = null;
		
		try
		{
			reader = new Scanner(new FileInputStream(OUTPUT_FILE));
			
			while(reader.hasNextLine())
			{
				System.out.println(reader.nextLine());
			}
		}
		catch (FileNotFoundException ex)
		{
			System.out.println("Error encountered when attempting "
					+ "to open " + OUTPUT_FILE + ex.getMessage());
		}
		finally
		{
			if(reader != null)
			{
				reader.close();
			}
		}
	}//printSearchHistory()
	
	/**
	 * Clears Search History in searchhistory.txt
	 */
	public void clearHistory()
	{
		PrintWriter writer = null;
		
		try
		{
			writer = new PrintWriter(new FileOutputStream(OUTPUT_FILE));
		}
		catch (FileNotFoundException ex)
		{
			System.out.println("Error encountered when attempting "
					+ "to open " + OUTPUT_FILE + ex.getMessage());
		}
		finally
		{
			if(writer != null)
			{
				writer.close();
			}
		}
	}//clearHistory()
	
	/**
	 * Used for debugging purposes
	 * 
	 * returns String filename
	 */
	public String toString()
	{
		return "Search History [File: " + OUTPUT_FILE + "]";
	}//toString()
}
