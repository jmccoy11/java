package edu.greenriver.it.starting_threads;

public class Story extends Thread implements Runnable
{

	@Override
	public void run() 
	{
		System.out.println("Welcome to my Story Book!");
		
		try 
		{
			Thread.sleep(2000);
		} 
		catch (InterruptedException e) 
		{
			// Do nothing
		}
		
		System.out.print("The story began long, long ago");
		
		for (int i = 0; i < 10; i++) 
		{
			System.out.print(".");
			try 
			{
				Thread.sleep(500);
			} 
			catch (InterruptedException e) 
			{
				//Do nothing
			}
		}
		
		System.out.println();
	}
}

