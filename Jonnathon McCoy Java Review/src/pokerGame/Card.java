/*
 * Jonnathon McCoy
 * January 8, 2017
 * 
 * Package: pokerGame
 * Class: PlayingCardChild.java
 * 
 * This class contains the constructor for the child class 
 * of the PlayingCard class.
 */

package pokerGame;

import enumsPackage.CardSuit;
import enumsPackage.CardType;

/**
 * This class contains the constructor for the child class 
 * of the PlayingCard class.
 * 
 * @author Jonnathon McCoy
 * @version 1.0
 *
 */
public class Card extends PlayingCard{

	//fields
	CardSuit suit;
	CardType rank;
	
	//constructor
	/**
	 * Parameterized constructor for PlayingCardChild.
	 * 
	 * @param color - for parent constructor, red or black
	 * @param suit - hearts, diamond, spades, or clubs
	 * @param rank - rank 1-13, 1 is Ace, 11 is Jack, 12 is Queen,
	 * 		and 13 is King.
	 */
	public Card(String color, CardSuit suit, CardType rank) {
		super(color);
		this.suit = suit;
		this.rank = rank;
	}

	//methods
	public String getCardSuit(){
		return suit.getSuit();
	}
	
	public CardType getCardValue(){
		return rank;
	}
	
	public String toString(){
		return rank.getName() + " of " + suit.getSuit();
	}
}
