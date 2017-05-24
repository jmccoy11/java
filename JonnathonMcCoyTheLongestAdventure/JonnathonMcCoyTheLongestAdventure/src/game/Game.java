/*
 * Jonnathon McCoy
 * April 20, 2016
 * Game.java
 * 
 * The Longest Adventure Assignment
 * 
 * Player chooses how many tiles for their journey.
 * Player choses a class: Knight, Princess, or Squire.
 * Based upon their choice, the particular hero will attempt
 * to reach the end of the journey before their stamina runs
 * out.
 * 
 * Each tile will potentially affect heroes in different ways
 * to slow them down.
 */

package game;

import players.Player;
import players.HeroType;
import tiles.BasicTile;
import tiles.TileType;
import game.RandomGenerator;
import java.util.Scanner;

/**
 * This class represents a whole Game object with Player and Tile
 * objects.
 * 
 * @author Jonnathon McCoy
 * @version 1.0
 */
public class Game
{
	//fields
	//objects
	private RandomGenerator rand = new RandomGenerator();
	private java.util.Scanner console = new Scanner(System.in);
	private BasicTile[] tiles; //stores tile objects
	private Player player; //stores player object
	private Game game;
	//primitive data
	private boolean gameLoop = true;
	private int position = -1; //position of player within the tiles array
	private int turns = 0;
	
	//constructors
	/**
	 * This constructor is only for use with the visitTile() method.
	 * 
	 * Constructor for Game Class that only uses a player object in order to
	 * pass a Game object with all the Game fields to another method
	 * called visitTile(Game game).
	 * 
	 * @param player Sends a Player object created from GameDriver.java
	 * for instantiation within the Game class.
	 */
	public Game(Player player) //used for passing game into visitTile(game)
	{
		this.player = player;
	}
	
	/**
	 * Constructor for Game that creates the variables necessary for the
	 * program to run.
	 * 
	 * @param numTiles numTiles is passed from the GameDriver.java in order
	 * to allow buildTiles() method to build the requested amount of objects
	 * within BasicTiles[] array.
	 * 
	 * @param player Sends a Player object created from GameDriver.java
	 * for instantiation within the Game class.
	 */
	public Game(int numTiles, Player player)
	{
		tiles = new BasicTile[numTiles]; //set the size of the BasicTile array
		this.player = player;
	}
	
	//public methods
	
	/**
	 * Starts the game loop when called from GameDriver.java
	 */
	public void startGame() //contains the main loop
	{
		buildTiles(); //this creates our array of tile objects
		
		game = new Game(this.player); //for use later with visitTile()
		
		System.out.println(player.getHeroType());
		
		while(gameLoop)
		{
			if(position >= tiles.length) //verify the character position is
										 //is not outside the array.
			{
				gameLoop = false;
				win();
			}
			else
			{
				//verify fatigue is not greater than MAX_FATIGUE
				if(player.getFatigue() < player.getMaxFatigue())
				{
					int steps = rand.nextInt(1, player.getMaxSteps());
					movePlayer(steps);
					movementFlavorText(steps);
					
					String tileArray = printTiles();
					System.out.println(tileArray);
					
					if(position < tiles.length)
					{
						visitTile();
						System.out.println();
						System.out.println("Press Enter to continue on your "
								+ "journey.");
						console.nextLine();
						turns ++;
					}
				}
				else
				{
					gameLoop = false;
					lose();
				}
			}
		}
	}
	
	/**
	 * Getter for retrieving a Player object.
	 * 
	 * @return Player object
	 */
	public Player getPlayer()
	{
		return player;
	}
	
	/**
	 * Prints current Player fatigue and Player MAX_FATIGUE
	 * to the console.
	 */
	//Prints player fatigue using getters.
	public void printPlayerFatigue()
	{
		System.out.println("Player fatigue: " + player.getFatigue()
				+ "/" + player.getMaxFatigue());
	}
	
	//private methods
	
	//moves player position within the tiles[] array
	private void movePlayer(int distance)
	{
		if(position+distance <= 0) //avoid exiting the array into a negative index
		{
			this.position = 0;
		}
		else
		{
			this.position = position + distance;
		}
	}

	private void buildTiles()
	{
		/*
		 * This method should instantiates tile objects with the following:
		 * percentages:
		 * 10% trap, 20% mud, 30% road, and 40% grass tiles
		 */
		
		for(int i = 0; i < tiles.length; i++)
		{
			int number = rand.nextInt(1,10);
			
			if(number > 0 && number <= 4)
			{
				tiles[i] = new tiles.GrassTile();
			}
			else if(number > 4 && number <= 7)
			{
				tiles[i] = new tiles.RoadTile();
			}
			else if (number > 7 && number <= 9)
			{
				tiles[i] = new tiles.MudTile();
			}
			else
			{
				tiles[i] = new tiles.TrapTile();
			}
		}
	}
	
	//prints out the tiles[] array along with the current position of the player
	private String printTiles()
	{
		String result = "[";
		
		for (int i = 0; i < tiles.length; i++)
		{
			if (i != 0)
			{
				result += ", ";
			}
			
			if (position == i)
			{
				result += tiles[i].toString() + " - (player)";
			}
			else
			{
				result += tiles[i].toString();
			}
		}
		result += "]";
		
		if (position >= tiles.length)
		{
			result += " (player)";
		}
		
		return result;
	}
	
	//use the visitTile method within the corresponding tileType class
	//for the object within the tiles[] array.  Eventually printing the 
	//corresponding stepsOn(In) method for the Player for that tileType.
	private void visitTile()
	{		
		tiles[position].visitTile(game);
		
		if(tiles[position].getType() == TileType.TRAP &&
				player.getHeroType() != HeroType.PRINCESS)
		{
			movePlayer(-1);
			System.out.println();
			movementFlavorText(-1);
			String updated = printTiles();
			System.out.println(updated);
		}
	}
	
	private void movementFlavorText(int steps)
	{
		if(position < tiles.length) //if position inside the array 
									//to avoid ArrayOutOfBoundsException
		{
			System.out.println(player.getName() + " moves " + steps + " step(s) into a " 
						+ tiles[position].getType() + " tile.");
		}
		else //if position is outside
		{
			System.out.println(player.getName() + " moves " + steps + " step(s)");
		}
	}
	
	//lose message
	private void lose()
	{
		System.out.println();
		System.out.println("Unfortunately " + player.getName() +", you have become "
				+ "to tired to continue!");
		System.out.println("Your journey took " + turns+ " number of turns.");
	}
	
	//win message
	private void win()
	{
		System.out.println();
		System.out.println("Congratulations " + player.getName() + ", you have "
				+ "completed your journey!");
		System.out.println("Your journey took " + turns+ " number of turns.");
	}
}
