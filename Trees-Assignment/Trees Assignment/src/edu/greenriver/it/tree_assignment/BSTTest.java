package edu.greenriver.it.tree_assignment;

/*
 * Jeremy Manalo
 * 5-4-17
 * BSTTest.java
 * Binary Search Tree Test
 */

import java.util.List;

public class BSTTest 
{

	public static void main(String[] args) 
	{
		//construct
		BinarySearchTree<Integer> tree = new BinarySearchTree<>();
		
		//adds
		tree.addUpdate(5);
		tree.addUpdate(2);
		tree.addUpdate(3);
		tree.addUpdate(7);
		tree.addUpdate(5);
		
		//contain
		System.out.println(tree.contains(5));
		
		//get
		int three = tree.get(3);
		System.out.println(three);
		System.out.println(tree.size());
		
		//list
		List<Integer> testList = tree.inOrder();
		
		for(Integer number : testList)
		{
			System.out.print(number);
		}
		
		//clear
		tree.clear();
		List<Integer> testList2 = tree.inOrder();
		
		for(Integer number : testList2)
		{
			System.out.println(number);
		}
		

	}

}
