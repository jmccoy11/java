package edu.greenriver.it.trees;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;

public class BinarySearchTree<T extends Comparable<T>> implements Iterable<T>
{
	//fields
	private Node root;
	private int size;
	
	public BinarySearchTree()
	{
		//do nothing
	}
	
	//basic methods
	public void add(T element)
	{
		//empty tree?
		if(root == null)
		{
			root = new Node(element);
			size++; //don't forget this part
		}
		else 
		{
			//we need to recursively find the position of our new element
			root = add(element, root);
		}
	}
	
	private Node add(T element, Node current)
	{
		//if we have a null current node, then we found an open spot
		if(current == null) //base case
		{
			size++;
			return new Node(element);
		}
		
		//are we looking to the left and right
		int compare = current.data.compareTo(element);
		
		if(compare < 0) //element is greater than current  :  go right
		{
			current.right = add(element, current.right);
		}
		else if (compare > 0) //element is smaller than current :  go left
		{
			current.left = add(element, current.left);
		}
		
		return current;
	}
	
	//returns true if the element is found and removed, otherwise returns false
	public void remove(T element)
	{
		root = remove(element, root);
	}
	
	private Node remove(T element, Node current)
	{
		//base case?
		if(current == null)
		{
			return null; //not found!
		}
		
		int compare = current.data.compareTo(element);
		
		if(compare < 0) //element is greater than current.data  :  go right
		{
			current.right = remove(element, current.right);
		}
		else if (compare > 0) //element is less than current.data  :  go left
		{
			current.left = remove(element, current.left);
		}
		else //current is equal
		{
			//Node has two children
			if (current.left != null && current.right != null)
			{
				//replace the data at our current node
				Node maxLeft = findMax(current.left);
				current.data = maxLeft.data;
				
				//recursively remove the largest element in the left subtree
				current.left = remove(maxLeft.data, current.left);
				
				//size-- not necessary because the code below will eventually
				//do this.
			}
			else if (current.left != null)
			{
				current = current.left;
				size--;
			}
			else if (current.right != null)
			{
				current = current.right;
				size--;
			}
			else //no children
			{
				current = null;
				size--;
			}
		}
		

		return current;
	}
	
	private Node findMax(Node current)
	{
		if (current.right != null)
		{
			return findMax(current.right);
		}
		
		return current;
	}
	
	public boolean contains(T element)
	{
		return contains(element, root);
	}
	
	private boolean contains(T element, Node current)
	{
		if(current == null)
		{
			return false;
		}
		
		int compare = current.data.compareTo(element);
		
		if(compare < 0) //element is greater than current  :  go right
		{
			return contains(element, current.right);
		}
		else if (compare > 0) //element is less than current  :  go left
		{
			return contains(element, current.left);
		}
		else //current is equal
		{
			return true;
		}
	}
	
	public int size()
	{
		return size;
	}
	
	public boolean isEmpty()
	{
		return size == 0;
	}
	
	public void clear()
	{
		root = null;
		size = 0;
	}
	
	public void inOrder() //Left : Node : Right
	{
		inOrder(root);
	}
	
	private void inOrder(Node current)
	{
		if (current != null)
		{
			inOrder(current.left); //left
			System.out.println(current.data); //node
			inOrder(current.right); //right
		}
	}
	
	public void postOrder() //Left : Right : Node
	{
		postOrder(root);
	}
	
	private void postOrder(Node current)
	{
		if (current != null)
		{
			inOrder(current.left); //left
			inOrder(current.right); //right
			System.out.println(current.data); //node
		}
	}
	
	public void preOrder() //Node : Left : Right
	{
		preOrder(root);
	}
	
	private void preOrder(Node current)
	{
		if (current != null)
		{
			System.out.println(current.data); //node
			inOrder(current.left); //left
			inOrder(current.right); //right
		}
	}
	
	public List<T> toList()
	{
		List<T> results = new ArrayList<>();
		toList(root, results);
		
		return results;
	}
	
	private void toList(Node current, List<T> results)
	{
		if (current != null)
		{
			inOrder(current.left); //left
			results.add(current.data); //node  //when we visit the node, add to results
			inOrder(current.right); //right
		}
	}
	
	@Override
	public Iterator<T> iterator() 
	{
		return new BSTIterator(root);
	}
	
	@SuppressWarnings({ "unchecked", "unused" })
	private class NaiveIterator implements Iterator<T>
	{
		private Object[] data;
		private int position;
		
		public NaiveIterator(BinarySearchTree<T> owner)
		{
			data = owner.toList().toArray();
		}
		
		@Override
		public boolean hasNext() 
		{
			return position < data.length;
		}

		
		@Override
		public T next() 
		{
			return (T)data[position++];
		}
	}
	
	private class BSTIterator implements Iterator<T>
	{
		private Stack<Node> nodeStack = new Stack<>();
		
		public BSTIterator(Node current)
		{
			//move to the first node
			while(current != null)
			{
				nodeStack.push(current);
				current = current.left;
			}
		}
		
		@Override
		public boolean hasNext() 
		{
			return !nodeStack.isEmpty();
		}

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
		}
	}
	
	//binary tree node
	private class Node
	{
		private T data;
		private Node left;
		private Node right;
		
		private Node(T data)
		{
			this.data = data;
		}
		
		public String toString()
		{
			//using the ternary operator
			// (question) "?"=true value  ":"= false value
			//prevents a NullPointerException
			String dataString = (data == null) ? "null" : data.toString();
			String leftChild = (left == null) ? "null" : left.data.toString();
			String rightChild = (right == null) ? "null" : right.data.toString();
			
			return leftChild + "<--" + dataString + "-->" + rightChild;
		}
	}
}
