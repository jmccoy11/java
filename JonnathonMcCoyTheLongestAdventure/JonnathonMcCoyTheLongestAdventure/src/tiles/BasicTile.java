/*
 * Jonnathon McCoy
 * April 20, 2016
 * BasicTile.java
 * 
 * The Longest Adventure Assignment
 * 
 * Class to create and store a BasicTile object
 * and associated fields.
 */

package tiles;

import game.Game;

/**
 * Class to create and store a BasicTile object
 * and associated fields.
 * 
 * @author Jonnathon McCoy
 * @version 1.0
 */
public abstract class BasicTile
{
	private TileType type;
	
	/**
	 * Creates a Basic Tile object.
	 * 
	 * @param type Type within TileType Enum
	 */
	public BasicTile(TileType type)
	{
		this.type = type;
	}
	
	/**
	 * Getter for TileType.
	 * 
	 * @return returns TileType from TileType Enum
	 */
	public TileType getType()
	{
		return type;
	}
	
	/**
	 * Intended for debugging.
	 * 
	 * @return returns the first letter of the value within 
	 * TileType
	 */
	public String toString()
	{
		return type.toString().substring(0, 1);
	}
	
	/**
	 * Allows methods specific to a particular TileType to be used
	 * in correspondence to a specific HeroType.
	 * 
	 * @param game Passes the current Game object with a Player object 
	 * stored within it so that the corresponding stepsOn(In) method 
	 * for the Player for that a specific tileType will be used
	 */
	public abstract void visitTile(Game game);
}
