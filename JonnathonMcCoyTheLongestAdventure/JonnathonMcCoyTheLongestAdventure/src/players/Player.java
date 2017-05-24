/*
 * Jonnathon McCoy
 * April 20, 2016
 * Player.java
 * 
 * The Longest Adventure Assignment
 * 
 * Class to create and store a Player object
 * and associated fields.
 */

package players;

/**
 * Class to create and store a Player object
 * and associated fields.
 * 
 * @author Jonnathon McCoy
 * @version 1.0
 */
public abstract class Player
{
	//fields
	private final int MAX_STEPS, MAX_FATIGUE;
	private String name;
	private HeroType heroType;
	private int fatigue = 0;
	
	//constructors
	/**
	 * Creates a Player object.
	 * 
	 * @param MAX_STEPS Maximum steps for Player object passed 
	 * from the child class
	 * 
	 * @param MAX_FATIGUE Maximum fatigue for Player object
	 * passed from the child class
	 * 
	 * @param heroType Hero type from within the HeroType Enum
	 * 
	 * @param name String name of the Player object
	 */
	public Player(int MAX_STEPS, int MAX_FATIGUE, 
			HeroType heroType, String name)
	{
		this.MAX_STEPS = MAX_STEPS;
		this.MAX_FATIGUE = MAX_FATIGUE;
		this.name = name;
		this.heroType = heroType;
	}
	
	//methods
	/**
	 * Prints a statement to console defined in child class
	 */
	public abstract void stepsInMud();
	
	/**
	 * Prints a statement to console defined in child class
	 */
	public abstract void stepsOnGrass();
	
	/**
	 * Prints a statement to console defined in child class
	 */
	public abstract void stepsOnRoad();
	
	/**
	 * Prints a statement to console defined in child class
	 */
	public abstract void stepsOnTrap();
	
	/**
	 * Getter for the Player name.
	 * 
	 * @return returns String name of Player object
	 */
	public String getName()
	{
		return name;
	}
	
	/**
	 * Setter for the Player name.
	 * 
	 * @param name New name for the object
	 */
	public void setName(String name)
	{
		this.name = name;
	}
	
	/**
	 * Getter for the HeroType from HeroType Enum.
	 * 
	 * @return returns HeroType from HeroType Enum
	 */
	public HeroType getHeroType()
	{
		return heroType;
	}
	
	/**
	 * Getter for Player fatigue.
	 * 
	 * @return returns int for Player fatigue
	 */
	public int getFatigue()
	{
		return fatigue;
	}
	
	/**
	 * Setter to set Player fatigue.
	 * 
	 * @param increase How much to increase the Player
	 * fatigue
	 */
	public void setFatigue(int increase)
	{
		this.fatigue += increase;
	}
	
	/**
	 * Getter for the Player final int MAX_STEPS.
	 * 
	 * @return returns the MAX_STEPS for Player object
	 */
	public int getMaxSteps()
	{
		return MAX_STEPS;
	}
	
	/**
	 * Getter for the Player final in MAX_FATIGUE.
	 * 
	 * @return returns the MAX_FATIGUE for Player object
	 */
	public int getMaxFatigue()
	{
		return MAX_FATIGUE;
	}
	
	/**
	 * Intended for debugging.
	 * 
	 * @return returns the the name, heroType, MAX_STEPS,
	 * MAX_FATIGUE and current fatigue.
	 */
	public String toString()
	{
		return "Player name: " + name +
				"\nHero: " + heroType +
				"\nMaximum Steps character can take: " + MAX_STEPS +
				"\nMaximum Fatigue: " + MAX_FATIGUE +
				"\nCurrent fatigue: " + fatigue;
	}
}
