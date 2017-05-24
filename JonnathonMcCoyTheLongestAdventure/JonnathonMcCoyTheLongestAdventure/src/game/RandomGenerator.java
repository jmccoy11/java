/*
 * Jonnathon McCoy
 * April 20, 2016
 * RandomGenerator.java
 * 
 * The Longest Adventure Assignment
 * 
 * Class created to add the random.nextInt() method
 * to allow the capability to create a number within a
 * specific range.
 */

package game;

import java.util.Random;

/**
 * Class to add the random.nextInt() method to include
 * the capability to create a number within a specific range.
 * 
 * @author Jonnathon McCoy
 * @version 1.0
 */
public class RandomGenerator extends Random //I don't know what it wants
											//with serialVersionUID or if
											//it's necessary.
{
	private Random rand = new Random();
	
	/**
	 * Constructor for to use Random default constructor
	 */
	public RandomGenerator()
	{
		super();
	}
	
	/**
	 * Addition for Random.nextInt() method to include the
	 * capability to create a number within a specific range.
	 * 
	 * @param min Low integer
	 * @param max High integer
	 * @return Returns a random number between min and max
	 */
	public int nextInt(int min, int max)
	{
		return min + rand.nextInt(max-min+1);
	}
}
