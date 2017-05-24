/* Jonnathon McCoy
 * January 8, 2017
 * 
 * Package: edu.greenriver.it.gameTestPackage
 * Class: BlackjackDriver.java
 * 
 * Driver class for the Blackjack Game.
 */
 
package edu.greenriver.it.gameTestPackage;

import edu.greenriver.it.gameObjectPackage.BlackjackGame;

/**
 * Driver class for the Blackjack Game.
 * 
 * @author Jonnathon McCoy
 * @version 1.0
 */
public class BlackjackDriver {

	/**
	 * This is the entry point for the program.
	 * 
	 * @param args Command-line arguments
	 */
	public static void main(String[] args) {
		
		BlackjackGame game = new BlackjackGame();
		
		game.playRound();
	}

}
