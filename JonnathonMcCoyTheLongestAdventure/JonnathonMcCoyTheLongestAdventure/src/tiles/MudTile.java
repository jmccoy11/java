/*
 * Jonnathon McCoy
 * April 20, 2016
 * MudTile.java
 * 
 * The Longest Adventure Assignment
 * 
 * Class to create a child MudTile object
 * of BasicTile class.
 */

package tiles;

import game.Game;
import players.HeroType;
import players.Player;

/**
 * Class to create a child MudTile object
 * of BasicTile class.
 * 
 * @author Jonnathon McCoy
 * @version 1.0
 */
public class MudTile extends BasicTile
{
	/**
	 * Creates a MudTile object using parent
	 * BasicTile constructor.
	 */
	public MudTile()
	{
		super(TileType.MUD);
	}

	/**
	 * Allows methods specific to a MudTile to be used
	 * in correspondence to a specific HeroType.
	 * 
	 * @param game Passes the current Game object with a Player object 
	 * stored within it so that the corresponding stepsOn(In) method 
	 * for the Player for that a MudTile can be used
	 */
	public void visitTile(Game game)
	{
		Player player = game.getPlayer();
		
		if(player.getHeroType() == HeroType.KNIGHT)
		{
			player.setFatigue(2);
		}
		else if(player.getHeroType() == HeroType.PRINCESS)
		{
			player.setFatigue(2);
		}
		
		game.printPlayerFatigue();	
		player.stepsInMud();
	}
}