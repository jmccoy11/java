/*
 * Jonnathon McCoy
 * January 8, 2017
 * 
 * Package: pokerGame
 * Class: PlayingCard.java
 * 
 * This class contains the constructor and various methods for the
 * PlayingCard class used in the Java Review. 
 */
package pokerGame;

/**
 * This class contains the constructor and various methods for the 
 * PlayingCard objects used in the Java Review.
 * 
 * @author Jonnathon McCoy
 * @version 1.0
 */
public class PlayingCard {
	
	//fields
	String color;
	final double WIDTH = 2.5; //inches
	final double HEIGHT = 3.5; //inches
	
	//constructors
	/**
	 * Parameterized constructor for the PlayingCard class.
	 * 
	 * @param color - The color of the PlayingCard. Hearts and Diamonds
	 * are typically red, Spades and Clubs are typically black.
	 */
	public PlayingCard(String color){
		this.color = color;
	} //PlayingCard()
	
	//methods
	/**
	 * Getter for the color.
	 * 
	 * @return String color.
	 */
	public String getColor() {
		return color;
	} //getColor()

	/**
	 * Setter for color.
	 * 
	 * @param color - red or black
	 */
	public void setColor(String color) {
		this.color = color;
	} //setColor()
	
	/**
	 * String representation of the PlayingCard object.
	 * 
	 * @return - Human-readable representation of the PlayingCard
	 *     object.
	 */
	public String toString() {
		return "Playing Card: " + this.WIDTH + " inches " + this.HEIGHT 
				+ " inches; Color: " + getColor();
	} //toString()
} //PlayingCard.java
