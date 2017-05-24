package edu.greenriver.it.trees;

public class TestBST 
{

	public static void main(String[] args) 
	{
		BinarySearchTree<Integer> bst = new BinarySearchTree<>();
		
		bst.add(60);
		bst.add(41);
		bst.add(74);
		bst.add(16);
		bst.add(53);
		bst.add(46);
		bst.add(55);
		bst.add(42);
		
		System.out.println("contains(60):" + bst.contains(60));
		System.out.println("contains(74):" + bst.contains(74));
		System.out.println("contains(0):" + bst.contains(0));
		
		bst.remove(60);
		System.out.println("contains(60):" + bst.contains(60));
		
		System.out.println("inOrder()");
		bst.inOrder();
		
		System.out.println();
		
		System.out.println("postOrder()");
		bst.postOrder();
		
		System.out.println();
		
		System.out.println("preOrder()");
		bst.preOrder();
		
		System.out.println();
		
		System.out.println("Testing Iterator");
		for(int element : bst)
		{
			System.out.println(element);
		}
		
		bst.clear();
		
		bst.add(3);
		bst.add(63);
		bst.add(44);
		bst.add(28);
		bst.add(1);
		bst.add(2);
		bst.add(94);
		bst.add(32);
		bst.add(1);
		bst.add(5);
		bst.add(11);
		bst.add(117);
		bst.add(86);
		bst.add(60);
		bst.add(59);
		
		bst.remove(63);
	}

}
