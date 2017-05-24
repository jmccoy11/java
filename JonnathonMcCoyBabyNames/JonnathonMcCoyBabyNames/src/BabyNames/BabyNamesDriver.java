/*
 * Jonnathon McCoy
 * 04/26/2016
 * BabyNamesDriver.java
 * 
 * A program that takes a name from a user and searches
 * through a file named babynames.txt which is the top
 * 100 baby names in 2013 in Oregon.
 * 
 * The program will then tell you how many times it shows
 * in the file and will search another file for whether the
 * name is a boy or girl's name.
 * 
 * The search is recorded in a searchhistory.txt file from
 * which the user can view their search history and clear
 * the search history log.
 */

package BabyNames;

import BabyNames.Search;
import BabyNames.SearchHistory;
import java.util.Scanner;

/**
 * Driver class for BabyNames package.
 * 
 * @author Jonnathon McCoy
 * @version 1.0
 */
public class BabyNamesDriver 
{
	private static boolean mainLoop = true;
	private static Scanner console = new Scanner(System.in);
	private static Search search;
	private static SearchHistory history = 
			new SearchHistory("searchhistory.txt");
	
	/**
	 * Entry point for BabyNames package.
	 * 
	 * @param args Command line arguments
	 */
	public static void main(String[] args) 
	{
		greeting();
		
		while(mainLoop)
		{
			menu();
		}
		
		console.close();
	}//run
	
	//print greeting to console
	private static void greeting()
	{
		System.out.println("What is your favorite "
				+ "baby name?");
		System.out.println("The following program prints "
				+ "statistics for the top");
		System.out.println("100 popular boy and girl names"
				+ " in Oregon during 2013.");
		System.out.println("");
	}//greeting()
	
	//print menu and retrieve input from user
	private static void menu()
	{
		System.out.println("** Menu **");
		System.out.println("1. Enter a baby name");
		System.out.println("2. View your search history");
		System.out.println("3. Exit this program");
		
		while(!console.hasNextInt())
		{
			System.out.println("Please enter a valid menu"
					+ " option.");
			console.nextLine();
		}
		int userInput = console.nextInt();
		System.out.println();
		
		resolveMenu(userInput);
	}//menu()
	
	//resolve input from user
	private static void resolveMenu(int userInput)
	{
		//1. Enter a baby name
		if(userInput == 1)
		{
			//clear buffer
			console.nextLine();
			
			//get input
			System.out.print("Enter a name: ");
			String getName = console.nextLine();
			
			//instantiate search object
			search = new Search(getName);
			
			//save search history
			history.writeToFile(getName);

			//read two separate files for data
			search.findType();
			search.findName();
			
			//print results
			search.printResults();
			System.out.println();			
			System.out.println("Press Enter to continue.");
			System.out.println();
			console.nextLine();
		}
		//2. View your search history
		else if(userInput == 2)
		{
			//clear buffer
			console.nextLine();
			
			System.out.println("Printing your search "
					+ "history to the console.");
			System.out.println();
			
			//print search history
			history.printSearchHistory();
			System.out.println();
			
			//ask user if they'd like to clear their history
			clearHistory();
		}
		//3. Exit this program
		else if(userInput == 3)
		{
			//end program
			System.out.println("Exiting program...");
			System.out.println();
			System.out.println("Have a wonderful day!");
			mainLoop = false;
		}
		else
		{
			System.out.println("Please enter a valid menu"
					+ " option.");
			System.out.println();
			menu();
		}
	}//resolveMenu()
	
	private static void clearHistory()
	{
		System.out.println("Would you like to clear your history?");
		System.out.println("(Y)es or (N)o");
		String response = console.nextLine();
		
		boolean validate = true;
		while(validate)
		{
			if(response.toLowerCase().equals("yes") || 
					response.toLowerCase().equals("y"))
			{
				validate = false;
				
				history.clearHistory();
				
				System.out.println();
				System.out.println("Your history has been cleared.");
				System.out.println();				
			}
			else if(response.toLowerCase().equals("no") || 
					response.toLowerCase().equals("n"))
			{
				validate = false;
				
				System.out.println();
			}
			else
			{
				System.out.println("Please enter a (Y)es or (N)o.");
				response = console.nextLine();
			}
		}//validate
	}//clearHistory()
}//BabyNamesDriver.java
