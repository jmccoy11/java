/*
 * Jonnathon McCoy
 * April 20, 2016
 * Squire.java
 * 
 * The Longest Adventure Assignment
 * 
 * Class to create a child Squire class
 * of Player class and associated final
 * fields.
 */

package players;

/**
 * Class to create a child Squire class
 * of Player class and associated final
 * fields.
 * 
 * @author Jonnathon McCoy
 * @version 1.0
 */
public class Squire extends Player
{
	public static final int MAX_STEPS = 3;
	public static final int MAX_FATIGUE = 15;
	
	/**
	 * Creates a Squire object.
	 * 
	 * @param name String name of the Squire Object
	 */
	public Squire(String name)
	{
		super(MAX_STEPS, MAX_FATIGUE, HeroType.SQUIRE, name);
	}
	
	/**
	 * Prints a statement to console.
	 */
	public void stepsInMud()
	{
		System.out.println("I grew up in the mud, this "
				+ "won't affect me!");
	}
	
	/**
	 * Prints a statement to console.
	 */
	public void stepsOnGrass()
	{
		System.out.println("I love the open grassy "
				+ "fields.");
	}
	
	/**
	 * Prints a statement to console.
	 */
	public void stepsOnRoad()
	{
		System.out.println("I wonder how long this "
				+ "road will go on.");
	}
	
	/**
	 * Prints a statement to console.
	 */
	public void stepsOnTrap()
	{
		
		System.out.println("Ugh a trap!");
	}
}
