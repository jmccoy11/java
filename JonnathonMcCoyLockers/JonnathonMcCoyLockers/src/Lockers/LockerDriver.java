/*
 * Jonnathon McCoy
 * 05/25/2016
 * LockersDriver.java
 * 
 * Request a number of lockers from the user.  Create userInput amount
 * of lockers in an ArrayList with Locker Objects and systematically
 * go through each object and toggle the open/closed status of each
 * if the number is divisible by the current pass of the "for" loop.
 */

package Lockers;

import java.util.ArrayList;
import java.util.Scanner;


/**
 * Request a number of lockers from the user.  Create userInput amount
 * of lockers in an ArrayList with Locker Objects and systematically
 * go through each object and toggle the open/closed status of each
 * if the number is divisible by the current pass of the "for" loop.
 * 
 * @author Jonnathon McCoy
 * 
 * @version 1.0
 */
public class LockerDriver 
{
	private static final Scanner CONSOLE = new Scanner(System.in);
	
	private static ArrayList<Locker> school;
	private static boolean gameLoop = true;
	
	
	public static void main(String[] args) 
	{
		while(gameLoop)
		{
			greeting();
			
			String userInput = CONSOLE.nextLine();
			int value = verifyInput(userInput);
			
			createSchool(value);
			System.out.println();
			
			openDoors();
			
			playAgain();
		}
		
		CONSOLE.close();
	}//main()

	
	//verify the userInput is a positive Integer.
	private static int verifyInput(String userInput)
	{
		Integer value = 0;
		
		boolean verify = true;
		while(verify)
		{
			try
			{
				value = Integer.parseInt(userInput);
				if(value > 0)
				{
					verify = false;
				}
				else
				{
					throw new NumberFormatException();
				}
			}
			catch(NumberFormatException ex)
			{
				System.out.println("Please enter a positive Integer.");
				userInput = CONSOLE.nextLine();
				System.out.println();
			}
		}
		
		return value;
	}//verifyInput()
	
	
	//initialize the school ArrayList
	private static void createSchool(int value)
	{
		System.out.println("Creating " + value + " lockers!");
		System.out.println("Each of your lockers are numbered from 1 to " 
				+ value + ".");
		
		school = new ArrayList<Locker>(value);
		
		for(int i = 0; i < value; i++)
		{
			school.add(i, new Locker(i, false));
		}
	}//createSchool()
	
	
	//program greeting
	private static void greeting()
	{
		System.out.println("Welcome to the locker game!");
		System.out.println("A new high school has just been completed.");
		System.out.println("How many school lockers would you like to create?");
	}//greeting()
	
	
	/*
	 * Systematically go through each object and toggle the open/closed 
	 * status of each if the number is divisible by the current pass of 
	 * the "for" loop.
	 */
	private static void openDoors()
	{
		//per the assignment, students are changing the state of the lockers
		int students = school.size(); 
		int divisibleBy = 1; //essentially pass + 1, incremented at the end of loop
		
		
		for(int pass = 0; pass < students; pass++)
		{
			if(pass == students-1) //if last change
			{
				System.out.println("Final state of the lockers:");
			}
			else
			{
				System.out.println("Pass " + (pass + 1) + ":");
			}
			
			for(int pos = 0; pos < students; pos++)
			{
				//if lockerNum/divisibleBy has zero remainder
				if(school.get(pos).getLockerNum()%divisibleBy == 0)
				{
					//toggle the status of the locker
					school.get(pos).toggle();
				}
			}
			
			for(int i = 0; i < students; i++)
			{
				System.out.println(school.get(i).toString());
			}
			
			divisibleBy++;
			System.out.println();
		}
	}//openDoors()
	
	
	//request if user would like to use the program again
	private static void playAgain()
	{
		String userInput;
		
		boolean verify = true;
		while(verify)
		{
			System.out.println("Create new lockers? (Y) or (N)");
			userInput = CONSOLE.nextLine();
			
			if (userInput.equalsIgnoreCase("n"))
			{
				gameLoop = false;
				verify = false;
			}
			else if(userInput.equalsIgnoreCase("y"))
			{
				System.out.println();
				verify = false;
			}
		}
	}//playAgain()
}//LockerDriver.java
