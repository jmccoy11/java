/*
 * Jonnathon McCoy
 * May 09, 2016
 * KevinBaconGame
 * 
 * BaconNumber.java
 * 
 * This class contains the constructor to be used with
 * BaconGame.java that prints the data from actors.txt to
 * the console and creates a HashMap from the data in
 * relationships.txt and performs task like comparing user
 * input to the data within the HashMap.
 * 
 * Contains a recursive method to determine how many degrees
 * of separation the userInput is from "Kevin Bacon".
 */

package Main;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

/**
 * This class contains the constructor to be used with
 * BaconGame.java that prints the data from actors.txt to
 * the console and creates a HashMap from the data in
 * relationships.txt and performs task like comparing user
 * input to the data within the HashMap.
 *  
 * Contains a recursive method to determine how many degrees
 * of separation the userInput is from "Kevin Bacon".
 * 
 * @author Jonnathon McCoy
 * 
 * @version 1.0
 */
public class BaconNumber 
{
	//fields
	private HashMap<String, String> relationships =
			new HashMap<String, String>();
	private Scanner reader;
	
	
	//constructors
	/**
	 * Default constructor for BaconNumber.java
	 * 
	 * Opens relationships.txt and uses it to build the
	 * relationships HashMap.
	 */
	public BaconNumber()
	{
		try 
		{
			reader = new Scanner(new FileInputStream("relationships.txt"));
			
			while(reader.hasNextLine())
			{
				String line = reader.nextLine();
				String[] temp = line.split(" \\| ");
				relationships.put(temp[0], temp[1]);
			}
		} 
		catch (FileNotFoundException exc) 
		{
			System.out.println("File not Found. " + exc.getMessage());
		}
		catch(Exception exc)
		{
			System.out.println("An error occurred. " +
					exc.getMessage());
		}
		finally
		{
			if(reader != null)
			{
				reader.close();
				System.out.println();
			}
		}//open relationships.txt
	}//BaconNumber() Constructor
	
	
	//methods
	/**
	 * Opens actors.txt and prints the contents of the
	 * file to the console.
	 */
	public void printActors()
	{
		try
		{
			this.reader = 
					new Scanner(new FileInputStream("actors.txt"));
			
			
			while(reader.hasNextLine())
			{
				String line = reader.nextLine();
				System.out.println(line);
			}
		}
		catch(FileNotFoundException exc)
		{
			System.out.println("File not found. " + 
					exc.getMessage());
		}
		catch(Exception exc)
		{
			System.out.println("An error occurred. " +
					exc.getMessage());
		}
		finally
		{
			if(reader != null)
			{
				reader.close();
				System.out.println();
			}
		}//open actors.txt
	}//printActors()
	
	
	/**
	 * Retrieves input from the user and verifies
	 * that the data exists in the relationships
	 * HashMap.  If not, it will continue prompting
	 * for input that printed from printActors().
	 * 
	 * @return String userInput to be passed to
	 * getBaconNumber()
	 */
	public String getInput()
	{
		/*
		 * creating my own Scanner within this class and then
		 * closing it, is closing my System.in stream and for
		 * some reason, I cannot reopen it in my main when I call
		 * repeat().  I get NoSuchElementException.  Either I can
		 * forgo closing the scanner in this method (which gives
		 * me a resource leak warning from the compiler) or I make
		 * the scanner from the driver class public static and
		 * use it within the BaconNumber class.  I'm not really
		 * sure which is better.
		 */
		
		//Scanner console = new Scanner(System.in);
		String userInput = BaconGame.console.nextLine();
		
		while(true)
		{
			if(relationships.containsKey(userInput))
			{
				//console.close();
				break;
			}
			else
			{
				System.out.println("Please enter an actor from the list below.");
				printActors();
				userInput = BaconGame.console.nextLine();
			}
		}
		
		return userInput;
	}//getInput()
	
	
	/**
	 * A recursive call that finds the name as a key within
	 * relationships HashMap and takes the split() of the
	 * value associated with that key.  This split is used to 
	 * print a message to the console explaining the relationship 
	 * to the name passed to this method.  The split of the value 
	 * also contains a new name to be passed on to a further call to
	 * getBaconNumber().
	 * 
	 * If name does not equal "Kevin Bacon" the method will call
	 * itself with a new name split from the value of the previous
	 * name.
	 * 
	 * @param name String name split from the value of the original 
	 * name passed to getBaconNumber()
	 * 
	 * @param baconNumber Integer initially set as zero.  Each time the
	 * method calls itself, the baconNumber is increased by one.
	 * 
	 * @return Returns the int baconNumber when the recursive method
	 * getBaconNumber() finally completes.
	 */
	public int getBaconNumber(String name, int baconNumber)
	{	
		//name verified to be in relationships HashMap
		//in getInput()
		String value = relationships.get(name);
		String[] temp = value.split(" : ");
		
		//base case
		if(temp[0].equals("Kevin Bacon")) 
		{
			System.out.println(name + " starred in \"" + temp[1] + "\" with " +
					temp[0] +".");
			return baconNumber;
		}
		else
		{
			System.out.println(name + " starred in \"" + temp[1] + "\" with " +
					temp[0] +".");
			
			//recursive call
			int result = getBaconNumber(temp[0], baconNumber + 1);
			
			return result;
		}
	}//getBaconNumber()
}//BaconNumber.java
