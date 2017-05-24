import java.util.Arrays;

/**
 * 
 */

/**
 * @author Jonn
 *
 */
public class DivideAndConquer 
{
	
	private static int[] temp;

	/**
	 * @param args
	 */
	public static void main(String[] args) 
	{
		//int[] testArray = {13, 1, 46, -10, 20, 100, 33, 94, 2, 15};
		int[] testArray = {38, 27, 43, 3, 9, 82, 10};
		
		System.out.println("Unsorted:");
		System.out.println(Arrays.toString(testArray));
		
		mergeSort(testArray);
		
		System.out.println();
		System.out.println("Sorted:");
		System.out.println(Arrays.toString(testArray));
	}

	public static void mergeSort(int[] array)
	{
		//preconditions
		if(array.length == 0 || array.length == 1)
		{
			return; //array is already sorted
		}
		
		//O(n) space needed
		temp = new int[array.length];
		
		mergeSort(array, 0, array.length-1);
	}
	
	//low and high represent the lowest index and highest index of the sub-array
	//we are interacting with for each method call
	private static void mergeSort(int[] array, int low, int high)
	{
		//base case: when we have one element in our sub-array
		if (high-low == 0)
		{
			return;
		}
		
		//recurse
		int mid = low + (high-low)/2;
		mergeSort(array, low, mid);
		mergeSort(array, mid+1, high);
		
		//then merge the two sorted sub-arrays
		merge(array, low, mid, mid+1, high);	
	}
	
	private static void merge(int[] array, int lowLeft, int highLeft, int lowRight, int highRight)
	{
		//create a few temporary variables to help with the merge steps below
		int left = lowLeft;
		int right = lowRight;
		int count = highRight - lowLeft + 1;
		
		for(int i = 0; i < count; i++)
		{
			//have we exhausted elements from the left sub-array?
			if(left > highLeft)
			{
				temp[lowLeft + i] = array[right];
				right++;
			}
			//have we exhausted elements from the right sub-array?
			else if(right > highRight)
			{
				temp[lowLeft + i] = array[left];
				left++;
			}
			//is the left element the smallest?
			else if(array[left] < array[right])
			{
				temp[lowLeft + i] = array[left];
				left++;
			}
			//is the right element the smallest?
			else //if(array[right] <= array[left])
			{
				temp[lowLeft + i] = array[right];
				right++;
			}
			
			//System.out.println("Temporary Array: ");
			//System.out.println(Arrays.toString(temp));
			//System.out.println();
		}
		
		//copy the results from our temporary array back to our input array
		for(int i = 0; i < count; i++)
		{
			array[lowLeft + i] = temp[lowLeft + i];
		}
	}
}
