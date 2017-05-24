/*
 * Jonnathon McCoy
 * January 8, 2017
 * 
 * Package: edu.greenriver.it.cardsPackage
 * Class: Card.java
 * 
 * This class contains the constructor for the child class 
 * of the PlayingCard class.
 */

package edu.greenriver.it.cardsPackage;

import edu.greenriver.it.enumsPackage.CardSuit;
import edu.greenriver.it.enumsPackage.CardType;

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
	/**
	 * Getter for suit. Uses the getSuit() method in the CardSuit enum.
	 * 
	 * @return - String suit of card.
	 */
	public String getCardSuit(){
		return suit.getSuit();
	}
	
	/**
	 * Getter for the rank of the card using the CardType enum.
	 * 
	 * @return - CardType from CardType enum.
	 */
	public CardType getCardValue(){
		return rank;
	}
	
	/**
	 * String representation of the Card object.
	 * 
	 * return - String representation of the Card object.
	 */
	public String toString(){
		return rank.getName() + " of " + suit.getSuit();
	}
}
