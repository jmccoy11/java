/*
 * Jonnathon McCoy
 * January 11, 2017
 * 
 * Package: enumsPackage
 * Class: CardSuit.java
 * 
 * This enum contains the suits in a deck to ensure invalid suits cannot
 *   be used to create a card within a deck.
 */

package enumsPackage;

/**
 * This enum contains the suits in a deck to ensure invalid suits cannot
 *   be used to create a card within a deck.
 * 
 * @author Jonnathon McCoy
 * @version 1.0
 *
 */
public enum CardSuit {

	HEARTS("Hearts"),
	DIAMONDS("Diamonds"),
	SPADES("Spades"),
	CLUBS("Clubs");
	
	String suit;
	
	/*
	 * Paramaterized constructor for the enum to hold a String 'suit' for
	 *   the toString() method to print the proper suit name. ie: Hearts,
	 *   Diamonds, Spades, or Clubs.
	 * 
	 * @param suit - String suit is the name of the suit to be passed to the
	 *   constructor. ie: Hearts, Diamonds, Spades, or Clubs.
	 */
	private CardSuit(String suit){
		this.suit = suit;
	}
	
	/**
	 * Getter for returning the suit name.
	 * 
	 * @return String suit. Hearts, Diamonds, Spades, or Clubs.
	 */
	public String getSuit(){
		return suit;
	}
}