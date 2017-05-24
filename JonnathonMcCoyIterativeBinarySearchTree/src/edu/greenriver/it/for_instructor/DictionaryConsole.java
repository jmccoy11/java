/*
 * Jonnathon McCoy
 * May 13, 2017
 * 
 * package: edu.greenriver.it.iterativebst
 * class: DictionaryConsol.java
 * 
 * An interface for the user to load the dictionary, search for a term,
 * and if a term doesn't exist, for them to add it to the dictionary.
 */
package edu.greenriver.it.for_instructor;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import edu.greenriver.it.dictionary.Dictionary;

/**
 * An interface for the user to load the dictionary, search for a term,
 * and if a term doesn't exist, for them to add it to the dictionary.
 * 
 * @author Jonnathon McCoy
 * @version 1.0
 */
public class DictionaryConsole 
{
	private static Scanner console = new Scanner(System.in);
	private static Dictionary dictionary = new Dictionary();
	
	/**
	 * Entry point for the program.
	 * 
	 * @param args String[] - command-line arguments
	 */
	public static void main(String[] args) 
	{
		System.out.println("*** WELCOME TO JONNATHON MCCOY'S DICTIONARY ***");
		
		menu();
	} //main()
	
	/*
	 * Selection menu
	 */
	private static void menu()
	{
		System.out.println();
		System.out.println("PLEASE CHOOSE ONE OF THE FOLLOWING OPTIONS");
		System.out.println();
		System.out.println(
				  "1. Load Dictionary\n"
				+ "2. Lookup word\n"
				+ "3. Exit\n" );
		String userInput = console.nextLine();
		choice(userInput);
	} //menu()
	
	/*
	 * Validation and controller
	 */
	private static void choice(String userInput)
	{
		try
		{
			int menuOption = Integer.parseInt(userInput);
			
			if(menuOption < 1 || menuOption > 3)
			{
				throw new InputMismatchException();
			}
			
			switch (menuOption)
			{
				case 1:	loadDictionary();
						break;
				case 2: lookup();
						break;
				case 3:	exit();
						break;
				default: throw new NumberFormatException();
			}
		}
		catch(NumberFormatException exc)
		{
			System.out.println("Please choose one of the three options.");
			menu();
		}
		catch(Exception exc)
		{
			System.out.println("Something went wrong! " + exc.getMessage());
		}
	} //choice()
	
	/*
	 * Loads the dictionary
	 */
	public static void loadDictionary() throws FileNotFoundException
	{
		ArrayList<String> words = new ArrayList<>();
		ArrayList<String> definitions = new ArrayList<>();
		
		//Scan all the words and definitions into an ArrayList
		Scanner in = null;
		
		try 
		{
			in = new Scanner(new File("dictionary.txt"));
			
			while (in.hasNextLine())
			{
				String next = in.nextLine();
				String[] data = next.split(": ");
				
				words.add(data[0]);
				definitions.add(data[1]);
			}
			
			dictionary = new Dictionary(words.toArray(new String[0]),
					definitions.toArray(new String[0]));
		} 
		catch (FileNotFoundException exc) 
		{
			System.out.println("File not found! " + exc);
		}
		finally
		{
			in.close();
		}
		
		System.out.println("Dictionary loaded.");
		menu();
	} //loadDictionary()
	
	/*
	 * Asks for a term to search for and returns the definition to the
	 * console. If the term doesn't exist, it asks for a definition and will
	 * add the word/definition pair to the Dictionary.
	 */
	private static void lookup()
	{
		if(dictionary.isEmpty())
		{
			System.out.println("The dictionary hasn't been loaded yet. Please choose option 1 first.");
			menu();
		}
		
		System.out.print("Please enter a word: ");
		String word = console.nextLine().toLowerCase();
		
		String found = dictionary.define(word);
		
		if(found != null)
		{
			System.out.println("Definition: " + found);
			menu();
		}
		else
		{
			System.out.print("Please enter a definition for \"" + word + "\": ");
			String definition = console.nextLine();
			
			dictionary.updateDictionary(word, definition);
			
			menu();
		}
	} //lookup()
	
	/*
	 * Display a goodbye message and exit.
	 */
	private static void exit()
	{
		console.close();
		System.out.println("Thank you for viewing my dictionary.");
		System.exit(0);
	} //exit()
} //DictionaryConsole.java
