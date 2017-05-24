/*
 * Jonnathon McCoy
 * April 20, 2016
 * MudTile.java
 * 
 * The Longest Adventure Assignment
 * 
 * Class to create a child RoadTile object
 * of BasicTile class.
 */

package tiles;

import game.Game;
import players.HeroType;
import players.Player;

/**
 * Class to create a child RoadTile object
 * of BasicTile class.
 * 
 * @author Jonnathon McCoy
 * @version 1.0
 */
public class RoadTile extends BasicTile
{
	/**
	 * Creates a RoadTile object using parent
	 * BasicTile constructor.
	 */
	public RoadTile()
	{
		super(TileType.ROAD);
	}

	/**
	 * Allows methods specific to a RoadTile to be used
	 * in correspondence to a specific HeroType.
	 * 
	 * @param game Passes the current Game object with a Player object 
	 * stored within it so that the corresponding stepsOn(In) method 
	 * for the Player for that a RoadTile can be used
	 */
	public void visitTile(Game game)
	{
		Player player = game.getPlayer();
		
		if(player.getHeroType() == HeroType.SQUIRE)
		{
			player.setFatigue(1);
		}
		
		game.printPlayerFatigue();	
		player.stepsOnRoad();
	}
}
