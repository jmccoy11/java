/*
 * Jonnathon McCoy
 * May 13, 2017
 * 
 * package: edu.greenriver.it.iterativebst
 * class: Dictionary.java
 * 
 * A dictionary with word/definition pairs based on a balanced binary search tree.
 */

package edu.greenriver.it.dictionary;

import java.util.List;
import java.util.NoSuchElementException;

import edu.greenriver.it.bstsymboltable.BSTSymbolTable;

/**
 * A dictionary with word/definition pairs based on a balanced binary search tree.
 * 
 * @author Jonnathon McCoy
 * @version 1.0
 */
public class Dictionary 
{
	private BSTSymbolTable<String, String> words;
	private int size;
	private final int HIBBARD_INCREMENTS = 2;
	
	/**
	 * Create a new empty dictionary.
	 */
	public Dictionary()
	{
		this.words = new BSTSymbolTable<String, String>();
		size = 0;
	}
	
	/**
	 * Create a new dictionary with given word/definition pairs as input.
	 * 
	 * @param words String[] - Array of words
	 * @param definitions String[] - Array of corresponding definitions for words[]
	 * @throws IllegalArgumentException - if the size of both the input arrays differ
	 * @throws IllegalStateException - if the elements of the words array are not in sorted order
	 */
	public Dictionary(String[] words, String[] definitions) 
			throws IllegalArgumentException, IllegalStateException
	{
		//check if the arrays are the same length
		//if not, throw an IllegalArgumentException
		if(words.length != definitions.length)
		{
			throw new IllegalArgumentException("The size of both input arrays differ.");
		}
		
		this.words = new BSTSymbolTable<String, String>();
		
		sort(words, definitions);
		
		//if the sort was unsuccessful for whatever reason, throw an an
		//IllegalStateException
		if(!add(words, definitions, 0, words.length-1))
		{
			throw new IllegalStateException("The arrays are not sorted");
		}
	}
	
	/*
	 * This method will add the middle element to the internal Binary Search Tree Symbol Table
	 * which in turn stores a Binary Search Tree.  If the words are not sorted properly, the
	 * method will return false which will cause the calling constructor to throw an
	 * IllegalStateException.
	 */
	private boolean add(String[] words, String[] definitions, int low, int high)
	{
		//find mid
		int mid = low + (high-low)/2;
		
		//if a check to mid+1 won't give an IndexOutOfBoundsException and 
		//if the items are not in sorted order, return false;
		if(mid+1 < words.length-1 && words[mid].compareTo(words[mid+1]) > 0)
		{
			return false;
		}
		
		//insert to BST
		updateDictionary(words[mid], definitions[mid]);

		//base case
		if(high - low > 0)
		{
			if(!add(words, definitions, low, mid))
			{
				return false;
			}
			if(!add(words, definitions, mid+1, high))
			{
				return false;
			}
		}
		
		return true;
	}
	
	/**
	 * Adds word/definition pairs to the dictionary if the input word is not presently in the dictionary.
	 * Otherwise, the method will update the definition for an already existing word.
	 * 
	 * @param word String - new word or word to be updated
	 * @param definition String - corresponding definition for the word
	 * @return boolean - if a new word was added, return true, else return false
	 */
	public boolean updateDictionary(String word, String definition)
	{
		boolean addUpdate = words.put(word, definition);
		
		if(addUpdate) //if a new word was added
		{
			size++;
		}
		
		return addUpdate;
	}
	
	/**
	 * Return true if the input word is in the dictionary, otherwise return false.
	 * 
	 * @param word String - input word to be found
	 * @return boolean - return true if the input word is in the dictionary, otherwise return false
	 */
	public boolean hasWord(String word)
	{
		return words.containsKey(word);
	}
	
	/** 
	 * Returns the definition of the input word from the dictionary.
	 * 
	 * @param word String - input word to be defined
	 * @return - return the definition of the input word
	 * @throws NoSuchElementException - if the word is not found in the dictionary
	 */
	public String define(String word) throws NoSuchElementException
	{
		return words.get(word);
	}
	
	/**
	 * Return an ordered list of words from the Dictionary.
	 * 
	 * @return List - an ordered list of words from the dictionary
	 */
	public List<String> words()
	{
		return words.keys();
	}
	
	/**
	 * Return an unordered list of words from the Dictionary.
	 * 
	 * @return List - an unordered list of definitions from the dictionary
	 */
	public List<String> definitions()
	{
		return words.values();
	}
	
	/**
	 * Getter for the size of the dictionary.
	 * 
	 * @return int - size of the dictionary
	 */
	public int size()
	{
		return size;
	}
	
	/**
	 * Checks if the dictionary is empty.
	 * 
	 * @return boolean - return true if the size is == 0, otherwise return false
	 */
	public boolean isEmpty()
	{
		return size == 0;
	}
	
	/**
	 * Clears the dictionary.
	 */
	public void clear()
	{
		size = 0;
		words = new BSTSymbolTable<String, String>();
	}
	
	/**
	 * String representation of the Dictionary.
	 * 
	 * @return String - String representation of the Dictionary
	 */
	public String toString()
	{
		return "Dictionary: Current size = " + size();
	}
	
	/**
	 * Method used to start the recursive quickSort() method to sort the words lexigraphically
	 * and swap the definitions to correspond with where the words might move to.
	 * 
	 * @param words String - words, or keys, to sort
	 * @param definitions String - corresponding definitions, or values, to move with the keys
	 */
	public void sort(String[] words, String[] definitions)
	{
		int length = words.length-1;
		int gapFactor = HIBBARD_INCREMENTS;
		int gap = 1;
		
		//find the gap
		while (gap < length)
		{
			if (gap * gapFactor + 1 > length)
			{
				break;
			}
			
			gap = gap * gapFactor + 1;
		}
		
		//each iteration of this while loop reduces the gap by
		//(gap-1)/3 effectively devolving into an insertion sort
		while(gap > 0)
		{
			//starting at index 0 iterate through the array per
			//each gap increasing j to be greater than i by the gap
			for(int i = 0; i < length; i++)
			{
				int j = i + gap;
				
				//if j has not exceeded the length of the array
				if(j <= length)
				{
					//compare word at i and words at j
					if(words[i].compareTo(words[j]) > 0)
					{
						//if j is less than i, swap the two
						swap(words, definitions, i, j);
						
						//go backwards through the array and find the
						//next element that is greater than the element
						//swapped into j.
						for(int k = j-1; k > i; k--)
						{
							if(words[k].compareTo(words[j]) > 0)
							{
								swap(words, definitions, k, j);
								k=i; //this ends the for loop because k 
								     //is supposed to be > i
							}
						}
					}
				}
			}
			
			//decrement the gap by gap-1 divided by the gapFactor
			gap = (gap-1)/gapFactor;
		}
	}
	
	/*
	 * My quickSort was actually blowing out the stack but I didn't want to get
	 * rid of this code
	 * 
	private void quickSort(String[] words, String[] definitions, int low, int high)
	{
		if(high-low <= 0)
		{
			return;
		}
		
		int partitionIndex = partition(words, definitions, low, high);
		quickSort(words, definitions, low, partitionIndex);
		quickSort(words, definitions, partitionIndex+1, high);
	}
	
	private int partition(String[] words, String[] definitions, int low, int high)
	{
		int pivotIndex = low;  //make the pivot start from the left most index given
		int i = low;
		int j = high;
		
		//base case
		if (j - i == 0)
		{
			return j;
		}
		
		while(i < j)
		{
			i++;
			
			//increase i until the object at i is greater than the pivot
			while(i < high && words[i].compareTo(words[pivotIndex]) < 0 )
			{
				i++;
			}
			
			j--;
			
			//decrease j until the object at j is less than the pivot
			while(j >= pivotIndex && words[j].compareTo(words[pivotIndex]) > 0)
			{
				j--;
			}
			
			if(j < i) //if j is less than i, swap the object at the partitionIndex with j
			{
				//swap the two positions
				swap(words, definitions, pivotIndex, j);
			}
			else //(i < j) else swap i with j
			{
				//swap the two positions
				swap(words, definitions, i, j);
			}
		}
		
		//return the right index to act as the variable 'high' for each
		//subsequent recursive call to partition()
		return j;
	}
	*/
	
	/*
	 * Swap the elements at index1 and index2 of both the words array and the
	 * definitions array
	 */
	private void swap(String[] words, String[] definitions, int index1, int index2)
	{
		//temp variables
		String wordsTemp = words[index1];
		String definitionsTemp = definitions[index1];
		
		//make index1 of words and definitions equal to the items at index2
		words[index1] = words[index2];
		definitions[index1] = definitions[index2];
		
		//replace the items at index2 with the temp variables
		words[index2] = wordsTemp;
		definitions[index2] = definitionsTemp;
	}
}
