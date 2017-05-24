package edu.greenriver.it.iterators;

import java.util.Arrays;
import java.util.ConcurrentModificationException;
import java.util.Iterator;

//stores a fixed size array, but elements can be added
//at will without specifying an index
public class Bag implements Iterable<Object>
{
	//fields
	private static final int DEFAULT_BAG_SIZE = 10;
	private Object[] data;
	private int currentNumberOfElements = 0;
	
	//modification count variable: increment every time there is a change
	//(add, remove, insert)
	private int modCount = 0;
	
	//constructors
	public Bag()
	{
		data = new Object[DEFAULT_BAG_SIZE];
	}
	
	public Bag(int sizeOfBag)
	{
		data = new Object[sizeOfBag];
	}
	
	//methods
	public boolean add(Object newElement)
	{
		//search for a spot to place our new element
		for(int i = 0; i < data.length; i++)
		{
			//is the current spot == null
			if(data[i] == null)
			{
				//add the element and exit the method
				data[i] = newElement;
				currentNumberOfElements++;
				modCount++;
				return true;
			}
		}
		
		//the bag is full!!!
		return false;
	}
	
	public int size()
	{
		return currentNumberOfElements;
	}
	
	public String toString()
	{
		return Arrays.toString(data);
	}

	//this method declares that our class has an iterator,
	//and it returns an iterator
	@Override
	public Iterator<Object> iterator() 
	{
		return new BagIterator(data, this);
	}
	
	//inner classes...
	private static class BagIterator implements Iterator<Object>
	{
		private Object[] outerClassData;
		private int currentPosition = 0;
		private Bag parentBag;
		private int currentModCount;
		
		public BagIterator(Object[] outerClassData, Bag parentBag)
		{
			this.outerClassData = outerClassData;
			this.parentBag = parentBag;
			
			//save the current state of the bag
			currentModCount = parentBag.modCount;
		}
		
		@Override
		public boolean hasNext() 
		{
			//ensure the data hasn't changed while we are iterating
			if(parentBag.modCount != currentModCount)
			{
				throw new ConcurrentModificationException
				("You cannot change a bag while iterating over it");
			}
			
			//make sure we still have a valid index and the current 
			//element is not empty (null)
			return currentPosition < outerClassData.length && 
					outerClassData[currentPosition] != null;
		}

		@Override
		public Object next() 
		{
			//ensure the data hasn't changed while we are iterating
			if(parentBag.modCount != currentModCount)
			{
				throw new ConcurrentModificationException
				("You cannot change a bag while iterating over it");
			}
			
			//return the current element, and we need to increment
			//currentPosition so that it points to the next element
			Object nextElement = outerClassData[currentPosition];
			currentPosition++;
			
			return nextElement;
		}
	}
}
