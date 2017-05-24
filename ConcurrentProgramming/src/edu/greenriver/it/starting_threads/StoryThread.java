package edu.greenriver.it.starting_threads;

public class StoryThread {

	public static void main(String[] args)
	{
		Thread thread = new Story();
		
		thread.start();
		
		try 
		{
			thread.join();
		} 
		catch (InterruptedException e1) 
		{
			//Do nothing
		}
		
		try 
		{
			thread.join();
		} 
		catch (InterruptedException e) 
		{
			//Do nothing
		}
		
		System.out.println("And they lived hapily ever after!");
	}

}
