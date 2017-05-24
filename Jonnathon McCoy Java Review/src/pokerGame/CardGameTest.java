/*
 * Jonnathon McCoy
 * January 8, 2017
 * 
 * Package: pokerGame
 * Class: CardGameTest.java
 * 
 * This class the driver for the Java Review.
 * 
 * The purpose of this program is to create an array of individual
 * cards extending from the parent class PlayingCard to be
 * instantiated within an array so that it may be shuffled.
 */

package pokerGame;

import java.util.Random;

import enumsPackage.CardSuit;
import enumsPackage.CardType;

/**
 * This class is the driver for the Java Review.
 * 
 * The purpose of this program is to create an array of individual
 * cards extending from the parent class PlayingCard to be
 * instantiated within an array so that it may be shuffled.
 * 
 * @author Jonnathon McCoy
 * @version 1.0
 */
public class CardGameTest {
	
	public static final byte DECK_SIZE = 52;

	public static Random rand = new Random();
	
	/**
	 * This is the entry point for the program.
	 * 
	 * @param args Command-line arguments
	 */
	public static void main(String[] args) {
		PlayingCard[] myDeck = new PlayingCard[DECK_SIZE];
		
		createDeck(myDeck);
		printDeck(myDeck);
		
		System.out.println();
		System.out.println("--shuffling one time--");
		System.out.println();
		
		myDeck = shuffle(myDeck);
		printDeck(myDeck);
		
		System.out.println();
		System.out.println("--shuffling 100 times--");
		System.out.println();
		
		myDeck = shuffle(myDeck, 100);
		printDeck(myDeck);
	} //main()
	
	/**
	 * Create a deck of 52 cards into the array. The first 13 (0-12) indexes
	 * will be the red hearts suit, index 13-25 will be red diamonds,
	 * index 26-38 will be black spades, and index 39-51 will be
	 * black clubs.
	 * 
	 * @param deck - Array of PlayingCards.
	 */
	public static void createDeck(PlayingCard[] deck){
		int indexPlaceHolder = 0;
		
		for(CardSuit suit : CardSuit.values()){ //for each suit (hearts, diamond, spades, and clubs)
			
			for(CardType card : CardType.values()){ //for each card type (Ace through King)
				
				if(suit.getSuit().equalsIgnoreCase("hearts") || 
						suit.getSuit().equalsIgnoreCase("diamonds")){
					
					deck[indexPlaceHolder] = new Card("red", suit, card);  //create card of suit and card
					indexPlaceHolder++;
				}
				else{
					deck[indexPlaceHolder] = new Card("black", suit, card); //create card of suit and card
					indexPlaceHolder++;
				}
			} //CardType for:each
		} //CardSuit for:each
	} //createDeck()
	
	/**
	 * Print the contents of the PlayingCard array to the console.
	 * 
	 * @param deck - Array of PlayingCards.
	 */
	public static void printDeck(PlayingCard[] deck){
		for(int i = 0; i < deck.length; i++){
			System.out.println(deck[i].toString());
		}
	} //printDeck()
	
	/**
	 * Similar to splitting a deck to shuffle all the cards once, not all the cards will change
	 * position.  This method will swap up to a maximum of 26 pairs of cards in the array.
	 * 
	 * @param deck - Array of PlayingCards.
	 * @return - returns the new shuffled deck.
	 */
	public static PlayingCard[] shuffle(PlayingCard[] deck){
		/* A minimum of 1 pair of cards are shuffled (or swapped in our array) in any given shuffling 
		 * of a deck to a maximum 26 pairs of cards meaning that a minimum of 2 to a maximum of 52 cards
		 * may be able swap positions.
		 * 
		 * In order to ensure that the random number of cards to swap is always an even number, in order
		 * to achieve a maximum of 26 pairs, a random number between 1 and 13 will be multiplied by two.
		 */
		final int MIN = 1;
		final int MAX = 13;
		int cardsToSwap = (rand.nextInt(MAX-MIN)+ MIN) * 2;
		
		for(int i = 0; i < cardsToSwap; i++){
			int firstIndex = rand.nextInt(deck.length-1); //0-51 = 52 indices 
			int secondIndex = rand.nextInt(deck.length-1);
			
			while(firstIndex == secondIndex){ //ensure firstIndex does not equal secondIndex
				secondIndex = rand.nextInt(deck.length-1); 
			}
			
			PlayingCard swap = deck[firstIndex];
			deck[firstIndex] = deck[secondIndex];
			deck[secondIndex] = swap;
		}
		
		return deck;
	} //shuffle()
	
	/**
	 * Simulates splitting a deck n number of times and shuffling the deck.
	 * 
	 * @param deck - Array of PlayingCards.
	 * @param times - int number of times desired to shuffle and swap.
	 * @return - returns the new shuffled deck.
	 */
	public static PlayingCard[] shuffle(PlayingCard[] deck, int times){
		for(int i = 0; i < times; i++)
		{
			deck = shuffle(deck);
		}
		
		return deck;
	} //shuffle() overloaded
} //CardGameTest.java
