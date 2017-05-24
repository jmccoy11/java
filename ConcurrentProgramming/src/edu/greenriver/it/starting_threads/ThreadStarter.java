package edu.greenriver.it.starting_threads;

public class ThreadStarter 
{

	public static void main(String[] args) 
	{
		SumThread[] threads = new SumThread[4];
		
		for (int i = 0; i < threads.length; i++)
		{
			threads[i] = new SumThread(100);
			threads[i].start();
		}
		
		for(int i = 0; i < threads.length; i++)
		{
			//pause the "main" thread and wait for our
			//worker thread to complete
			try
			{
				threads[i].join();
			}
			catch (InterruptedException ex)
			{
				System.out.println("Thread interrupted " + ex.getMessage());
			}
		}
		
		//print the result of all our threads
		for(int i = 0; i < threads.length; i++)
		{
			System.out.println(threads[i].getSum());
		}
		
	}
	
	public static void countingThread()
	{
		Thread countingThread1 = new Thread(new CountingThread(1, 500));
		Thread countingThread2 = new Thread(new CountingThread(501, 1000));
		Thread countingThread3 = new Thread(new CountingThread(1001, 1500));
		Thread countingThread4 = new Thread(new CountingThread(1501, 2000));
		Thread countingThread5 = new Thread(new CountingThread(2001, 2500));
		
		Thread[] threads = {countingThread1,countingThread2,countingThread3,
				countingThread4,countingThread5,};
	
		for(Thread thread : threads)
		{
			thread.start();
		}
		
		for(int i = 501; i <= 1000; i++)
		{
			System.out.println(i);
		}
	}
}
