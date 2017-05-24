package edu.greenriver.it.producer_consumer;

public class ConsolePrinter extends Thread 
{
	@Override
	public void run()
	{
		while(true)
		{
			String word;
			try 
			{
				word = SharedWordQueue.getWord();
				System.out.println(word);
			} 
			catch (InterruptedException e) 
			{
				//do nothing
			}
			
		}
	}
}
