/*
 * Jonnathon McCoy
 * April 20, 2016
 * GameDriver.java
 * 
 * The Longest Adventure Assignment
 * 
 * Driver class that contains the main method for the program.
 */

package game;

import java.util.InputMismatchException;
import java.util.Scanner;
import players.*;

/**
 * Driver class that contains the main method for the program.
 * 
 * @author Jonnathon McCoy
 * @version 1.0
 */
public class GameDriver
{
	private static Scanner console = new Scanner(System.in);
	private static Player player;
	
	/**
	 * Entry point for the GameDriver program
	 * 
	 * @param args Command-line arguments
	 */
	public static void main(String[] args)
	{
		/*
		 * You should greet the user and gather all game
		 * related values before sending them to the 
		 * game object below.
		 */
		greeting();
		
		int getTiles = 0;
		while(getTiles == 0) //Positive Integer validation loop
		{
			try
			{
				getTiles = console.nextInt();
				System.out.println();
				
				if(getTiles < 0)
				{
					throw new InputMismatchException();
				}
			}	
			catch (InputMismatchException e)
			{
				console.nextLine(); //not entirely sure why but this is needed
				System.out.println("Please enter a valid positive number.");
				getTiles = 0;
			}
			catch (Exception e)
			{
				System.out.println("Something went wrong!");
				System.out.println(e);
			}
		}

		System.out.println("Enter your player name: ");
		console.nextLine(); //not entirely sure why but this is needed
		String getPlayerName = console.nextLine();
		System.out.println();
		
		typeFlavorText();
		
		String heroType = console.nextLine();
		
		createChar(heroType, getPlayerName);
		
		//send in the number of tiles and a new player object here
		Game game = new Game(getTiles, player);
		game.startGame();
		
		console.close();
	}
	
	private static void greeting()
	{
		System.out.println("Welcome to the Longest Adventure");
		System.out.println("********************************");
		System.out.println();
		System.out.println("Please choose a number of tiles: ");
	}
	
	private static void typeFlavorText()
	{
		System.out.println("Please choose a player type (Knight, Princess, "
				+ "or Squire):");
		System.out.println("KNIGHT - Tough travelers that can weather any "
				+ "adversity!");
		System.out.println("PRINCESS - Smart and savvy, a princess is never "
				+ "caught off guard!");
		System.out.println("SQUIRE - Squires have a knack for getting there "
				+ "quicker!");
	}
	
	private static void createChar(String heroType, String playerName)
	{
		while(true)
		{
			if(heroType.toLowerCase().equals("knight") ||
					heroType.toLowerCase().equals("princess") ||
					heroType.toLowerCase().equals("squire"))
			{
				break;
			}
			else
			{
				System.out.println("Please enter a Knight, Princess, "
						+ "or Squire.");
				heroType = console.nextLine();
			}
		}
		
		if(heroType.toLowerCase().equals("knight"))
		{
			player = new Knight(playerName);
		}
		else if(heroType.toLowerCase().equals("princess"))
		{
			player = new Princess(playerName);
		}
		else
		{
			player = new Squire(playerName);
		}
		System.out.println();
	}
}
