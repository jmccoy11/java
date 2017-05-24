package edu.greenriver.it.tree_assignment;

/*
 * Jeremy Manalo
 * 5-4-17
 * BSTSymbolTable.java
 * Binary Search Tree Symbol Table
 */

import java.util.ArrayList;
import java.util.List;

/**
 * A Symbol Table that uses a BST to store its data
 * @author Jeremy Manalo
 * @version 1.0
 * @param <K> A generic key type
 * @param <V> A generic value type
 */

public class BSTSymbolTable<K extends Comparable<K>, V> 
{
	//fields
	private BinarySearchTree<Entry> data;
	
	//constructor
	/**
	 * Constructor that does nothing
	 */
	public BSTSymbolTable()
	{
		data = new BinarySearchTree<Entry>();
	}
	
	//methods
	/**
	 * puts an entry into the data tree
	 * @param key - for the entry
	 * @param value - for the entry
	 * @return true if successful
	 */
	public boolean put(K key, V value)
	{
		//null pointer
		Entry current = new Entry(key, value);
		return data.addUpdate(current);
	}
	
	/**
	 * Gets the value of an entry with the given key
	 * @param key to search for in the tree
	 * @return the value associated with the given key
	 */
	public V get(K key)
	{
		Entry current = new Entry(key, null);
		Entry temp = data.get(current);
		return temp.value;
	}
	
	/**
	 * checks if the given key exists in the data tree
	 * @param key to search for
	 * @return true if the tree contains the key, false otherwise
	 */
	public boolean containsKey(K key)
	{
		Entry current = new Entry(key, null);
		
		return data.contains(current);
	}
	
	/**
	 * checks if the given value exists in the data tree
	 * @param value to search for
	 * @return true if the tree contains the value, false otherwise
	 */
	public boolean containsValue(V value)
	{
		List<V> valuesInTree = values();
		
		for(V valueInList : valuesInTree)
		{
			if (valueInList.equals(value))
			{
				return true;
			}
		}
		
		return false;
	}
	
	/**
	 * Gets a list of the keys in the tree 
	 * @return List<K> list of keys in the tree
	 */
	public List<K> keys()
	{
		List<Entry> returned = data.inOrder();
		List<K> keys = new ArrayList<K>();
		
		for(Entry entry : returned)
		{
			keys.add(entry.key);
		}
		
		return keys;
	}
	
	/**
	 * Gets a list of the values in the tree 
	 * @return List<V> list of values in the tree
	 */
	public List<V> values()
	{
		List<Entry> returned = data.inOrder();
		List<V> values = new ArrayList<V>();
		
		for(Entry entry : returned)
		{
			values.add(entry.value);
		}
		
		return values;
	}
	
	/**
	 * Get the size of the tree
	 * @return number of entries in the tree
	 */
	public int size()
	{
		return data.size();
	}
	
	/**
	 * checks if the tree is empty
	 * @return true if the tree is empty, false otherwise
	 */
	public boolean isEmpty()
	{
		return data.isEmpty();
	}
	
	/**
	 * clears the tree
	 */
	public void clear()
	{
		data.clear();
	}
	
	//private
	private class Entry implements Comparable<Entry>
	{	
		//fields
		private K key;
		private V value;
		
		//constructor
		public Entry(K key, V value) 
		{
			this.key = key;
			this.value = value;
		}

		//compareTo override, compare with keys
		@Override
		public int compareTo(Entry other) 
		{
			return this.key.compareTo(other.key);
		}
		
		public String toString()
		{
			return "Key: " + key + ", Value: " + value;
		}
		
	}
}
