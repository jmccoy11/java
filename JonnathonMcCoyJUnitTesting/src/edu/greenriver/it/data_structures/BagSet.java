package edu.greenriver.it.data_structures;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * This class represents a bag of elements which you can
 * add elements to, remove elements from, but is fixed size
 * in capacity.
 * 
 * This version of the bag also maintains that all elements
 * are unique at all times.
 * 
 * @author Josh Archer
 * @version 1.0
 */
public class BagSet<T> implements Iterable<T>
{
	private Object[] data;
	private int size;
	
	/**
	 * Creates a new BagSet with enough room to store the given capacity
	 * of elements.
	 * 
	 * @param capacity the space available in the bag
	 * @throws IllegalArgumentException this is thrown when the capacity
	 * given is negative or zero
	 */
	public BagSet(int capacity)
	{
		//precondition
		if (capacity <= 0)
		{
			throw new IllegalArgumentException("A BagSet must have a"
					+ "positive capacity");
		}
		
		data = new Object[capacity];
	}
	
	/**
	 * Adds a new element to the BagSet given that there is room within the capacity
	 * of the bag and also that the element is not already present in the structure.
	 * 
	 * @param element the new element to add to my BagSet
	 * @return true if the element is added to the BagSet, otherwise false
	 */
	public boolean add(T element)
	{	
		//did we exceed our bounds?
		if (size == data.length)
		{
			return false;
		}
		
		//make sure we have no duplicates
		if(contains(element))
		{
			return false;
		}
		
		//find a spot for the new element
		for (int i = 0; i < data.length; i++)
		{
			if (data[i] == null)
			{
				data[i] = element;
				size++;
				return true;
			}
		}
		
		//this should not be possible given other constraints
		return true;
	}
	
	/**
	 * Returns true if the given element is in the BagSet, otherwise false.
	 * 
	 * @param element the element to search for
	 * @return true if the element is found, otherwise false
	 */
	public boolean contains(T element)
	{
		//search for the element
		for (int i = 0; i < data.length; i++)
		{
			//data[i] can be null
			if (data[i] != null && data[i].equals(element))
			{
				return true; //found!
			}
		}
		
		return false; //not found!
	}
	
	/**
	 * Searches for and removes the given element from the BagSet
	 * if it is found.
	 * 
	 * @param element the element to remove
	 * @return true if the element is found, otherwise false
	 */
	public boolean remove(T element)
	{
		//search for the element
		for (int i = 0; i < data.length; i++)
		{
			if (data[i] != null && data[i].equals(element))
			{
				data[i] = null;
				size--;
				return true; //found!
			}
		}
		
		return false; //not found!
	}
	
	/**
	 * Returns the number of elements in the BagSet.
	 * 
	 * @return an element count
	 */
	public int size()
	{
		return size;
	}
	
	/**
	 * Returns a new iterator object over the BagSet.
	 */
	public Iterator<T> iterator()
	{
		return new BagSetIterator(data);
	}
	
	//an internal iterator class
	private class BagSetIterator implements Iterator<T>
	{
		private Object[] data;
		private int current = 0;
		
		public BagSetIterator(Object[] data)
		{
			this.data = data;
		}

		@Override
		public boolean hasNext()
		{
			//save our index
			int checkIndex = current;
			
			//find the next non-null element
			while (checkIndex < data.length)
			{
				if (data[checkIndex] != null)
				{
					return true;
				}
				else
				{
					checkIndex++;
				}
			}
			
			//none found!
			return false;
		}

		@SuppressWarnings("unchecked")
		@Override
		public T next()
		{
			//save our index
			int checkIndex = current;

			//find the next non-null element
			while (checkIndex < data.length)
			{
				if (data[checkIndex] != null)
				{
					//move the iterator to the next position and return our current value
					current = checkIndex + 1;
					return (T)data[checkIndex];
				}
				else
				{
					checkIndex++;
				}
			}
			
			//none found!
			throw new NoSuchElementException("Element not found!");
		}
	}
}











