package edu.greenriver.it.iterators;

import java.util.ArrayList;
import java.util.Iterator;

public class BagTest 
{

	public static void main(String[] args) 
	{
		//create a bag
		Bag myBag = new Bag(8);
		
		//add a few elements to the bag
		myBag.add("eloquent");
		myBag.add("hilarious");
		myBag.add("equestrian");
		
		//System.out.println(myBag.toString());
		
		//this doesn't work! we need iterators
		//for (int i=0; i < myBag.size(); i++)
		//{
			//System.out.println(myBag.get(i)); //don't have access by index
		//}
		
		Iterator<Object> it = myBag.iterator();
		while(it.hasNext())
		{
			String word = (String) it.next();
			System.out.println(word);
		}
		
		for (Object word : myBag)
		{
			System.out.println(word);
		}
		
		//for-each loop, enhanced for-loop (SYNTACTIC SUGAR)
		ArrayList<String> colors = new ArrayList<String>();
		
		colors.add("blue");
		colors.add("red");
		colors.add("white");
		
		//for each loops use iterators internally
		for(String color: colors)
		{
			System.out.println(color);
		}
		
		Iterator<String> iterator = colors.iterator();
		while(iterator.hasNext())
		{
			System.out.println(iterator.next());
		}
		
		//causes a ConcurrentModificationException
		//You cannot make changes to a data structure while using an Iterator
		//Whenever you're using an Iterator, you cannot change the underlying
		//data
		
		//for (String color: colors)
		//{
			//colors.add(color.toUpperCase());
		//}
	}
}
