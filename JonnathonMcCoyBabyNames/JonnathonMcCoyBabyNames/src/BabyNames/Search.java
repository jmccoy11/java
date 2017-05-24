/*
 * Jonnathon McCoy
 * 04/26/2016
 * Search.java
 * 
 * This class creates a Search object to handle
 * searches in babynames.txt and nametypes.txt
 * to create an output to the console with the
 * results of the desired name to be searched for.
 */

package BabyNames;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * This class creates a Search object to handle
 * searches in babynames.txt and nametypes.txt
 * to create an output to the console with the
 * results of the desired name to be searched for.
 * 
 * @author Jonnathon McCoy
 * @version 1.0
 */
public class Search 
{
	private String name;
	private String nameType = "";
	private final String BABY_NAMES_FILE = "babynames.txt";
	private final String NAME_TYPES_FILE = "nametypes.txt";
	private int appears = 0;
	
	/**
	 * Creates a Search object
	 * 
	 * @param name The name requested to search for
	 */
	public Search(String name)
	{
		this.name = name;
	}//Search constructor
	
	/**
	 * Searches nametypes.txt for the name and
	 * sets nameType to boy or girl
	 */
	public void findType()
	{
		Scanner reader = null;
		
		try
		{
			reader = new Scanner(new FileInputStream(NAME_TYPES_FILE));
			
			while(reader.hasNextLine())
			{
				/* 
				 * data format "NAME - boy/girl"
				 * split line into two indices
				 * lineIndex[0] = NAME
				 * lineIndex[1] = boy/girl
				 */
				String line = reader.nextLine();
				String[] lineIndex = line.split(" - ");
				
				if (lineIndex[0].equals(name.toUpperCase()) &&
						lineIndex[1].equals("boy") ||
						lineIndex[1].equals("girl"))
				{
					nameType = lineIndex[1];
					break;
				}
			}
		}
		catch (FileNotFoundException ex)
		{
			System.out.println("Error encountered when attempting "
					+ "to open " + NAME_TYPES_FILE + ex.getMessage());
		}
		finally
		{
			if(reader != null)
			{
				reader.close();
			}
		}
	}//findType()
	
	/**
	 * Searches babynames.txt for the name and
	 * counts how many times it appears.
	 */
	public void findName()
	{
		Scanner reader = null;
		
		try
		{
			reader = new Scanner(new FileInputStream(BABY_NAMES_FILE));
			
			while(reader.hasNextLine())
			{
				if (name.toUpperCase().equals(reader.nextLine()))
				{
					//count how many times the name appears
					//in babynames.txt
					appears++;
				}
			}
		}
		catch (FileNotFoundException ex)
		{
			System.out.println("Error encountered when attempting "
					+ "to open " + BABY_NAMES_FILE + ex.getMessage());
		}
		finally
		{
			if(reader != null)
			{
				reader.close();
			}
		}
	}//findName()
	
	/**
	 * Print the results of the search to the console.
	 */
	public void printResults()
	{
		if(!nameType.isEmpty() && appears > 0)
		{
			System.out.println(name + " is a " + nameType +
					"'s name and appears " + appears + 
					" times in " + BABY_NAMES_FILE + ".");
		}
		else
		{
			System.out.println(name + " was not found in " + 
				BABY_NAMES_FILE +	".");
		}
	}//printResults()
	
	/**
	 * Used for debugging purposes
	 * 
	 * returns String name, nameType, file names, and 
	 * appears count
	 */
	public String toString() 
	{
		return "Search [name=" + name + ", nameType=" + nameType + 
				", babyNamesFile=" + BABY_NAMES_FILE + 
				", nameTypesFile=" + NAME_TYPES_FILE + 
				", appears=" + appears + "]";
	}//toString()
}//Search.java
