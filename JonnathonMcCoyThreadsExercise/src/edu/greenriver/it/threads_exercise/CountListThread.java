/* 
 * Jonnathon McCoy
 * February 17, 2017
 * 
 * Package: edu.greenriver.it.threads_exercise
 * Class: CountListThread.java
 * 
 * A class that extends from the Thread class to count the lines of data
 *   in a file.
 */

package edu.greenriver.it.threads_exercise;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * A class that extends from the Thread class to count the lines of data
 *   in a file.
 *    
 * @author Jonnathon McCoy
 * @version 1.0
 */
public class CountListThread extends Thread
{
	private String listName;
	private int lineCount;
	
	/**
	 * Parameterized constructor for the CountListThread Class.
	 *  
	 * @param listName - String - Takes the name of the file, in this case a list of words.
	 */
	public CountListThread(String listName)
	{
		this.listName = listName;
	}//CountListThread constructor
	
	@Override
	/**
	 * Opens a file and counts the lines of data in the file.
	 * Sets the number of lines to the lineCount field.
	 */
	public void run()
	{
		int lineCount = 0;
		Scanner reader = null;
		
		try //try to open the file
		{
			reader = new Scanner(new File(listName));
		} 
		catch (FileNotFoundException exc) 
		{
			System.out.println("File not found: " + exc.getMessage());
		}//try/catch
		
		//while the file has data
		while(reader.hasNextLine())
		{
			lineCount++;  //increase the lineCount by one
			reader.nextLine(); //move to the nextLine in the file.
		}//while reader.hasNextLine()
		
		setLineCount(lineCount);
		
		reader.close();
	}//run()
	
	/**
	 * Getter for the listName field.
	 * 
	 * @return - String - listName field.
	 */
	public String getListName()
	{
		return listName;
	}//getListName()
	
	/**
	 * Getter for the lineCount field
	 * 
	 * @return - String - lineCount field.
	 */
	public int getLineCount()
	{
		return lineCount;
	}//getLineCount()
	
	/**
	 * Setter for the lineCount field
	 * 
	 * @param lineCount - int - new lineCount.
	 */
	public void setLineCount(int lineCount)
	{
		this.lineCount = lineCount;
	}//setLineCount()
}//CountListThread.java
