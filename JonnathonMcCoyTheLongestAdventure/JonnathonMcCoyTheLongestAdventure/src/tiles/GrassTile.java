/*
 * Jonnathon McCoy
 * April 20, 2016
 * GrassTile.java
 * 
 * The Longest Adventure Assignment
 * 
 * Class to create a child GrassTile object
 * of BasicTile class.
 */

package tiles;

import game.Game;
import players.Player;

/**
 * Class to create a child GrassTile object
 * of BasicTile class.
 * 
 * @author Jonnathon McCoy
 * @version 1.0
 */
public class GrassTile extends BasicTile
{
	/**
	 * Creates a GrassTile object using parent
	 * BasicTile constructor.
	 */
	public GrassTile()
	{
		super(TileType.GRASS);
	}

	/**
	 * Allows methods specific to a GrassTile to be used
	 * in correspondence to a specific HeroType.
	 * 
	 * @param game Passes the current Game object with a Player object 
	 * stored within it so that the corresponding stepsOn(In) method 
	 * for the Player for that a GrassTile can be used
	 */
	public void visitTile(Game game)
	{
		Player player = game.getPlayer();
		
		player.setFatigue(1);
		game.printPlayerFatigue();		
		player.stepsOnGrass();
	}
}
