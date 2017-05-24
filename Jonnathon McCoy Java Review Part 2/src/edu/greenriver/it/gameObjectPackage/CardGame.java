/* 
 * Jonnathon McCoy
 * January 18, 2017
 * 
 * Package: edu.greenriver.it.gameObjectPackage
 * Class: CardGAme.java
 * 
 * Abstract Parent Class to the BlackjackGame class. Contains several
 * abstract methods.
 */

package edu.greenriver.it.gameObjectPackage;

import edu.greenriver.it.cardsPackage.PlayingCard;

/**
 * Abstract Parent Class to the BlackjackGame class. Contains several
 * abstract methods.
 * 
 * @author Jonnathon McCoy
 * @version 1.0
 *
 */
public abstract class CardGame {
	
	private String gameName;
	private String welcomeMessage;
	
	/**
	 * Parameterized constructor for CardGame.
	 * 
	 * @param name - String name of the game being played.
	 * @param message - String custom welcome message.
	 */
	public CardGame(String name, String message){
		this.setGameName(name);
		this.setWelcomeMessage(message);
	}

	/**
	 * shuffle() is intended to shuffle the ArrayList of cards
	 */
	public abstract void shuffle();
	
	/**
	 * Pulls a card from the ArrayList of cards.
	 * 
	 * @return - PlayingCard card
	 */
	public abstract PlayingCard deal();
	
	/**
	 * Play a round of the game. Contains the majority of the game logic
	 */
	public abstract void playRound();

	/**
	 * Getter for game name.
	 * 
	 * @return - String gameName.
	 */
	public String getGameName() {
		return gameName;
	}

	/**
	 * Setter for game name.
	 * 
	 * @param gameName - String name of game.
	 */
	public void setGameName(String gameName) {
		this.gameName = gameName;
	}

	/**
	 * Getter for the welcome message.
	 * 
	 * @return - String welcome message.
	 */
	public String getWelcomeMessage() {
		return welcomeMessage;
	}

	/**
	 * Setter for the welcome message.
	 * 
	 * @param welcomeMessage - String new welcome message.
	 */
	public void setWelcomeMessage(String welcomeMessage) {
		this.welcomeMessage = welcomeMessage;
	}
} //CardGame.java
