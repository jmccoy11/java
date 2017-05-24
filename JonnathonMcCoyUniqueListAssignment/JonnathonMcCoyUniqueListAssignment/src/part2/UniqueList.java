/* 
 * Jonnathon McCoy
 * April 18, 2017
 * 
 * Package: part2
 * Class: UniqueList.java
 * 
 * An implementation of the LinkedList class.
 */

package part2;

import java.util.Collection;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 * An implementation of the LinkedList class.
 * 
 * @author Jonnathon McCoy
 * @version 2.0
 * @param <T> Generic for Class-type
 */
public class UniqueList <T> implements List<T>, Iterable<T>
{
	//fields
	private Node head;  //beginning of list
	private Node tail;  //end of list
	private int size;  //current size of the list
	
	//modification counter:
	//Used with the Iterator to ensure that if you attempt to modify the data
	//  within the list while iterating through it. If the modCount doesn't match
	//  the modCount in the Iterator, it will throw a ConcurrentModificationException.
	private int modCount = 0;
	
	//constructors
	//default constructor
	
	//Inner Classes
	/*
	 * An implementation of the Node class to be used by a LinkedList. The Node class
	 *   stores the data of type <T> for the LinkedList and has a pointer to the next
	 *   element in the LinkedList.
	 */
	private class Node
	{
		//fields
		private Node next;
		private T element;
		
		//constructors
		/*
		 * Constructor for Node.
		 * 
		 * @param - element - data to be stored within the Node class
		 */
		private Node(T element)
		{
			this.element = element;
		} //Node() constructor
	} //Node
	
	/*
	 * The smart-object Iterator that knows how to traverse through the data structure.
	 */
	private class LLIterator implements Iterator<T>
	{
		//fields
		private Node currentNode;  
		private int currentPosition = 0;  //current position in the list, may not be needed
		private UniqueList<T> outerList;  //passes the current condition of the parent list
										  //  that called the Iterator
		private int currentModCount;  //used to check if a change has been made to the list
									  //  while iterating over the data. Will throw a 
		                              //  ConcurrentModificationException if the parent
									  //  modCount does not equal the currentModCount.
		
		private LLIterator(Node currentNode, UniqueList<T> outerList)
		{
			this.outerList = outerList;
			this.currentNode = currentNode;
			currentModCount = outerList.modCount;
		} //LLIterator()
		
		/**
		 * Checks to see if there is a next item in the LinkedList.
		 * 
		 * @return - boolean - If the current position within the LinkedList is less than the
		 * size and the current node is not equal to null, it will return true. Else, there is
		 * nothing left in the LinkedList.
		 */
		@Override
		public boolean hasNext() 
		{
			if(currentModCount != outerList.modCount)
			{
				throw new ConcurrentModificationException
					("You cannot modify the data in the list while iterating over it");
			}
			
			return currentPosition < size && currentNode != null;
		} //hasNext()

		/**
		 * Retrieves the element of the current Node and returns the value. Increments
		 * the position counter as well as moves the currentNode to the currentNode.next
		 * (next Node in the list).
		 * 
		 * @return - <T> - Returns the data stored in the Node.element variable.
		 */
		@Override
		public T next() 
		{
			if(currentModCount != outerList.modCount)
			{
				throw new ConcurrentModificationException
					("You cannot modify the data in the list while iterating over it");
			}
			
			T element = currentNode.element;
			currentNode = currentNode.next;
			currentPosition++;
			return element;
		} //next()
	} //LLIterator
	
	//Methods
	/**
	 * Method used to add new elements at the end of the Linked List.
	 * 
	 * @param - element - New object to be added to the Linked list.
	 * 
	 * @return - boolean - if an element is already in the list this method will return
	 * false after being unable to add to the list. Successful additions will return true.
	 */
	@Override
	public boolean add(T element)
	{
		if(isEmpty()) //if the LL is empty
		{
			head = new Node(element); //create the first element
			tail = head; //with only one element in the list, the head is also the tail
			size++;
			modCount++;
			return true;
		}
		else
		{	
			if(!contains(element)) //if the LL does not contain the element
			{
				tail.next = new Node(element); //create a new node
				tail = tail.next; //move the tail pointer to the new tail.next
				size++;
				modCount++;
				return true;
			}
		}
		
		//if neither of these two conditions happen, the add() failed
		return false;
	} //add()

	/**
	 * Clear all objects from the lists by removing the head of the list. The garbage
	 * 	collector will reclaim the memory of the non-root or non-live objects.
	 */
	@Override
	public void clear()
	{
	 	head = null;
	 	tail = null;
	 	size = 0;
	 	modCount++;
	} //clear()

	/**
	 * Method searches the Linked List to find whether the object currently
	 * resides in the list already.
	 * 
	 * @param - element - Object to be compared to each object stored
	 * in the Node's element variables.
	 * 
	 * @return - boolean - If the object is currently in the Linked List, this method
	 * will return true. Otherwise, it will return false.
	 */
	@Override
	public boolean contains(Object element)
	{
		//indexOf(element) returns -1 if the element is not in the list else it returns the
		//  index of the element in the list. So if, indexOf(element) is less than zero, 
		//  that means the list does not contain the element.
		if(indexOf(element) < 0)
		{
			return false;
		}
		
		return true;
	} //contains()

	/**
	 * Method checks to see if the list is currently empty by checking if size is
	 * 	equal to zero.
	 * 
	 * @return - boolean - If the Linked List is empty, this method will return true
	 * 	if the size is currently 0.
	 */
	@Override
	public boolean isEmpty()
	{
		return size == 0; //evaluate if size is equal to zero, true or false
	} //isEmpty()
	
	/**
	 * This method retrieves the data within a Node at a particular index within the
	 *  Linked List.
	 * 
	 * @param - int - The index of the desired object.
	 * 
	 * @return - T - Returns the Object of type T that is stored in the Node's
	 * 	element variable at index 'index'.
	 */
	@Override
	public T get(int index)
	{
		//check to see if the index is out of bounds
		if(index < 0 || index >= size)
		{
			throw new IndexOutOfBoundsException();
		}
		
		T data = null;
		
		if(!isEmpty()) //if the LL is not empty
		{
			Node current = head;
			
			for(int i = 0; i < index; i++) //if i=0, i cannot be less than zero so this code will not
										   //  run and move the current to current.next if index 0 is
										   //  the desired index.
			{
				current = current.next; //traverse through the LL until the desired Node is achieved
			}
			
			data = current.element; //return the data from the Node at index
		}
		
		return data;
	} //get()

	/**
	 * This object removes a Node from the Linked List that contains the target element.
	 * 
	 * @param - Object - The target element to be removed.
	 * 
	 * @return - boolean - if the Linked List does not contain the target element the method
	 *  will return false. Otherwise, if the element has been successfully removed and the
	 *  method will return true. For any other reason, the method will return false.
	 */
	@Override
	public boolean remove(Object element)
	{
		if(!contains(element))  //contains also checks isEmpty() and will return false as well
								// if the LL is empty.
		{
			return false;
		}
		else
		{
			Node current = head;
			
			if(current.element.equals(element)) //if element is in the head Node
			{
				head = head.next;
				size--;
				modCount++;
				return true;
			}
			
			for(int i = 0; i < size; i++) //check if element is any other node
			{
				//if there is a next Node and the next Node's element equals the desired element
				if(current.next != null && current.next.element.equals(element))
				{
					if(current.next.equals(tail)) //if the current.next is in fact the tail, move 
												  //  the tail to the current Node.
					{
						tail = current;
						size--;
						modCount++;
						return true;
					}
					else //remove the Node by pointing the current.next to Node after the next Node.
					{
						current.next = current.next.next;
						size--;
						modCount++;
						return true;
					}
				}
				
				current = current.next; //move current to the next Node
			}
		}
		
		return false;
	} //remove()

	/**
	 * Getter for the size of the Linked List.
	 * 
	 * @return - int - returns the current size of the LinkedList
	 */
	@Override
	public int size()
	{
		return size;
	} //size()

	/**
	 * Creates an array equal to the size of the Linked List and will return an array
	 * with the data within the element variable of each Node within the Linked List.
	 * 
	 * @return - Object[] - Returns an array with the Object residing within the Node
	 * element variable.
	 */
	@Override
	public Object[] toArray()
	{
		if(isEmpty())
		{
			return null;
		}
		
		Node current = head;
		Object[] array = new Object[size];
		
		//Loop through LL and assign the .element to the index of the array
		for(int i = 0; i < size; i++) 
		{
			array[i] = current.element;
			current = current.next;
		}
		
		return array;
	} //toArray()
	
	// ****** INDEX-BASED METHODS ******
	
	/**
	 * If the LL does not contain the element, add a new element at an index.
	 */
	@Override
	public void add(int index, T element)
	{
		if(index < 0 || index >= size()) //Check if the index is out of bounds
		{
			throw new IndexOutOfBoundsException();
		}
		
		if(!contains(element)) //if the element is not currently in the list
		{
			Node current = head;
			
			//check if the head is being replaced
			if(index == 0)
			{
				Node temp = new Node(element); //create the new Node with the element
				head = temp; //point the head to the new element
				head.next = current; //point the head.next to the previous head stored in current
				size++;
				modCount++;
			}
			else
			{
				for(int i = 0; i < index-1; i++) //stop at the Node just before the index
				{
					current = current.next; //traverse the LL
				}
				
				Node temp = new Node(element); //create the new Node with the element
				temp.next = current.next; //point the new Node.next to current.next
				current.next = temp; //point current.next = to the new Node
				size++;
				modCount++;
			}
		}
	} //add(index)

	/**
	 * Sets the element at the desired index to a new value if the element does not
	 * already exist in the LinkedList and return the old value.
	 * 
	 * @return - T - Returns the old value after the element was set to the new value
	 */
	@Override
	public T set(int index, T element)
	{
		T oldValue = null;
		
		//check if index is out of bounds
		if(index < 0 || index >= size())
		{
			throw new IndexOutOfBoundsException();
		}
		
		//if the LL does not currently contain the element to set
		if(!contains(element))
		{
			Node current = head;
			
			for(int i = 0; i < index; i++) //loop through the Linked List until arriving at
										   //  the desired index
			{
				current = current.next;
			}
			
			oldValue = current.element;
			current.element = element;
			modCount++;
		}
		
		//return the element that was previously in the Node at that index
		return oldValue;
	} //set()

	/**
	 * Returns first index of the desired element. If the element is not found it will
	 * return -1.
	 * 
	 * @return - int - Returns the index of the desired element. If the element was not
	 * found, it will return -1.
	 */
	@Override
	public int indexOf(Object element)
	{
		int index = -1;
		
		if(!isEmpty()) //if the LL is not empty
		{
			int count = 0;
			
			for(T data : this)
			{
				if(data.equals(element))
				{
					index = count;
					return index;
				}
				
				count++;
			}
		}
		
		return index; //if element is not found, return -1;
	} //indexOf()

	/**
	 * Removes the Node at desired index.
	 * 
	 * @return - T - Returns the data in the Node that was removed.
	 */
	@Override
	public T remove(int index)
	{
		T removed = null;
		
		if(index < 0 || index >= size())
		{
			throw new IndexOutOfBoundsException();
		}
		
		if(index == 0) //if the desired element is the head
		{
			removed = head.element;
			head = head.next;
			return removed;
		}
		else
		{
			Node current = head;
			for(int i = 0; i < index-1; i++) //stop at the Node just before the index
			{
				current = current.next; //traverse the LL
			}
			
			removed = current.next.element;
			current.next = current.next.next; //remove the index by skipping over it
			size--;
			modCount++;
			
			return removed;
		}
	} //remove()

	/**
	 * Returns the last index of the desired element in the LinkedList. Since this LinkedList
	 * only allows unique elements to exist in the data structure, lastIndexOf will return the
	 * first index of the desired element.
	 * 
	 * @return - T - Returns the index of the desired element. If the element was not
	 * found, it will return -1.
	 */
	@Override
	public int lastIndexOf(Object element)
	{
		return indexOf(element);
		
		//Currently unnecessary but I want this code in case I need to implement this type
		//of method in a future assignment
		/*
		int index = -1;
		 
		if(!contains(element))
		{
			return indexOf;
		}
		
		Node current = head;
		
		for(int i = 0; i < size; i++)
		{
			if(current.element.equals(element))
			{
				indexOf = i;
			}
			
			if(current.next != null)
			{
				current = current.next;
			}
		}
		
		return indexOf;*/
	} //lastIndexOf()
	
	/**
	 * Creates a New LinkedList Iterator (LLIterator) that knows how to traverse
	 * the LinkedList and retrieve the data within the data structure.
	 * 
	 * @return - Iterator<T> - Returns the smart-object that knows how to traverse
	 * the LinkedList and retrieve the data within the data structure.
	 */
	@Override
	public Iterator<T> iterator()
	{
		return new LLIterator(head, this);
	} //iterator()
	
	// ****** SET METHODS ******

	/**
	 * Add all elements from a Collection to the end of the LinkedList. Return true
	 * if any changes were made to the LinkedList.
	 * 
	 * @return - boolean - If the add was successful return true.
	 */
	@Override
	public boolean addAll(Collection<? extends T> other)
	{
		if(other.isEmpty())
		{
			return false;
		}
		
		for(T object : other)
		{
			add(object);
		}
		
		return true;
	} //addAll()

	/**
	 * Add all elements from a Collection at the desired index if the element does not 
	 * currently exist in the LinkedList. Return true if any changes were made to the
	 * LinkedList.
	 * 
	 * @return - boolean - 
	 */
	@Override
	public boolean addAll(int index, Collection<? extends T> other)
	{
		//check if index is out of bounds
		if(index < 0 || index >= size())
		{
			throw new IndexOutOfBoundsException();
		}
		
		//make sure other isn't empty
		if(other.isEmpty())
		{
			return false;
		}
		
		//if LL is empty, just add the entire Collection
		if(isEmpty())
		{
			for(T element : other)
			{
				add(element);
			}
			return true;
		}
		else
		{
			//check to see if the index is the head
			if(index == 0)
			{
				for(T element : other)
				{
					add(0, element);
					index++;
				}
				return true;
			}
			else
			{
				for(T element : other)
				{
					if(!contains(element))
					{
						add(index, element);
						index++;
					}
				}
				return true;
			}
		}
	} //addAll()

	/**
	 * Checks to see if the LinkedList contains all of the elements in the collection
	 * passed to it.
	 * 
	 * @return - boolean - returns true only if all elements within the collection exist
	 * in the LinkedList. Immediately returns false LinkedList does not contain the element.
	 */
	@Override
	public boolean containsAll(Collection<?> other)
	{
		for(Object element : other)
		{
			if(!contains(element)) //if the LinkedList does not contain the element
			{
				return false; //return false and exit the method
			}
		}
		
		return true;
	} //containsAll()

	/**
	 * Removes all elements within a collection if they exist within the LinkedList.
	 * Returns true if the LinkedList has changed.
	 * 
	 * @return - boolean - Returns true if any elements within the LinkedList have
	 * been removed.
	 */
	@Override
	public boolean removeAll(Collection<?> other)
	{
		boolean changed = false;
		for(Object element : other)
		{
			if(remove(element))
			{
				changed = true;
			}
		}
		return changed;
	} //removeAll()

	/**
	 * Retains only the elements contained in 'other' Collection.
	 * 
	 * @return - boolean - Return true if any changes have been made to the original
	 * LinkedList.
	 */
	@Override
	public boolean retainAll(Collection<?> other)
	{
		if(other.isEmpty())
		{
			return false;
		}
		
		//create the array of values that need to be removed
		UniqueList<T> toBeRemoved = new UniqueList<T>();
		boolean changed = false;
		
		Iterator<T> iterator = iterator();
		
		while(iterator.hasNext())
		{
			T data = iterator.next();
			boolean hit = false;
			
			//compare data to each element in other
			//if it doesn't equal anything, put it in the removal collection
			for(Object collectionElement : other)
			{
				if(data == collectionElement)
				{
					hit = true;
				}
			}
			
			if(!hit)
			{
				toBeRemoved.add(data);
				changed = true;
			}
		}
		
		//then call removeAll
		removeAll(toBeRemoved);
		
		return changed;
		
	} //retainAll()
	
	// ****** DO NOT IMPLEMENT ******

	@Override
	public List<T> subList(int start, int end)
	{
		throw new UnsupportedOperationException("subList() is not supported");
	} //subList()

	@SuppressWarnings("hiding")
	@Override
	public <T> T[] toArray(T[] arrayToFill)
	{
		throw new UnsupportedOperationException("toArray() is not supported");
	} //toArray()

	@Override
	public ListIterator<T> listIterator()
	{
		throw new UnsupportedOperationException("listIterator() is not supported");
	} //listIterator()

	@Override
	public ListIterator<T> listIterator(int index)
	{
		throw new UnsupportedOperationException("listIterator() is not supported");
	} //listIterator()
	
	/**
	 * String representation of the LinkedList Object.
	 * 
	 * @return - String - String representation of the LinkedList Object.
	 */
	public String toString()
	{
		String listString ="[";
		
		for(T element : this)
		{
			listString += element + ", ";
		}
		
		listString += "]";
		
		return listString;
	} //toString()
} //UniqueList.java
