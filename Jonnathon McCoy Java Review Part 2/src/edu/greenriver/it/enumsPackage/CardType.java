/*
 * Jonnathon McCoy
 * January 11, 2017
 * 
 * Package: edu.greenriver.it.enumsPackage
 * Class: CardType.java
 * 
 * This enum contains the name, rank, and value of a card with regards to playing
 *   Blackjack.
 */

package edu.greenriver.it.enumsPackage;

/**
 * This enum contains the name, rank, and value of a card with regards to playing
 *   Blackjack.
 * 
 * @author Jonnathon McCoy
 * @version 1.0
 *
 */
public enum CardType {
	
	ACE("Ace", 1, 11),
	TWO("Two", 2, 2),
	THREE("Three", 3, 3),
	FOUR("Four", 4, 4),
	FIVE("Five", 5, 5),
	SIX("Six", 6, 6),
	SEVEN("Seven", 7, 7),
	EIGHT("Eight", 8, 8),
	NINE("Nine", 9, 9),
	TEN("Ten", 10, 10),
	JACK("Jack", 11, 10),
	QUEEN("Queen", 12, 10),
	KING("King", 13, 10);
	
	String name;
	int rank;
	int value;
	
	/*
	 * Parameterized constructor for the enum to hold the String 'name', the
	 *   integer 'rank' of the card, and the integer 'value' of the card for the
	 *   purposes of performing math functions for Blackjack.
	 * 
	 * @param name - String name of the card "Ace", "1", "2", ..., "Queen", "King"
	 * 	 for use with the toString() method.
	 * @param rank - int rank of the card. 1, 2, 3, ..., 11, 12, 13
	 * @param value - int value of the card. 2-10 are the value of the rank. Jack,
	 *   Queen, and King are all worth 10 and Ace is worth 11 unless the player's or
	 *   dealer's cards values added together have exceeded 21. Otherwise, Ace is worth
	 *   1. Default for Ace is 1 for the sake of organizing rank.
	 */
	private CardType(String name, int rank, int value){
		this.name = name;
		this.rank = rank;
		this.value = value;
	}
	
	/**
	 * Getter for name.
	 * 
	 * @return - String name.
	 */
	public String getName(){
		return name;
	}
	
	/**
	 * Getter for int rank.
	 * 
	 * @return - int rank.
	 */
	public int getRank(){
		return rank;
	}
	
	/**
	 * Getter for int value.
	 * 
	 * @return - int value.
	 */
	public int getValue(){
		return value;
	}
}
