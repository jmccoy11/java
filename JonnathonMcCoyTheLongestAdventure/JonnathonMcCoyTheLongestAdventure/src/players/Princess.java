/*
 * Jonnathon McCoy
 * April 20, 2016
 * Princess.java
 * 
 * The Longest Adventure Assignment
 * 
 * Class to create a child Princess class
 * of Player class  and associated final
 * fields.
 */

package players;

/**
 * Class to create a child Princess class
 * of Player class  and associated final
 * fields.
 * 
 * @author Jonnathon McCoy
 * @version 1.0
 */
public class Princess extends Player 
{
	public static final int MAX_STEPS = 3;
	public static final int MAX_FATIGUE = 12;
	
	/**
	 * Creates a Princess object.
	 * 
	 * @param name String name of the Princess Object
	 */
	public Princess(String name)
	{
		super(MAX_STEPS,MAX_FATIGUE,HeroType.PRINCESS, name);
	}
	
	/**
	 * Prints a statement to console.
	 */
	public void stepsInMud()
	{
		System.out.println("This mud is going to stain "
				+ "my dress!");
	}
	
	/**
	 * Prints a statement to console.
	 */
	public void stepsOnGrass()
	{
		System.out.println("This grass feels great between"
				+ " my toes.");
	}
	
	/**
	 * Prints a statement to console.
	 */
	public void stepsOnRoad()
	{
		System.out.println("This road is dusty.");
	}
	
	/**
	 * Prints a statement to console.
	 */
	public void stepsOnTrap()
	{
		System.out.println("This trap won't affect me!");
	}
}
