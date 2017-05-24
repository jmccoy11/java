//******************************************************************************************************** 
// CLASS: Sorter (Sorter.java) 
// 
// COURSE AND PROJECT INFO 
// CSE205 Object Oriented Programming and Data Structures, Spring 2017 
// Project Number: project-03 
// 
// AUTHOR 
// Jessica Thomas (jdthom22@asu.edu) 
//********************************************************************************************************
package Project3;

import java.util.ArrayList;

public class Sorter 
{
	private static int partition(ArrayList<Student> pList, int low, int high)
	{
		int pivotIndex = low;  //make the pivot start from the left most index given
		int leftIndex = low-1;
		int rightIndex = high+1;
		
		if (rightIndex-leftIndex == 1) //don't want to go below 1 since at least index 0 and 1 might need to be swapped
		{
			return leftIndex;
		}

		while(leftIndex < rightIndex)
		{
			leftIndex++;
			
			while(pList.get(leftIndex).compareTo(pList.get(pivotIndex)) < 0) //compare list.left to list.pivot
			{
				leftIndex++; //if list.left is less than list.pivot, increment the leftIndex until false
			}
			
			rightIndex--;
			
			while(pList.get(rightIndex).compareTo(pList.get(pivotIndex)) > 0) //compare list.right to list.pivot
			{
				rightIndex--; //if list.right is greater than list.pivot, decrement rightIndex until false
			}
		
			if(leftIndex < rightIndex) //once the Indexes finally cross
			{
				swap(pList, leftIndex, rightIndex); //swap the objects of list.left and list.right
				
			}
		}
		
		return rightIndex; //return the right index to create the second second array from which to quickSort
	}
	
	private static void quickSort(ArrayList<Student> pList, int low, int high)
	{
		if(high-low <= 0) //ensure you can't ask for an index out of range or only give one value 
		{
			return;
		}
		else
		{
			int partitionIndex = partition(pList, low, high); //find the partition index position
			quickSort(pList, low, partitionIndex); //solve the left subproblem
			quickSort(pList, partitionIndex+1, high); //solve the right subproblem
		}
	}

	
	public static void sort(ArrayList<Student> pStudentList)
	{
		quickSort(pStudentList, 0, pStudentList.size()-1);
	}
	
	private static void swap(ArrayList<Student> list, int index1, int index2)
	{
		Student temp = list.get(index1);  //temp holder for index 1
		list.set(index1, list.get(index2)); //make index 1, index 2
		list.set(index2, temp); //make index 2 the previous index 1
	}
}
