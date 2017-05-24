/*
 * Jonnathon McCoy
 * May 5, 2017
 * 
 * package: edu.greenriver.it.iterativebst
 * class: BSTSymbolTable.java
 * 
 * A binary search tree symbol table that uses the iteration of IterativeBinarySearchTree.java
 * to traverse through the tree rather than recursion.
 */

package edu.greenriver.it.bstsymboltable;

import java.util.ArrayList;
import java.util.List;
//import java.util.Comparator;

import edu.greenriver.it.iterativebst.IterativeBinarySearchTree;

/**
 * A binary search tree symbol table that uses the iteration of IterativeBinarySearchTree.java
 * to traverse through the tree rather than recursion.
 * 
 * @author Jonnathon McCoy
 * @version 1.0
 *
 * @param <K> - the type of elements maintained by this data structure as keys
 * @param <V> - the type of elements maintained by this data structure as values
 */
public class BSTSymbolTable<K extends Comparable<K>, V> 
{
	private IterativeBinarySearchTree<Entry> data;
	
	/**
	 * Constructor for the BSTSymbol Table. Creates a new internal IterativeBinarySearchTree of
	 * type Entry.  Each Entry is a key/value pair that contains a K key and a V value.
	 */
	public BSTSymbolTable()
	{
		data = new IterativeBinarySearchTree<Entry>();
	} //BSTSymbolTable()
	
	
	/**
	 * Add a new key/value pair as an Entry to the IterativeBinarySearchTree
	 * 
	 * @param key K - the key for the Entry
	 * @param value V - the value for the Entry
	 * 
	 * @return - if a new value has been added to the structure, return true, else if an Entry
	 * was updated, return false
	 */
	public boolean put(K key, V value)
	{
		return data.addUpdate(new Entry(key, value));
	} //put()
	
	
	/**
	 * Return the value that corresponds with the key.
	 * 
	 * @param key K - the key associated with the value
	 * @return V - Return the value associated with the corresponding key
	 */
	public V get(K key)
	{
		Entry retrieve = data.get(new Entry(key, null));
		
		if(retrieve != null)
		{
			return retrieve.value;
		}
		
		return null;
	} //get()
	
	/**
	 * Returns whether the data structure contains a desired key
	 * 
	 * @param key - desired key to check if the structure contains
	 * @return boolean - return true if the data structure contains the key, otherwise return false
	 */
	public boolean containsKey(K key)
	{
		return data.contains(new Entry(key,null));
	} //containsKey()
	
	/**
	 * Returns whether the data structure contains a desired value
	 * 
	 * @param value - desired value to check if the structure contains
	 * @return boolean - return true if the data structure contains the value, otherwise return false
	 */
	public boolean containsValue(V value)
	{
		List<V> values = values();
		
		for(V element : values)
		{
			if(element.equals(value))
			{
				return true;
			}
		}
		
		return false;
	} //containsValue()
	
	/**
	 * Returns an ArrayList of type K that contains an ordered list of keys
	 * 
	 * @return List - returns a list that implements the List interface, in this case an ArrayList
	 * with all of the keys in an ordered manner.
	 */
	public List<K> keys()
	{
		List<K> keys = new ArrayList<K>();
		
		for(Entry element : data)
		{
			keys.add(element.key);
		}
		
		//keys.sort(new keyComparator());  //not necessarily needed since I know my BST iterator
										   //iterates through in an inOrder fashion.
		
		return keys;
	} //keys()
	
	/**
	 * Returns an ArrayList of type V that contains an unordered list of values
	 * 
	 * @return List - returns a list that implements the List interface, in this case an ArrayList
	 * with all of the values with no order.
	 */
	public List<V> values()
	{
		List<V> values = new ArrayList<V>();
		
		for(Entry element : data)
		{
			values.add(element.value);
		}
				
		return values;
	} //values()
	
	/**
	 * Getter for the size of the data structure.
	 * 
	 * @return int - size of the IterativeBinarySearchTree stored within this class
	 */
	public int size()
	{
		return data.size();
	} //size()
	
	/**
	 * If IterativeBinarySearchTree size is zero, the data structure is empty.
	 * 
	 * @return boolean - if size of the BST is zero, return true, otherwise return false
	 */
	public boolean isEmpty()
	{
		return data.size() == 0;
	} //isEmpty()
	
	/**
	 * Clear the Binary Search Tree by reassigning data to a new object.
	 */
	public void clear()
	{
		data = new IterativeBinarySearchTree<Entry>();
	} //clear()
	
	/**
	 * String representation of the BSTSymbolTable
	 * 
	 * @return String - the String representation of the IterativeBinarySearchTree
	 */
	public String toString()
	{
		return data.toString();
	} //toString()
	
	/*
	 * Create a new Entry as a key/value pair
	 */
	private class Entry implements Comparable<Entry>
	{
		private K key;
		private V value;
		
		/**
		 * Constructor for a new Entry key/value pair
		 * 
		 * @param key K - Comparable key
		 * @param value V - Value
		 */
		public Entry(K key, V value)
		{
			this.key = key;
			this.value = value;
		} //Entry()
		
		/**
		 * String representation of a key/value pair Entry
		 * 
		 * @return String - key/value pair
		 */
		public String toString()
		{
			return "|Key: " + key + ", Value: " + value + "|";
		} //toString()
		
		/**
		 * Compare one key to another key and return whether the comparison is
		 * positive, negative, or zero.
		 * 
		 * @return int - If key < other.key return negative number, 
		 * else if key > other.key return positive number, 
		 * else key == other.key, return 0
		 */
		@Override
		public int compareTo(BSTSymbolTable<K, V>.Entry other) 
		{
			return this.key.compareTo(other.key);
		} //compareTo()
	}
	
	/*
	 * Since my BST iterates through in an inOrder fashion, a Comparator object is not
	 * needed to use the List.sort() method.
	 * 
	private class keyComparator implements Comparator<K>
	{
		@Override
		public int compare(K key1, K key2) 
		{
			if (key1.compareTo(key2) < 0)
			{
				return -1;
			}
			else if (key1.compareTo(key2) > 0)
			{
				return 1;
			}
			
			return 0;
		}
	}
	*/
}
