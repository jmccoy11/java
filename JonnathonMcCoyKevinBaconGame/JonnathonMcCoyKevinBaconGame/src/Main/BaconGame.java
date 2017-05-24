/*
 * Jonnathon McCoy
 * May 09, 2016
 * KevinBaconGame
 * 
 * BaconGame.java
 * 
 * This is the driver class for the Kevin Bacon Game.  
 * 
 * The purpose of this game is to compare a user input for 
 * an actor within a given list to find out how many degrees
 * of separation Kevin Bacon has from the user input with 
 * regards to movies they have acted in.
 */

package Main;

import java.util.Scanner;

/**
 * This is the driver class for the Kevin Bacon Game.
 * 
 * @author Jonnathon McCoy
 * 
 * @version 1.0
 */
public class BaconGame 
{
	//fields
	public static final Scanner console = new Scanner(System.in);
	private static Boolean mainLoop;
	
	
	//methods
	/**
	 * This is the entry point for the program.
	 * 
	 * @param args Command-line arguments
	 */
	public static void main(String[] args) 
	{
		mainLoop = true;
		
		while(mainLoop)
		{
			BaconNumber game = new BaconNumber();
			
			greeting();
			game.printActors();
			
			String userInput = game.getInput();
			int baconNumber = game.getBaconNumber(userInput, 0);
			
			System.out.println("The bacon number for " + userInput + " is " + 
					baconNumber + ".");
			
			repeat();
		}
		
		console.close();
	}
	
	//program greeting
	private static void greeting()
	{
		System.out.println("Welcome to the Kevin Bacon " +
				"Game!");
		System.out.println();
		System.out.println("Please enter a person below:");
	}
	
	
	//request user if they would like to play again
	private static void repeat()
	{
		System.out.println();
				
		while(true)
		{
			System.out.println("Enter \"yes\" to continue the game. "
					+ "\"no\" to exit.");
			String userInput = console.nextLine();
			
			if(userInput.toLowerCase().equals("yes"))
			{
				break;
			}
			else if(userInput.toLowerCase().equals("no"))
			{
				System.out.println("Thank you for playing. Program closing.");
				mainLoop = false;
				break;
			}
		}//verification loop
	}//repeat()
}//BaconGame.java
