package edu.greenriver.it.starting_threads;

import java.util.InputMismatchException;

public class CountingThread implements Runnable
{
	private int low;
	private int high;
	
	public CountingThread(int low, int high)
	{
		if( low > high)
		{
			throw new InputMismatchException("low must be less than or equal to high");
		}
		
		
		this.low = low;
		this.high = high;
	}
	
	@Override
	public void run() 
	{
		//this code will run on a secondary code
		
		for(int i = low; i <= high; i++)
		{
			System.out.println(i);
		}
	}

}
