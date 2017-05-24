/*
 * Jonnathon McCoy
 * May 13, 2017
 * 
 * package: edu.greenriver.it.iterativebst
 * class: DictionaryTests.java
 * 
 * JUnit tests for the Dictionary Class.
 */

package edu.greenriver.it.for_instructor;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import edu.greenriver.it.dictionary.Dictionary;

/**
 * JUnit tests for the Dictionary Class.
 * 
 * @author Jonnathon McCoy
 * @version 1.0
 */
public class DictionaryTests 
{
	private Dictionary dictionary = new Dictionary();
	private final int DICTIONARY_SIZE = 86035;
	
	/**
	 * Sets up the Dictionary. Scans the words and definitions from
	 * dictionary.txt into ArrayLists, makes them into standard arrays
	 * while putting it into the constructor for the Dictionary class.
	 */
	@Before
	public void setup()
	{
		ArrayList<String> words = new ArrayList<>();
		ArrayList<String> definitions = new ArrayList<>();
		
		//Scan all the words and definitions into an ArrayList
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
			String[] data = next.split(": ");
			
			words.add(data[0]);
			definitions.add(data[1]);
		}
		
		dictionary = new Dictionary(words.toArray(new String[0]), 
				definitions.toArray(new String[0]));
	} //setup()
	
	/**
	 * Tests the Dictionary constructor.
	 * 
	 * The constructor should not be able to accept arrays of different
	 * sizes. If it fails, an IllegalArgumentException should be thrown.
	 * 
	 * If the arrays unsuccessfully sort, an IllegalStateException should be
	 * thrown. This depends on how the compareTo() method is written for the
	 * item using the Comparable interface. I am actually unsure how to test
	 * this as my sort will sort lexigraphically using the String class
	 * compareTo for the key in the BSTSymbolTable.
	 */
	@Test
	public void testConstructor()
	{
		try
		{
			ArrayList<String> sizeMismatchArray = new ArrayList<String>();
			sizeMismatchArray.add("Word");
			
			ArrayList<String> sizeMismatchArray2 = new ArrayList<>();
			sizeMismatchArray2.add("Another Word");
			sizeMismatchArray2.add("Yet another word");
			
			Dictionary mismatchDictionary = new Dictionary(
					sizeMismatchArray.toArray(new String[0]), 
					sizeMismatchArray2.toArray(new String[0]));
			
			//should not be able to get to this line
			Assert.fail("An IllegalArgumentException was not thrown when "
					+ "creating a Dictionary with mismatching array sizes "
					+ "for words and definitions.");
			
			mismatchDictionary.clear();
		}
		catch (IllegalArgumentException exc)
		{
			//We're supposed to get here.
		}
	} //testConstructor()
	
	/**
	 * Tests the add/update method for the Dictionary.
	 */
	@Test
	public void testUpdateDictionary()
	{
		int addCount = 0;
		
		//verify an update is successful
		Assert.assertTrue("updateDictionary() should return true if an element was successfully added",
				dictionary.updateDictionary("Jonnathon", "A handsome devil!"));
		addCount++;
		
		//verify size() increases
		Assert.assertEquals("The size() of the dictionary should be 86,036", DICTIONARY_SIZE+addCount, dictionary.size());
		
		//verify that define returns the proper value
		Assert.assertEquals("updateDictionary() should return A handsome devil! when searching for Jonnathon",
				"A handsome devil!", dictionary.define("Jonnathon"));
		
		//verify that an element cannot be added if it already exists in the dictionary
		Assert.assertFalse("updateDictionary() should return false if an element already exists in the Dictionary",
				dictionary.updateDictionary("Jonnathon", "A handsome devil!"));
		
		//verify that size does not increase after updating the Dictionary
		Assert.assertEquals("The size() of the dictionary should be 86,036", DICTIONARY_SIZE+addCount, dictionary.size());
		
		//verify that updateDictionary will update the definition
		dictionary.updateDictionary("Jonnathon", "A cool guy.");
		Assert.assertEquals("updateDictionary() should return 'A cool guy.' instead of the original 'A handsome devil!'",
				"A cool guy.", dictionary.define("Jonnathon"));
	} //testDictionary()
	
	/**
	 * Test the search function of the Dictionary
	 */
	@Test
	public void testHasWord()
	{	
		Dictionary newDictionary = new Dictionary();
		
		//test that a search on an empty dictionary will return false
		Assert.assertFalse("A search on an empty Dictionary should return false", 
				newDictionary.hasWord("empty"));
		
		//test that the Dictionary has a word
		Assert.assertTrue("hasWord() should return true if the element is found in the Dictionary", 
				dictionary.hasWord("kinesodic"));
		
		//test that the Dictionary does not contain a word
		Assert.assertFalse("hasWord() should return false if the element is found in the Dictionary", 
				dictionary.hasWord("supercalifragilisticexpialidocious"));
	} //testHasWord()
	
	/**
	 * Test the value return of the Dictionary
	 */
	@Test
	public void testDefine()
	{
		//returns the definition if it exists in the Dictionary
		Assert.assertEquals("define() should return the value/definition of the key/word if the element"
				+ "exists in the dictionary.", "A humming bird.", dictionary.define("hummer"));
		
		//returns null if the definition does not exist in the Dictionary
		Assert.assertEquals("define() should return null if the element does not exist in the "
				+ "dictionary", null, dictionary.define("hephalump"));
	} //testDefine()
	
	/**
	 * Test the List of keys generation from Dictionary
	 */
	@Test
	public void testWords()
	{
		Dictionary newDictionary = new Dictionary();
		
		//test that a search on an empty dictionary will return an empty list
		Assert.assertTrue("words() on an empty dictionary should return empty", newDictionary.words().isEmpty());
		
		//test that a words() kicks back a List
		Assert.assertFalse("words() should return a List that has elements", dictionary.words().isEmpty());
		
		//words() should return true on a hit on a key that exists in the dictionary
		List<String> wordsList = dictionary.words();
		Assert.assertTrue("should return true if words() contains 'hummer' in the list", 
				wordsList.contains("hummer"));
		
		//words() should return false on a key that doesn't exist in the dictionary
		Assert.assertFalse("should return false if words() does not contain 'hephalump' in the list",
				wordsList.contains("hephalump"));
	} //testWords()
	
	/**
	 * Test the List of definitions from Dictionary
	 */
	@Test
	public void testDefinitions()
	{
		Dictionary newDictionary = new Dictionary();
		
		//test that a search on an empty dictionary will return an empty list
		Assert.assertTrue("definitions() on an empty dictionary should return empty", newDictionary.definitions().isEmpty());
		
		//test that a words() kicks back a List
		Assert.assertFalse("definitions() should return a List that has elements", dictionary.definitions().isEmpty());
		
		//definitions() should return true on a hit on a key that exists in the dictionary
		Assert.assertTrue("should return true if words() contains 'hummer' in the list", 
				dictionary.definitions().contains("A humming bird."));
		
		//words() should return false on a key that doesn't exist in the dictionary
		Assert.assertFalse("should return false if words() does not contain 'hephalump' in the list",
				dictionary.definitions().contains("Pharfenugen"));
	} //testDefinitions()
	
	/**
	 * Test that the size increases with additions to the dictionary only
	 * and will remain the same if definitions are updated.
	 */
	@Test
	public void testSize()
	{
		Dictionary newDictionary = new Dictionary();
		int addCount = 0;
		
		//verify size is zero for an empty dictionary
		Assert.assertEquals("The size() of an empty dictionary should be zero", 0, newDictionary.size());
		
		//verify size is 86,035
		Assert.assertEquals("The size() of the dictionary should be 86,035", DICTIONARY_SIZE, dictionary.size());
		
		//verify size increases to 86,036 after adding an element
		dictionary.updateDictionary("hephalump", "Mythical creature that lives in the Hundred Acre Woods.");
		addCount++;
		Assert.assertEquals("The size() of the dictionary should be 86,036", DICTIONARY_SIZE+addCount, dictionary.size());
		
		//verify size increases to 86,037 after adding an element
		dictionary.updateDictionary("Jonnathon", "A handsome devil!");
		addCount++;
		Assert.assertEquals("The size() of the dictionary should be 86,037", DICTIONARY_SIZE+addCount, dictionary.size());
	} //testSize()
	
	/**
	 * Test the clear function of the Dictionary.
	 */
	@Test
	public void testClear()
	{
		//test clear
		dictionary.clear();
		Assert.assertEquals("The size() of the dictionary should be zero", 0, dictionary.size());
		
		//test is empty
		Assert.assertTrue("isEmpty() should be true if the dictionary is now empty", dictionary.isEmpty());
	} //clear()
} //DictionaryTest.java
