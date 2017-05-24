package edu.greenriver.it.queues;

import java.util.Random;

public class MaryHeapInformalTest {

	public static void main(String[] args) 
	{
		//create our queue
		MaryHeap<Integer> queue = new MaryHeap<>();
		
		//add a few items
		int[] input = {6, 22, 5, 77, 12, 11, 42, 150, 11, 11, 11};
		for (int i = 0; i < input.length; i++)
		{
			queue.insert(input[i]);
		}
		
		//remove all elements and print them in order
		while (!queue.isEmpty())
		{
			System.out.println(queue.delMin());
		}
		
		//generate a large random input array
		Random random = new Random();
		random.setSeed(1);
		Integer[] largeInput = new Integer[1000];
		for (int i = 0; i < largeInput.length; i++)
		{
			largeInput[i] = random.nextInt(1000);
		}
		
		queue = new MaryHeap<>(largeInput);
		while (!queue.isEmpty())
		{
			System.out.println(queue.delMin());
		}
	}
}
