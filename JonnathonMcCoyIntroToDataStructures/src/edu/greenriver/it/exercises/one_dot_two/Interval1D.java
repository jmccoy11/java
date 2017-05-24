package edu.greenriver.it.exercises.one_dot_two;

import java.util.Scanner;

public class Interval1D 
{
	static Scanner console = new Scanner(System.in);
	double min, max;
	
	public static void main(String[] args)
	{
		
		System.out.println("Enter how many intervals you'd like to test against the 1D interval: ");
		String userInput = console.nextLine();
		
		int intervals = (int)validateNumber(userInput, false);
		System.out.println();
		
		System.out.println("Enter the minimum number: ");
		userInput = console.nextLine();
		
		double minimum = validateNumber(userInput, true);
		System.out.println();
		
		System.out.println("Enter the maximum number: ");
		userInput = console.nextLine();
		
		double maximum = validateNumber(userInput, true);
		
		boolean valid = false;
		while(!valid)
		{
			if(maximum < minimum)
			{
				System.out.println("Maximum number must be greater than the minimum of " + minimum);
				userInput = console.nextLine();
				maximum = validateNumber(userInput, true);
			}
			else
			{
				valid = true;
			}
		}
		
		Interval1D userInterval = new Interval1D(minimum, maximum);
		
		for(int i = 0; i <= intervals; i++)
		{	
			Interval1D newInterval = new Interval1D(0,i);
			
			if(userInterval.intersects(newInterval))
			{
				System.out.println(i + " intersects.");
			}
		}
		
	}
	
	public Interval1D(double min, double max)
	{
		this.min = min;
		this.max = max;
	}
	
	private double min()
	{
		return min;
	}
	
	private double max()
	{
		return max;
	}
	
	private double length()
	{
		return max-min;
	}
	
	private boolean contains(double x)
	{
		if(x >= min && x <= max)
		{
			return true;
		}
		
		return false;
	}
	
	private boolean intersects(Interval1D that)
	{
		if(contains(that.max()))
		{
			return true;
		}
		return false;
	}
	
	private static double validateNumber(String input, boolean negativeOK)
	{
		boolean valid = false;
		double number = 0;
		
		while(!valid)
		{
			try
			{
				number = Integer.parseInt(input);
				
				if(negativeOK == false)
				{
					if(number < 0)
					{
						throw new NumberFormatException();
					}
					else
					{
						valid = true;
					}
				}
				else
				{
					valid = true;
				}
			}
			catch(NumberFormatException exc)
			{
				System.out.println("Please enter a number greater than zero.");
				input = console.nextLine();
			}
			catch(Exception exc)
			{
				System.out.println("Please enter a number greater than zero.");
				input = console.nextLine();
			}
		}
		return number;
	}
}
