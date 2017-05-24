package edu.greenriver.it.exercises.one_dot_two;

public class Node
{
	private Node first;
	private int n;
	private int data;
	private Node next;
	
	public boolean isEmpty()
	{
		return first == null;
	}
	
	public int size()
	{
		return n;
	}
	
	public void push(int data)
	{
		Node oldfirst = first;
		first = new Node();
		first.data = data;
		first.next = oldfirst;
		n++;
	}
	
	public int pop()
	{
		int data = first.data;
		first = first.next;
		n--;
		return data;
	}
	
	public int max()
	{
		int max = 0;
		Node nextNode = null;
		
		if(!isEmpty())
		{
			max = first.data;
			nextNode = first.next;
			
			while(nextNode != null)
			{
				if(nextNode.data > max)
				{
					max = nextNode.data;
				}
				
				nextNode = nextNode.next;
			}
			
			return max;
		}
		else
		{
			return 0;
		}
	}
}
