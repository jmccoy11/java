/*
 * Jonnathon McCoy
 * April 20, 2016
 * TrapTile.java
 * 
 * The Longest Adventure Assignment
 * 
 * Class to create a child TrapTile object
 * of BasicTile class.
 */

package tiles;

import game.Game;
import players.HeroType;
import players.Player;

/**
 * Class to create a child TrapTile object
 * of BasicTile class.
 * 
 * @author Jonnathon McCoy
 * @version 1.0
 */
public class TrapTile extends BasicTile
{
	/**
	 * Creates a TrapTile object using parent
	 * BasicTile constructor.
	 */
	public TrapTile()
	{
		super(TileType.TRAP);
	}

	/**
	 * Allows methods specific to a TrapTile to be used
	 * in correspondence to a specific HeroType.
	 * 
	 * @param game Passes the current Game object with a Player object 
	 * stored within it so that the corresponding stepsOn(In) method 
	 * for the Player for that a TrapTile can be used
	 */
	public void visitTile(Game game)
	{
		Player player = game.getPlayer();

		if(player.getHeroType() == HeroType.KNIGHT)
		{
			player.setFatigue(2);
		}
		else if(player.getHeroType() == HeroType.SQUIRE)
		{
			player.setFatigue(2);
		}
		
		game.printPlayerFatigue();
		player.stepsOnTrap();
	}
}
