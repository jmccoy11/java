/*
 * Jonnathon McCoy
 * May 3, 2017
 * 
 * package: edu.greenriver.it.iterativebst
 * class: IterativeBSTInformalTesting.java
 * 
 * Driver class for informally testing IterativeBinarySearchTree.java
 */

package edu.greenriver.it.iterativebst;

import java.util.List;

/**
 * Driver class for informally testing IterativeBinarySearchTree.java
 *
 * @author Jonnathon McCoy
 * @version 1.0
 */
public class IterativeBSTInformalTesting 
{
	private static IterativeBinarySearchTree<String> testBST = new IterativeBinarySearchTree<>();
	
	/**
	 * Entry point for BST Informal Testing.
	 * 
	 * @param args - Command-line arguments
	 */
	public static void main(String[] args) 
	{		
		System.out.println("Adding Jared, Carey, Alvin, Jack, Emma, Sarah"
				+ "Teri, and Sarah again and printing the size() of the BinarySearchTree");
		System.out.println(" Jared: " + testBST.addUpdate("Jared") + ": size(): " + testBST.size());
		System.out.println(" Carey: " + testBST.addUpdate("Carey") + ": size() : " + testBST.size());
		System.out.println(" Alvin: " + testBST.addUpdate("Alvin") + ": size() : " + testBST.size());
		System.out.println(" Jack: " + testBST.addUpdate("Jack") + ": size() : " + testBST.size());
		System.out.println(" Emma: " + testBST.addUpdate("Emma") + ": size() : " + testBST.size());
		System.out.println(" Sarah: " + testBST.addUpdate("Sarah") + ": size() : " + testBST.size());
		System.out.println(" Teri: " + testBST.addUpdate("Teri") + ": size() : " + testBST.size());
		System.out.println(" Sarah again? Should maintain the same size(): " + testBST.addUpdate("Sarah")  
			+ ": size(): " + testBST.size());
		System.out.println();
		
		System.out.println("Printing out the BST toString(): " + testBST.toString());
		System.out.println();
		
		System.out.println("Testing BST inOrder() with for-each loop: ");
		List<String> inOrder = testBST.inOrder();
		for(String element : inOrder)
		{
			System.out.println(element);
		}
		System.out.println();
		
		System.out.println("Testing contains():");
		System.out.println("contains(Alvin)?:" + testBST.contains("Jared"));
		System.out.println("contains(Teri):" + testBST.contains("Teri"));
		System.out.println("contains(Jonnathon)" + testBST.contains("Jonnathon"));
		System.out.println();
		
		System.out.println("Testing get(Emma) and get(Jonnathon)");
		String retrieve = testBST.get("Emma");
		System.out.println("get(Emma) which should return Emma: " + retrieve);
		retrieve = testBST.get("Jonnathon");
		System.out.println("get(Jonnathon), which should return null: " + retrieve);
		System.out.println();
		
		System.out.println("Testing isEmpty() and clear()");
		System.out.println("isEmpty()?, should be false: " + testBST.isEmpty());
		System.out.println();
		
		testBST.clear();
		System.out.println("Testing clear() and isEmpty() again, should be true: " + testBST.isEmpty());
	} //main()
} //IterativeBSTInformalTesting.java
