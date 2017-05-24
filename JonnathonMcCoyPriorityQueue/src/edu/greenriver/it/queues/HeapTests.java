package edu.greenriver.it.queues;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import org.junit.Assert;

/**
 * This classes verifies the functionality in the MaryHeap class.
 * 
 * @author Jonnathon McCoy
 * @version 1.0
 */
public class HeapTests
{
	//fields
	private MaryHeap<Integer> heap;
	private Integer[] input = {6, 22, 5, 77, 12, 11, 42, 150, 11, 53, 
			37, 9, 81, 70, 10, 1};
	private Integer[] sortedInput;
	
	//setup
	@Before
	public void setup()
	{
		//setup a sorted array for comparisons
		//clone the input array
		sortedInput = new Integer[input.length];
		for(int i = 0; i < input.length; i++)
		{
			sortedInput[i] = input[i];
		}
		
		//sort the new array
		Arrays.sort(sortedInput);
		
		//build heap from original unsorted input
		heap = new MaryHeap<Integer>(input);
	}
	
	//tests
	@Test
	public void testConstructor()
	{
		//iterate over the sorted inputs and check to make sure that delMin spits them out
		//in sorted order if they were added correctly in the first place
		for (int i = 0; i < heap.size(); i++)
		{
			Assert.assertEquals("The minimum should equal " + i, sortedInput[i], heap.delMin());
		}
	}
	
	@Test
	public void testInsert()
	{
		//test that a value can be inserted and that the heap contains it
		heap.insert(4);
		
		Assert.assertTrue("contains(4) should return true if 4 exists in the heap", 
				heap.contains(4));
	}
	
	@Test
	public void testPeek()
	{
		//test that peek() will return false on an empty heap
		MaryHeap<Integer> emptyHeap = new MaryHeap<Integer>();
		Assert.assertEquals("peek() should return null on an empty heap", 
				null, emptyHeap.peek());
				
		//test that peek() will return the minimum element in the heap
		Assert.assertEquals("peek() should return the minimum value of the heap", 
				sortedInput[0], heap.peek());
		
		//test that peek() will return null on an element in a "cleared" heap
		heap.clear();
		Assert.assertEquals("peek() should return null if an element does not \"exist\""
				+ "in a \"cleared\" list", null, heap.peek());
		
	}
	
	@Test
	public void testDelMin()
	{
		//test that peek() will return false on an empty heap
		MaryHeap<Integer> emptyHeap = new MaryHeap<Integer>();
		Assert.assertEquals("delMin() should return null on an empty heap", 
				null, emptyHeap.delMin());
				
		//test that delMin() will return the minimum element in the heap
		Assert.assertEquals("delMin() should return the minimum value of the heap", 
				sortedInput[0], heap.delMin());
		
		//test that delMin() will return the minimum element in the heap
		Assert.assertEquals("delMin() should return the next minimum value of the heap", 
				sortedInput[1], heap.delMin());
		
		//test that delMin() will return null on an element in a "cleared" heap
		heap.clear();
		Assert.assertEquals("delMin() should return null if an element does not \"exist\""
				+ "in a \"cleared\" list", null, heap.delMin());
	}
	
	@Test
	public void testSize()
	{
		//test size() on an empty heap should be zero
		MaryHeap<Integer> emptyHeap = new MaryHeap<Integer>();
		Assert.assertEquals("size() should reflect zero on an empty heap", 
				0, emptyHeap.size());
		
		//test size() on a filled heap should equal the length of the array
		//originally passed to it
		MaryHeap<Integer> sizeHeap = new MaryHeap<Integer>(input);
		Assert.assertEquals("size() should reflect the size of the original array that was"
				+ "passed to it", input.length, sizeHeap.size());
		
		//test that size() reflects insertions
		sizeHeap.insert(89);
		Assert.assertEquals("size() should reflect one larger than the of the original array "
				+ "that was passed to it", input.length+1, sizeHeap.size());
		
		//size that size() reflects deletion
		sizeHeap.delMin();
		Assert.assertEquals("size() should reflect the size of the original array that was"
				+ "passed to it", input.length, sizeHeap.size());
		sizeHeap.delMin();
		Assert.assertEquals("size() should reflect the size of the original array that was"
				+ "passed to it", input.length-1, sizeHeap.size());
		
		//size should reflect zero when a heap is cleared
		sizeHeap.clear();
		Assert.assertEquals("size() should reflect zero on an empty heap", 
				0, sizeHeap.size());
	}
	
	@Test
	public void testIsEmptyAndClear()
	{
		//test if the Heap is empty
		Assert.assertFalse("isEmpty() should return false on list with elements in it",
				heap.isEmpty());
		
		//clear the heap
		heap.clear();
		
		//test if the heap is "empty"
		Assert.assertTrue("isEmpty() should return true on an \"empty\" list", 
				heap.isEmpty());
		
		//attempt to access a value at an index that technically still exists
		Assert.assertFalse("Contains(150) should return false, even if it is in "
				+ "a \"cleared\" heap", heap.contains(150));
		
	}
	
	@Test
	public void testContains()
	{
		//test that contains() will return false on an empty heap
		MaryHeap<Integer> emptyHeap = new MaryHeap<Integer>();
		Assert.assertFalse("contains() should return false on an empty heap",
				emptyHeap.contains(4));
		
		//test that contains() will return true on an element in the heap
		Assert.assertTrue("contains() should return true if an element exists in the "
				+ "heap", heap.contains(42));
		
		//test that contains() will return false on an element not in the heap
		Assert.assertFalse("contains() should return false if an element does not exist"
				+ "in the heap", heap.contains(245));
		
		//test that contains() will return false on an element in a "cleared" heap
		heap.clear();
		Assert.assertFalse("contains() should return false if an element does not \"exist\""
				+ "in a \"cleared\" list", heap.contains(42));
		
	}
}
