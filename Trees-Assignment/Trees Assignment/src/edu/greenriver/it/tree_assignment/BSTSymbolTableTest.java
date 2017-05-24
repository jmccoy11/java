package edu.greenriver.it.tree_assignment;

/*
 * Jeremy Manalo
 * 5-4-17
 * BSTSymbolTableTest.java
 * Binary Search Tree
 * Binary Search Tree Symbol Table Test
 */

import java.util.List;

public class BSTSymbolTableTest 
{
	public static void main(String[] args) 
	{
		BSTSymbolTable<String, Integer> table = new BSTSymbolTable<>();

		//put
		table.put("Kenny", 5);
		table.put("Joe", 6);
		table.put("Ben", 7);
		table.put("Ken", 8);
		table.put("Bob", 9);
		
		//size
		System.out.println(table.size() + " :: Size should be 5");
		
		//get/update
		System.out.println(table.get("Kenny") + " :: Should be 5");
		table.put("Kenny", 10);
		System.out.println(table.get("Kenny") + " :: Should now be 10");
		System.out.println(table.size() + " :: Size should still be 5");
		
		//contains
		System.out.println(table.containsKey("Ken")+ " :: Should return true");
		System.out.println(table.containsValue(9)+ " :: Should return true");
		
		//lists
		List<String> testList1 = table.keys();
		
		System.out.println("Keys:");
		for(String name : testList1)
		{
			System.out.print(name + " ");
		}
		
		System.out.println();
		
		List<Integer> testList2 = table.values();
		
		System.out.println("Values:");
		for(Integer number : testList2)
		{
			System.out.print(number + " ");
		}
		
		System.out.println();
		
		//isEmpty and clear
		System.out.println(table.isEmpty() + " :: isEmpty() Should be false");
		table.clear();
		System.out.println(table.isEmpty() + " :: isEmpty() Should be true");

		
	}


}
