/*
 * Jonnathon McCoy
 * May 13, 2017
 * 
 * package: edu.greenriver.it.iterativebst
 * class: DictionaryInformalTesting.java
 *
 * Informal testing for the Dictionary class
 */
package edu.greenriver.it.dictionary;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Informal testing for the Dictionary class
 * 
 * @author Jonnathon McCoy
 * @version 1.0
 */
public class DictionaryInformalTesting 
{
	/**
	 * Entry point for informal testing program
	 * 
	 * @param args String[] - Command line arguments
	 */
	public static void main(String[] args) 
	{
		Dictionary dict = new Dictionary();
		
		String[] names = {"Jonnathon", "Brittany", "Teri", "Dane", "Jessica", "Shanah", "Diana", "Kelly", "Dan",
				"Laura"};
		String[] relations = {"Me", "Wife", "Mother", "Son", "Sister-in-Law", "Sister-in-Law", "Mother-in-Law", "Father",
				"Father-in-Law", "Sister"};
		
		dict = new Dictionary(names, relations);
		
		System.out.println(dict.size());
		
		System.out.println(dict.define("Jonnathon"));
		
		System.out.println();
		
		dict.clear();
		System.out.println("size(): " + dict.size());
		
		//Scan all the words and definitions into an ArrayList
		ArrayList<String> dictWords = new ArrayList<>();
		ArrayList<String> dictDefinitions = new ArrayList<>();
		
		Scanner in = null;
		
		try 
		{
			in = new Scanner(new File("dictionary.txt"));
		} 
		catch (FileNotFoundException e) 
		{
			System.out.println("File not found! " + e);
		}
		
		while(in.hasNextLine())
		{
			String next = in.nextLine();
			String[] data = next.split(":");
			
			dictWords.add(data[0]);
			dictDefinitions.add(data[1]);
		}
		
		//change ArrayList to be a normal array
		String[] words = dictWords.toArray(new String[0]);
		String[] definitions = dictDefinitions.toArray(new String[0]);
		
		dict = new Dictionary(words, definitions);
		
		System.out.println();
		System.out.println("size(): " + dict.size());
	} //main()
} //DictionaryInformalTesting.java
