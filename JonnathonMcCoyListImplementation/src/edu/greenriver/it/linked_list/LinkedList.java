/* 
 * Jonnathon McCoy
 * April 11, 2017
 * 
 * Package: part1
 * Class: Unique List.java
 * 
 * An implementation of the LinkedList class.
 */

package edu.greenriver.it.linked_list;

import java.util.Collection;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 * An implementation of the LinkedList class.
 * 
 * @author Jonnathon McCoy
 * @version 1.0
 * @param <T> Generic for Class-type
 */
public class LinkedList <T> implements List<T>, Iterable<T>
{
	private Node head;
	private Node tail;
	
	private int size;
	
	//modification counter
	private int modCount = 0;
	
	//Node Inner Class
	/*
	 * An implementation of the Node class to be used by a LinkedList
	 */
	private class Node
	{
		private Node next;
		
		private T element;
		
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
	
	private class ULIterator<T> implements Iterator<T>
	{
		private Node currentNode;
		private LinkedList<T> outerList;
		private int currentModCount;
		
		private ULIterator(Node outerHead, LinkedList<T> outerList)
		{
			this.outerList = outerList;
			this.currentNode = outerHead;
			currentModCount = outerList.modCount;
		}
		
		@Override
		public boolean hasNext() 
		{
			if(currentModCount != outerList.modCount)
			{
				throw new ConcurrentModificationException
				("You cannot modify the data in the list while iterating over it");
			}
			
			return currentNode != null && currentNode.next != null;
		}

		@Override
		public T next() 
		{
			if(currentModCount != outerList.modCount)
			{
				throw new ConcurrentModificationException
				("You cannot modify the data in the list while iterating over it");
			}
			
			T element = (T) currentNode.element;
			currentNode = currentNode.next;
			return element;
		}
	}
	
	/**
	 * Method used to add new elements at the end of the Linked List.
	 * 
	 * @param - element - New object to be added to the Linked list.
	 * 
	 * @return - boolean - if an element is already in the list this method will return
	 * 	false after being unable to add to the list. Successful additions will return true.
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
	 * 	resides in the list already.
	 * 
	 * @param - element - Object to be compared to each object stored
	 * 	in the Node's element variables.
	 * 
	 * @return - boolean - If the object is currently in the Linked List, this method
	 * 	will return true. Otherwise, it will return false.
	 */
	@Override
	public boolean contains(Object element)
	{
		if(!isEmpty()) //if the LL is not empty
		{
			Node current = head;
			
			for(int i = 0; i < size; i++)
			{
				if(current.element.equals(element))
				{
					return true;
				}
				
				if(current.next != null) //if the next Node is not actually the tail
				{
					current = current.next; //move the pointer
				}
			}
		}
		
		return false;
	} //contains

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
				if(head.next == null) //if there is only one element in the LL
				{
					clear();
					size = 0;
					modCount++;
					return true;
				}
				else
				{
					head = head.next;
					size--;
					modCount++;
					return true;
				}
			}
			
			for(int i = 0; i < size; i++) //check if element is any other node
			{
				if(current.next != null) //if there's a next Node
				{
					if(current.next.element.equals(element)) //if the next Node element is the element
					{
						if(current.next.equals(tail)) //if the current.next is in fact the tail, move the tail to the current Node.
						{
							tail = current;
							size--;
							modCount++;
							return true;
						}
						else //remove the Node by pointing the current.next to
							 // the .next of the of current.next's Node.
						{
							current.next = current.next.next;
							size--;
							modCount++;
							return true;
						}
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
	 * @return - int - returns the current size of the Linked List
	 */
	@Override
	public int size()
	{
		return size;
	} //size()

	/**
	 * Creates an array equal to the size of the Linked List and will return an array
	 * 	with the data within the element variable of each Node within the Linked List.
	 * 
	 * @return - Object[] - Returns an array with the Object residing within the Node
	 *  element variable.
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
		
		for(int i = 0; i < size; i++)
		{
			array[i] = current.element;
			current = current.next;
		}
		
		return array;
	} //toArray()
	
	// ****** INDEX-BASED METHODS ******

	@Override
	public void add(int index, T element)
	{
		
	} //add()

	@Override
	public T set(int index, T element)
	{
		return null;
	} //set()

	@Override
	public int indexOf(Object element)
	{
		return 0;
	} //indexOf()

	@Override
	public T remove(int index)
	{
		return null;
	} //remove()

	@Override
	public int lastIndexOf(Object element)
	{
		return 0;
	} //lastIndexOf
	
	@Override
	public Iterator<T> iterator()
	{
		return new ULIterator<T>(head, this);
	} //iterator()
	
	// ****** SET METHODS ******

	@Override
	public boolean addAll(Collection<? extends T> other)
	{
		return false;
	} //addAll()

	@Override
	public boolean addAll(int index, Collection<? extends T> other)
	{
		return false;
	} //addAll()

	@Override
	public boolean containsAll(Collection<?> other)
	{
		return false;
	} //containsAll()

	@Override
	public boolean removeAll(Collection<?> other)
	{
		return false;
	} //removeAll()

	@Override
	public boolean retainAll(Collection<?> other)
	{
		return false;
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
} //UniqueList.java
