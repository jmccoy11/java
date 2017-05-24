/*
 * Jonnathon McCoy
 * May 3, 2017
 * 
 * package: edu.greenriver.it.iterativebst
 * class: IterativeBinarySearchTree.java
 * 
 * A binary search tree that uses iteration to traverse through the tree rather than recursion.
 */

package edu.greenriver.it.iterativebst;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;

/**
 *  * package: edu.greenriver.it.iterativebst
 * class: IterativeBinarySearchTree.java
 * 
 * A binary search tree that uses iteration to traverse through the tree rather than recursion.
 *
 * @author Jonnathon McCoy
 * @version 1.0
 *
 * @param <T> the type of elements maintained by this data structure
 */
public class IterativeBinarySearchTree<T extends Comparable<T>> implements Iterable<T>
{
	//fields
	private Node root;
	private int size;
	
	//methods
	
	/**
	 * This method adds new elements to the BST. If the element already exists, it will overwrite the
	 * element with updated element.
	 * 
	 * @param element - desired element to add or update in the BST
	 * @return boolean - if the element was successfully added return true. If an element was updated, return false.
	 */
	public boolean addUpdate(T element)
	{
		//empty tree?
		if(root == null)
		{
			root = new Node(element);
			size++; //don't forget this part
			return true;
		}
		else
		{
			Node current = root;
			Node previous = current;
			
			while(current != null)
			{
				int compare = element.compareTo(current.data);
				
				if(compare < 0) //the element is less than the current node's data  :  go left
				{					
					if(current.left == null) //if next is null, then the new element goes here
					{
						current.left = new Node(element);
						size++;
						return true;
					}
					
					previous = current;
					current = current.left; //else move current to it's own left child
				}
				else if (compare > 0) //the element is greater than the current node's data  :  go right
				{					
					if(current.right == null) //if next is null, then the new element goes here
					{
						current.right = new Node(element);
						size++;
						return true;
					}
					
					previous = current;
					current = current.right;
				}
				else //element equals current node
				{
					if(current == root) //we are updating the root
					{
						Node temp = new Node(element);
						temp.left = root.left;
						temp.right = root.right;
						root = temp;
					}
					else if(previous.left == current) //if we came from the previous.left
					{
						Node temp = new Node(element);
						temp.left = current.left;
						temp.right = current.right;
						current = temp;
						previous.left = current; //point the previous element's left pointer to the new current
					}
					else //(previous.right == current) //else we came from previous.right
					{
						Node temp = new Node(element);
						temp.left = current.left;
						temp.right = current.right;
						current = temp;
						previous.right = current; //point the previous element's right pointer to the new current
					}
					
					return false; //no new entry was actually added
				} //end addUpdate() if/else while(current!=null) if/else statement
			} //end addUpdate() if/else while(current!=null)
			
			return false;  //if anything goes wrong for whatever reason
		} //end addUpdate() if/else statement
	} //addUpdate()
	
	/**
	 * This method retrieves the desired element and returns it. Otherwise it will return null if the
	 * element was not found in the BST
	 * 
	 * @param element - desired element to be retrieved
	 * @return T - the retrieved element. If it wasn't found, return null
	 */
	public T get(T element)
	{
		Node current = root;
		
		while(current != null)
		{
			int compare = element.compareTo(current.data);
			
			if(compare == 0) //if the element is what the method is requesting
			{
				return current.data; //return the data
			}
			else if(compare < 0) //element is less than current  :  go left
			{
				current = current.left;
			}
			else // (compare > 0)  //element is greater than current  :  go right
			{
				current = current.right;
			}
		} //end get() while loop
		
		return null;
	} //get()
	
	/**
	 * This method searches through the BST for the first instance (which should be the only instance) 
	 * of desired element.
	 * 
	 * @param element - the desired element
	 * @return boolean - return true if found, else return false
	 */
	public boolean contains(T element)
	{
		if(root == null)
		{
			return false;
		}
		else
		{
			Node current = root;
			
			while(current != null)
			{
				int compare = element.compareTo(current.data);
				
				if(compare == 0)
				{
					return true;
				}
				else if(compare < 0)
				{
					current = current.left;
				}
				else // (compare > 0)
				{
					current = current.right;
				}
			} //end contains() if/else while loop
			
			return false;
		} //end contains() if/else statement
	} //contains()
	
	/**
	 * The number of elements within the BST.
	 * 
	 * @return int - the number of elements within the BST.
	 */
	public int size()
	{
		return size;
	} //size()
	
	/**
	 * Checks if the BST has no elements in it.
	 * 
	 * @return boolean - if size is zero, the BST is empty, return true, else return false.
	 */
	public boolean isEmpty()
	{
		return size == 0;
	} //isEmpty()
	
	/**
	 * Clear the BST. Set the root to null and the size to zero.
	 */
	public void clear()
	{
		root = null;
		size = 0;
	} //clear()
	
	/**
	 * Add the elements to an ArrayList in an ordered fashion and return that List<T>
	 * 
	 * @return List<T> - An ordered list of the elements in the BST.
	 */
	public List<T> inOrder() //Left : Root : Right
	{
		List<T> inOrder = new ArrayList<>();
		
		Node current = root;
		Stack<Node> nodeStack = new Stack<>();
		
		while(current != null) //dive to the left
		{
			nodeStack.push(current);
			current = current.left;
		}
		
		while(!nodeStack.isEmpty())
		{
			current = nodeStack.pop();  //reload the last not null element to current
			
			if(current.right != null) //go right
			{
				nodeStack.push(current.right); //push onto the stack
				
				Node next = current.right;
				while(next.left != null) //then dive left
				{
					nodeStack.push(next.left); //push onto the stack
					next = next.left;
				}
			}
			
			inOrder.add(current.data);
		} //end inOrder() while(!nodeStack.isEmpty())
		
		return inOrder;
	} //end inOrder()
	
	/**
	 * A Left:Node:Right String representation of the entire BST. Coincidentally, inOrder representation.
	 * 
	 * return String - String representation of the BST.
	 */
	public String toString()
	{
		String inOrder = "";
		
		Node current = root;
		Stack<Node> nodeStack = new Stack<>();
		
		while(current != null) //dive to the left
		{
			nodeStack.push(current);
			current = current.left;
		}
		
		while(!nodeStack.isEmpty())
		{
			current = nodeStack.pop();  //reload the last not null element to current
			
			if(current.right != null) //go right
			{
				nodeStack.push(current.right); //push onto the stack
				
				Node next = current.right;
				while(next.left != null) //then dive left
				{
					nodeStack.push(next.left); //push onto the stack
					next = next.left;
				}
			}
			
			inOrder += current.data + ", ";
		} //toString() while(!nodeStack.isEmpty())
		
		int chopOffEnd = 2;
		inOrder = inOrder.substring(0, inOrder.length() - chopOffEnd);
		return inOrder;
	} //toString()
	
	/**
	 * Create a smart object that knows how to iterate through the BST.
	 * 
	 * return BSTIterator - The smart object that knows how to traverse the BST
	 */
	@Override
	public Iterator<T> iterator() 
	{
		return new BSTIterator(root);
	} //iterator()
	
	/*
	 * Smart object that knows how to iterate through the BST in order
	 */
	private class BSTIterator implements Iterator<T>
	{
		private Stack<Node> nodeStack = new Stack<>();
		
		/**
		 * BSTIterator constructor. 
		 * 
		 * @param current - takes a node to start with and will dive down to the left of the BST,
		 * adding each node it touches to an internal Stack.
		 */
		public BSTIterator(Node current)
		{
			//move to the first node
			while(current != null)
			{
				nodeStack.push(current);
				current = current.left;
			}
		} //BSTIterator()
		
		/**
		 * As long as the stack is not empty, there are more nodes to traverse.
		 * 
		 * @return boolean - if the stack isn't empty, return true, else return false;
		 */
		@Override
		public boolean hasNext() 
		{
			return !nodeStack.isEmpty();
		} //hasNext()

		/**
		 * Return the next ordered Node in the stack. Then check if the right child is not equal to null.
		 * If it isn't null, dive down to the left of that sub-tree, adding each Node to the stack as it
		 * touches it. This results in the stack returning values in an inOrder fashion.
		 * 
		 * @return T - the last element on the stack.
		 */
		@Override
		public T next() 
		{
			//step #1: retrieve the next element to report
			Node next = nodeStack.pop();
			
			//step #2: if there is a right sub-tree, find the smallest element
			//adding Nodes to the stack as we go
			if(next.right != null)
			{
				//add the right child
				nodeStack.push(next.right);
				
				//and dive to the left of our right child
				Node current = next.right;
				while(current.left != null)
				{
					nodeStack.push(current.left);
					current = current.left;
				}
			}
			
			return next.data;
		} //next()
	} //BSTIterator()
	
	/*
	 * binary tree node
	 * 
	 * Contains the data within of type <T> and has a pointer to the left and right child.
	 */
	private class Node
	{
		private T data;
		private Node left;
		private Node right;
		
		/*
		 * Constructor for the Node
		 */
		private Node(T data)
		{
			this.data = data;
		} //Node()
		
		/**
		 * Create a data String that shows the left child : Node : right child
		 * 
		 * @return String - String representation of the Node object
		 */
		public String toString()
		{
			//using the ternary operator
			// (question) "?"=true value  ":"= false value
			//prevents a NullPointerException
			String dataString = (data == null) ? "null" : data.toString();
			String leftChild = (left == null) ? "null" : left.data.toString();
			String rightChild = (right == null) ? "null" : right.data.toString();
			
			return leftChild + "<--" + dataString + "-->" + rightChild;
		} //toString()
	} //Node()
} //IterativeBinaraySearchTree.java
