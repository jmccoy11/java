package edu.greenriver.it.linked_list;

import java.util.Iterator;

public class LLDriver 
{
	private static LinkedList<String> myList = new LinkedList<String>();
	
	public static void main(String[] args) 
	{
		System.out.println("List is empty? " + myList.isEmpty());
		System.out.println("List size is " + myList.size());
		
			System.out.println();
		
		System.out.println("Clear an empty list? ");
		myList.clear();
		System.out.println("Clear didn't break your program.");
		
			System.out.println();
		
		System.out.println("**adding \"a\" to the list**");
		myList.add("a");
		System.out.println("List is empty? " + myList.isEmpty());
		System.out.println("List size is " + myList.size());
		
			System.out.println();
		
		printList();
		
			System.out.println();
		
		System.out.println("**Attempting to add \"a\" again.**");
		myList.add("a");
		System.out.println("List is empty? " + myList.isEmpty());
		System.out.println("List size is " + myList.size());
		
			System.out.println();
		
		printList();
		
			System.out.println();
		
		System.out.println("**Adding \"b\" to the list.**");
		myList.add("b");
		System.out.println("List is empty? " + myList.isEmpty());
		System.out.println("List size is " + myList.size());
		
			System.out.println();
		
		printList();
		
			System.out.println();
			
		System.out.println("Adding more to the list.");
		
			System.out.println();
			
		myList.add("c");
		myList.add("d");
		myList.add("e");
		printList();
		
			System.out.println();
			
		System.out.println("Attempting to remove \"b\"");
		
			System.out.println();
			
		myList.remove("b");
		printList();
		
			System.out.println();
		
		System.out.println("Attempting to remove the head \"a\"");
		
		myList.remove("a");
		printList();
		
			System.out.println();
			
		System.out.println("Attempting to remove the tail \"e\"");
		
		myList.remove("e");
		printList();
	}

	private static void printList()
	{
		System.out.println("*Printing List**");
		for (String letter : myList)
		{
			System.out.println("The letters in the List are " + letter);
		}
		
		System.out.println();
		System.out.println("From the Iterator: ");
		Iterator<String> letterIterator = myList.iterator();
		while(letterIterator.hasNext())
		{
			System.out.println(letterIterator.next());
		}
	}
	
}
