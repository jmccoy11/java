package edu.greenriver.it.starting_threads;

import java.util.InputMismatchException;

public class SumThread extends Thread
{
	private int max;
	private int sum;
	
	public SumThread(int max)
	{
		if(max <= 0)
		{
			throw new InputMismatchException("Non-positive max given");
		}
		
		this.max = max;
	}
	
	@Override
	public void run()
	{
		for(int i = 1; i <= max; i++)
		{
			sum += i;
		}
	}
	
	public int getSum()
	{
		return sum;
	}
}
