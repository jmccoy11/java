/*
 * Jonnathon McCoy
 * April 20, 2016
 * Knight.java
 * 
 * The Longest Adventure Assignment
 * 
 * Class to create a child Knight class
 * of Player class and associated final
 * fields.
 */

package players;

/**
 * Class to create a child Knight class
 * of Player class and associated final
 * fields.
 * 
 * @author Jonnathon McCoy
 * @version 1.0
 */
public class Knight extends Player
{
	private static final int MAX_STEPS = 2;
	private static final int MAX_FATIGUE = 25;
	
	/**
	 * Creates a Knight object.
	 * 
	 * @param name String name of the Knight Object
	 */
	public Knight(String name)
	{
		super(MAX_STEPS, MAX_FATIGUE, HeroType.KNIGHT, name);
	}
	
	/**
	 * Prints a statement to console.
	 */
	public void stepsInMud()
	{
		System.out.println("Mud, yuck, mud everywhere!");
	}
	
	/**
	 * Prints a statement to console.
	 */
	public void stepsOnGrass()
	{
		System.out.println("This grass is slowing me "
				+ "down.");
	}
	
	/**
	 * Prints a statement to console.
	 */
	public void stepsOnRoad()
	{
		System.out.println("This road is more to my "
				+ "liking.");
	}
	
	/**
	 * Prints a statement to console.
	 */
	public void stepsOnTrap()
	{
		System.out.println("Lucky my armor is on, ouch!");
	}
}
