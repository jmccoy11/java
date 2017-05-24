package edu.greenriver.it.tree_assignment;

/*
 * Jeremy Manalo
 * 5-4-17
 * BinarySearchTree.java
 * Binary Search Tree
 */

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;

/**
 * A Binary Search Tree data structure 
 * that sorts elements of type T
 * @author Jeremy Manalo
 * @version 1.0
 * @param <T> Generic type to store
 */

public class BinarySearchTree<T extends Comparable<T>>
{
	//fields
	private Node root;
	private int size = 0;
	
	//constructor
	/**
	 * Default constructor does nothing
	 */
	public BinarySearchTree()
	{
		//nothing to start
	}
	
	//methods
	/**
	 * Adds a new element into the tree or updates the element if
	 * it is a duplicate
	 * @param element - Generic element to add
	 * @return true if the add was successful
	 */
	public boolean addUpdate(T element)
	{
		if(isEmpty())
		{
			//empty tree
			root = new Node(element);
			size++;
			return true;
		}
		else
		{
			Node current = root;
			int compare = 0;
			
			while(true)
			{
				compare = current.data.compareTo(element);
				
				if(compare > 0)//left
				{
					if(current.left == null)
					{
						current.left = new Node(element);
						size++;
						return true;
					}
					else if(current.left != null)
					{
						current = current.left;
					}
				}
				else if(compare < 0)//right
				{
					if(current.right == null)
					{
						current.right = new Node(element);
						size++;
						return true;
					}
					else if(current.right != null)
					{
						current = current.right;
					}
				}
				else // == 0
				{
					//found duplicate
					current.data = element;
					return true;
				}
			}
		}		
	}
	
	/**
	 * Checks to see if an element is in the tree
	 * @param element - generic element to look for
	 * @return true is the element is in the tree false otherwise.
	 */
	public boolean contains(T element)
	{
		if(isEmpty())
		{
			//empty tree
			return false;
		}
		else
		{
			Node current = root;
			int compare = 0;
			
			while(true)
			{
				compare = current.data.compareTo(element);
				
				if(compare > 0)//left
				{
					if(current.left == null)
					{
						return false;
					}
					else if(current.left != null)
					{
						current = current.left;
					}
				}
				else if(compare < 0)//right
				{
					if(current.right == null)
					{
						return false;
					}
					else if(current.right != null)
					{
						current = current.right;
					}
				}
				else // == 0
				{
					//found the elment
					return true;
				}
			}
		}
	}
	
	/**
	 * Gets the generic element if it is in the tree
	 * @param element - generic element to get
	 * @return the generic element
	 * @throws IllegalArgumentException() - if user 
	 * asks for element not in the tree.
	 */
	public T get(T element)
	{
		if(!contains(element))
		{
			//throw if element isn't in the tree
			throw new IllegalArgumentException();
		}
		else
		{
			Node current = root;
			int compare = 0;
			
			while(true)
			{
				compare = current.data.compareTo(element);
				
				if(compare > 0)//left
				{
					if(current.left == null)
					{
						throw new IllegalArgumentException();
					}
					else if(current.left != null)
					{
						current = current.left;
					}
				}
				else if(compare < 0)//right
				{
					if(current.right == null)
					{
						throw new IllegalArgumentException();
					}
					else if(current.right != null)
					{
						current = current.right;
					}
				}
				else // == 0
				{
					//found the element
					return current.data;
				}
			}
		}
	}
	
	/**
	 * gets the size of the tree
	 * @return number of elements in the tree
	 */
	public int size()
	{
		return size;
	}
	
	/**
	 * checks if the tree is empty
	 * @return true if the tree is empty, false otherwise.
	 */
	public boolean isEmpty()
	{
		return root == null;
	}
	
	/**
	 * clears the tree by setting the root to null
	 */
	public void clear()
	{
		root = null;
	}
	
	/**
	 * Returns all elements in the tree in 
	 * "in order" sorted order
	 * @return List<T> - a list of all elements in sorted order
	 */
	public List<T> inOrder()
	{
		ArrayList<T> listToReturn = new ArrayList<>();
		Stack<Node> stack = new Stack<>();
		Node current = root;
		
		if(isEmpty())
		{
			//return nothing
            return listToReturn; 
		}
        
		//while there's more to pop or current is not null
        while(!stack.empty() || current != null)
        {
 
        	//if there is more to traverse...
            if(current != null)
            {
            	//push onto stack & go left
                stack.push(current);
                current = current.left;
            }
            //at the end of a leg
            else
            {
            	//get node from stack, add it to list, then go right
                Node popped = stack.pop();
                listToReturn.add(popped.data);
                current = popped.right;
            }
        }
 
        return listToReturn;
	}
	
	private class Node
	{
		private T data;
		private Node left;
		private Node right;
		
		public Node (T data)
		{
			this.data = data;
		}
		
		public String toString()
		{
			String leftString = left == null ? "null" : left.data.toString();
			String rightString = right == null ? "null" : right.data.toString();
			
			return leftString + " <-- " + this.data +" --> " + rightString;
		}
	}
	
}
