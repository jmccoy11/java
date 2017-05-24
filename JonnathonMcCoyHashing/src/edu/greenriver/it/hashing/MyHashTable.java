package edu.greenriver.it.hashing;

import java.util.Collection;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Set;

public class MyHashTable<T> implements Set<T>
{
	//constants that control initial table size, load factor
	private static final int DEFAULT_SIZE = 10;
	private static final double DEFAULT_LOAD_FACTOR = 0.6;	
	
	private HashTableElement[] table;
	
	private double loadFactor;
	private int initialSize;
	
	private int size; //how many elements are in our table?
	private int usedSpace; //how spots are used in our table?
	private int modCount = 0; 
	
	//constructors
	public MyHashTable()
	{
		//uses the defaults above
		this(DEFAULT_SIZE, DEFAULT_LOAD_FACTOR); //constructor chaining)
	}
	
	public MyHashTable(int initialSize, double loadFactor)
	{
		//preconditions? 0.0 <= loadFactor <= 1.0, initialSize > 0
		table = new HashTableElement[initialSize];
		
		this.loadFactor = loadFactor;
		this.initialSize = initialSize;
	}
	
	@Override
	public boolean add(T element) 
	{
		//is there always room to add a new element?
		if ((double)usedSpace / table.length >= loadFactor)
		{
			rehash();
		}
		
		//we know now that we have space for a new element
		int index = Math.abs(element.hashCode()) % table.length;
		
		//search for a spot to place my new element using my index
		HashTableElement current = table[index];
		
		//search for an empty spot (null value)
		while (current != null)
		{
			//collision! we need to use linear probing to search for an empty spot
			
			//the element may already be in the table (no duplicates)
			if (current.element.equals(element) && !current.isEmpty)
			{
				return false; //--duplicate!!
			}
			
			//we may search off the end our table
			index = (index + 1) % table.length;
			current = table[index];
		}
		
		table[index] = new HashTableElement(element, false);
		size++;
		usedSpace++;
		modCount++;
		return true;
	}
	
	@SuppressWarnings("unchecked")
	private void rehash()
	{
		//resize our table and re-hash (place) our elements in a new table
		HashTableElement[] oldTable = table;
		table = new HashTableElement[oldTable.length * 2];
		size = 0;
		
		for (int i = 0; i < oldTable.length; i++)
		{
			if (oldTable[i] != null && !oldTable[i].isEmpty)
			{
				add((T)oldTable[i].element); //place elements in the new table based on their hashcode
				
			}
		}
	}
	
	@Override
	public boolean remove(Object element) 
	{
		int index = Math.abs(element.hashCode()) % table.length;
		
		HashTableElement current = table[index];
		
		//search for an empty spot (null value)
		while (current != null)
		{
			//is this the element to remove?
			if (current.element.equals(element) && !current.isEmpty)
			{
				current.isEmpty = true; //lazy deletion
				size--;
				modCount++;
				return true;
			}
			
			//we may search off the end our table
			index = (index + 1) % table.length;
			current = table[index];
		}
		
		return false;
	}

	@Override
	public boolean contains(Object element) 
	{
int index = Math.abs(element.hashCode()) % table.length;
		
		HashTableElement current = table[index];
		
		//search for an empty spot (null value)
		while (current != null)
		{
			//is this the element to remove?
			if (current.element.equals(element) && !current.isEmpty)
			{
				return true;
			}
			
			//we may search off the end our table
			index = (index + 1) % table.length;
			current = table[index];
		}
		
		return false;
	}

	@Override
	public int size() 
	{
		return size;
	}
	
	@Override
	public boolean isEmpty() 
	{
		return size == 0;
	}
	
	@Override
	public void clear() 
	{
		size = 0;
		table = new HashTableElement[initialSize];
		modCount++;
	}

	@Override
	public String toString()
	{
		String result = "";
		
		for (int i = 0; i < table.length; i++)
		{
			if (i != 0)
			{
				result += ", ";
			}
			
			if (table[i] != null)
			{
				result += table[i].toString();
			}
			else
			{
				result += "null";
			}
		}
		
		return result;
	}
	
	@Override
	public Iterator<T> iterator() 
	{
		return new HashTableIterator(table, modCount);
	}
	
	//we'll write these later...
	
	@Override
	public boolean addAll(Collection<? extends T> arg0) 
	{
		return false;
	}

	@Override
	public boolean containsAll(Collection<?> arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean removeAll(Collection<?> arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean retainAll(Collection<?> arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Object[] toArray() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> T[] toArray(T[] arg0) {
		// TODO Auto-generated method stub
		return null;
	}
	
	private class HashTableIterator implements Iterator<T>
	{
		private HashTableElement[] table;
		private int nextIndex = -1;
		private int modCountSnapshot;
		
		public HashTableIterator(HashTableElement[] table, int modCountSnapshot)
		{
			this.table = table;
			this.modCountSnapshot = modCountSnapshot;
			
			findNextIndex(); //find the first valid element
		}
		
		@Override
		public boolean hasNext() 
		{
			if (modCountSnapshot != MyHashTable.this.modCount)
			{
				throw new ConcurrentModificationException("Cannot change your hash "
						+ "table while using and Iterator");
			}
			
			return nextIndex != -1;
		}

		@SuppressWarnings("unchecked")
		@Override
		public T next() 
		{
			if(!hasNext())
			{
				throw new NoSuchElementException("There is no element to return!");
			}
			
			Object nextElement = table[nextIndex].element;
			findNextIndex(); //get the next valid element
			
			return (T)nextElement;
		}
		
		private void findNextIndex()
		{
			//this method will move nextIndex to the next valid element
			//valid = not null, not empty [deleted]
			for (int i = nextIndex + 1; i < table.length; i++)
			{
				if (table[i] != null && !table[i].isEmpty)
				{
					nextIndex = i;
					return; //exit now...
				}
			}
			
			nextIndex = -1;
		}
		
	}
	
	private static class HashTableElement
	{
		private Object element;
		private boolean isEmpty; //true, the element is deleted, otherwise false
		
		public HashTableElement(Object element, boolean isEmpty) 
		{
			this.element = element;
			this.isEmpty = isEmpty;
		}
		
		public String toString()
		{
			if (isEmpty)
			{
				return "empty";
			}
			
			return element.toString();
		}
	}
}
