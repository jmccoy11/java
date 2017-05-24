/*
 * Jonnathon McCoy
 * May 5, 2017
 * 
 * package: edu.greenriver.it.iterativebst
 * class: BSTSymbolTableInformalTesting.java
 * 
 * Driver class for informally testing BSTSymbolTable.java
 */

package edu.greenriver.it.bstsymboltable;

import java.util.List;

/**
 * Driver class for informally testing BSTSymbolTable.java
 * 
 * @author Jonnathon McCoy
 * @version 1.0
 *
 */
public class BSTSymbolTableInformalTesting 
{
	private static BSTSymbolTable<String, Integer> symbolTable = new BSTSymbolTable<String, Integer>();
	
	/**
	 * Entry point for the program.
	 * 
	 * @param args - Command line arguments
	 */
	public static void main(String[] args) 
	{
		System.out.println(" Adding Names and ages of the following: \n"
				+ "Jared(10), Carey(22), Alvin(25), Jack(8), Emma(15), Sarah(20), Teri(35), and "
				+ "Sarah again and printing the size() of the SymbolTable");
		System.out.println(" Jared: " + symbolTable.put("Jared", 10) + ": size() : " + symbolTable.size());
		System.out.println(" Carey: " + symbolTable.put("Carey" , 22) + ": size() : " + symbolTable.size());
		System.out.println(" Alvin: " + symbolTable.put("Alvin", 25) + ": size() : " + symbolTable.size());
		System.out.println("  Jack: " + symbolTable.put("Jack", 8) + ": size() : " + symbolTable.size());
		System.out.println("  Emma: " + symbolTable.put("Emma", 15) + ": size() : " + symbolTable.size());
		System.out.println(" Sarah: " + symbolTable.put("Sarah", 20) + ": size() : " + symbolTable.size());
		System.out.println("  Teri: " + symbolTable.put("Teri", 35) + ": size() : " + symbolTable.size());
		System.out.println();
		
		System.out.println(" SymbolTable toString() : ");
		System.out.println(symbolTable.toString());
		System.out.println();
		
		System.out.println(" Update Sarah and change her age to 18? Should maintain the same size(): " 
		+ symbolTable.put("Sarah", 18) + ": size(): " + symbolTable.size());
		System.out.println(symbolTable);
		System.out.println();
		
		System.out.println(" Update Sarah again but change her age back to 20? Should maintain the same size(): " 
				+ symbolTable.put("Sarah", 20) + ": size(): " + symbolTable.size());
		System.out.println(symbolTable);
		System.out.println();
		
		System.out.println(" Updating Jared (the BST root) to be 75? Should maintain the same size(): " 
				+ symbolTable.put("Jared", 75) + ": size(): " + symbolTable.size());
		System.out.println(symbolTable);
		System.out.println();
		
		System.out.println(" Updating Jared (the BST root) back to 10? Should maintain the same size(): " 
				+ symbolTable.put("Jared", 10) + ": size(): " + symbolTable.size());
		System.out.println(symbolTable);
		System.out.println();
				
		System.out.println("Getting the list of keys");
		List<String> keys = symbolTable.keys();
		for(String key : keys)
		{
			System.out.println(key);
		}
		System.out.println();
		
		System.out.println("Getting the list of values");
		List<Integer> values = symbolTable.values();
		for(Integer value : values)
		{
			System.out.println(value);
		}
		System.out.println();
		
		System.out.println("Testing isEmpty(), should be false: " + symbolTable.isEmpty());
		symbolTable.clear();
		System.out.println("Testing isEmpty(), should be true: " + symbolTable.isEmpty());
	} //main()
} //BSTSymbolTableInformalTesting.java
