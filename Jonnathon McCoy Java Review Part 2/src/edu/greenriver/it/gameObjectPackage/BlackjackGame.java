/* 
 * Jonnathon McCoy
 * January 18, 2017
 * 
 * Package: edu.greenriver.it.gameObjectPackage
 * Class: BlackjackGame.java
 * 
 * BlackjackGame Object for creating a new BlackjackGame to be initialized in the BlackjackDriver
 * class main() method.  This class holds all the methods required to play a round and print the
 * results to the console.
 */

package edu.greenriver.it.gameObjectPackage;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

import edu.greenriver.it.cardsPackage.Card;
import edu.greenriver.it.enumsPackage.CardSuit;
import edu.greenriver.it.enumsPackage.CardType;

/**
 * BlackjackGame Object for creating a new BlackjackGame to be initialized in the BlackjackDriver
 * class main() method.  This class holds all the methods required to play a round and print the
 * results to the console.
 * 
 * @author Jonnathon McCoy
 * @version 1.0
 */
public class BlackjackGame extends CardGame{
	
	private final int DECK_SIZE = 52;
	private final int MAX_RANGE = 21;
	private final int MIN_SHUFFLE = 1;
	private final int MAX_SHUFFLE = 13;
	private final String[] messages = {"Hit? (Y/N)",
			"Would you like to play again? (Y/N)",
			"Please enter a (Y/N)."};
	
	private ArrayList<Card> deck;
	
	public Random rand = new Random();
	public Scanner console = new Scanner(System.in);
	
	/**
	 * Default constructor for BlackjackGame. Instantiate and initialize a new
	 * ArrayList of type Card. Runs the createDeck() method to fill ArrayList
	 * with cards.
	 */
	public BlackjackGame(){
		super("Blackjack", "Welcome to my Blackjack Game!");
		
		//generate a new deck
		deck = new ArrayList<Card>(DECK_SIZE);
		createDeck();
	} //BlackjackGame()

	/**
	 * Create a deck of 52 cards into the array. The first 13 (0-12) indexes
	 * will be the red hearts suit, index 13-25 will be red diamonds,
	 * index 26-38 will be black spades, and index 39-51 will be
	 * black clubs.
	 * 
	 * @param deck - Array of Cards.
	 */
	private void createDeck(){
		for(CardSuit suit : CardSuit.values()){ //for each suit (hearts, diamond, spades, and clubs)
			
			for(CardType card : CardType.values()){ //for each card type (Ace through King)
				
				if(suit.getSuit().equalsIgnoreCase("hearts") ||	suit.getSuit().equalsIgnoreCase("diamonds")){
					
					deck.add(new Card("red", suit, card));  //create card of suit and card
				}
				else{
					deck.add(new Card("black", suit, card)); //create card of suit and card
				}
			} //CardType for:each
		} //CardSuit for:each
	} //createDeck()
	
	/**
	 * Similar to splitting a deck to shuffle all the cards once, not all the cards will change
	 * position.  This method will swap up to a maximum of 26 pairs of cards in the array.
	 * 
	 * @param deck - Array of Cards.
	 * @return - returns the new shuffled deck.
	 */
	public void shuffle(){
		/* A minimum of 1 pair of cards are shuffled (or swapped in our array) in any given shuffling 
		 * of a deck to a maximum 26 pairs of cards meaning that a minimum of 2 to a maximum of 52 cards
		 * may be able swap positions.
		 * 
		 * In order to ensure that the random number of cards to swap is always an even number, in order
		 * to achieve a maximum of 26 pairs, a random number between 1 and 13 will be multiplied by two.
		 */
		int cardsToSwap = (rand.nextInt(MAX_SHUFFLE-MIN_SHUFFLE)+ MIN_SHUFFLE) * 2; //ensures even number
		
		for(int i = 0; i < cardsToSwap; i++){
			int firstIndex = rand.nextInt(DECK_SIZE); //0-51 = 52 indices 
			int secondIndex = rand.nextInt(DECK_SIZE);
			
			while(firstIndex == secondIndex){ //ensure firstIndex does not equal secondIndex
				secondIndex = rand.nextInt(DECK_SIZE); 
			}
			
			Card swap = deck.get(firstIndex);
			deck.set(firstIndex, deck.get(secondIndex));
			deck.set(secondIndex, swap);
		}
	} //shuffle()
	
	/**
	 * Simulates splitting a deck n number of times and shuffling the deck.
	 * 
	 * @param deck - Array of Cards.
	 * @param times - int number of times desired to shuffle and swap.
	 * @return - returns the new shuffled deck.
	 */
	private ArrayList<Card> shuffle(int times){
		for(int i = 0; i < times; i++){
			shuffle();
		}
		
		return deck;
	} //shuffle() overloaded

	/**
	 * Get a card from the end of the deck array (removes the need for
	 * checks to ensure the same random number isn't grabbing the same
	 * card).
	 * 
	 * @return - Card Object from deck
	 */
	public Card deal(){
		Card card = deck.get(deck.size()-1);
		deck.remove(deck.size()-1);
		return card;
	} //deal()

	/**
	 * Play a round of Blackjack. Method contains main game loop.
	 */
	public void playRound() {
		ArrayList<Card> playerHand = new ArrayList<Card>();
		ArrayList<Card> dealerHand = new ArrayList<Card>();
		boolean gameLoop = true, errorFlag = false;
		int playerTotal = 0, dealerTotal = 0, playerWins = 0, dealerWins = 0;
		
		System.out.println(getWelcomeMessage());
		System.out.println();
		
		while(gameLoop){
			boolean bust = false;
			
			shuffle(rand.nextInt(100));
			
			System.out.println();
			
			playerHand = hit(false, playerHand, dealerHand);
			playerTotal = total(playerHand);
			System.out.println("Total: " + playerTotal);
			
			// hit?
			boolean hit = getUserInput(messages[0], messages[2]);
			
			while(hit) //if user selected Y, getUserInput returns true
			{
				playerHand = hit(false, playerHand, dealerHand);
				playerTotal = total(playerHand);
				System.out.println("Total: " + playerTotal);
				
				if(playerTotal > MAX_RANGE){ //if playerTotal has exceeded 21, player busts
					System.out.println("BUST! Dealer wins!");
					
					dealerWins++;
					
					hit = false;
					bust = true;
					break;
				} //if player bust before dealer even deals
				
				// hit?
				hit = getUserInput(messages[0], messages[2]); //ask to hit again and continue loop if Y
			}
			
			if(!bust){ //no need to run this code if the player has already busted.
				System.out.println();
				System.out.println("Dealer's turn.");
				System.out.println();
				
				// dealer must continue hitting until they have either exceeded the player's total
				// or reached 21
				while(dealerTotal < MAX_RANGE && dealerTotal < playerTotal){
					dealerHand = hit(true, playerHand, dealerHand);
					dealerTotal = total(dealerHand);
					System.out.println("Total: " + dealerTotal);
				} //dealer draw
				
				//logic for determining dealer win or bust 
				if(dealerTotal > MAX_RANGE){ // if Dealer is greater than 21
					System.out.println("Dealer BUSTED! You win!");
					
					playerWins++;
				} 
				else if(dealerTotal == playerTotal){
					System.out.println("Dealer push. Dealer wins!");
					
					dealerWins++;
				}
				else if(dealerTotal <= MAX_RANGE && dealerTotal > playerTotal){
					System.out.println("Dealer wins!");
					
					dealerWins++;
				}
				else{ // In case there's a condition I didn't account for.
					System.out.println("Unknown condition occurred:");
					System.out.println();
					System.out.println("Player total: " + playerTotal);
					System.out.println("Player hand:");
					for(Card card : playerHand){
						System.out.println(card);
					}
					System.out.println();
					System.out.println("Dealer Total " + dealerTotal);
					System.out.println("Dealer Hand:");
					for(Card card : dealerHand){
						System.out.println(card);
					}
					System.out.println();
					System.out.println("State of the game:");
					System.out.println(toString());
					
					errorFlag = true;
				} //logic for determining dealer win or bust
			}
			
			winner(playerWins, dealerWins);
			
			if(!errorFlag){
				for(Card card : playerHand){
					deck.add(card);
				}
				for(Card card : dealerHand){
					deck.add(card);
				}
			}
			
			// reset
			playerHand.clear();
			playerTotal = 0;
			dealerHand.clear();
			dealerTotal = 0;
			
			// Play again?
			boolean again = getUserInput(messages[1] , messages[2]);
			if(again == false) gameLoop = false;
		} //game while loop
		
		System.out.println();
		System.out.println("Thank you for playing.");
		
		console.close();
	} //playRound()
	
	private ArrayList<Card> hit(Boolean isDealer, ArrayList<Card> playerHand, ArrayList<Card> dealerHand){
		if(isDealer){
			if(dealerHand.isEmpty())  //on first call of hit(), it will give dealer two cards.
			{
				dealerHand.add(deal());
				printToConsole(true, dealerHand.get(0));
			}
			
			Card dealerCard = deal();
			printToConsole(isDealer, dealerCard);
			dealerHand.add(dealerCard);
		} //dealer is delt
		else{
			if(playerHand.isEmpty())  //on first call of hit(), it will give player two cards.
			{
				playerHand.add(deal());
				printToConsole(false, playerHand.get(0));
			}
			
			Card playerCard = deal();			
			printToConsole(isDealer, playerCard);	
			playerHand.add(playerCard);
		} //player is delt
		
		if(isDealer) return dealerHand;
		else return playerHand;
	} //hit()
	
	/*
	 * Sums the total of all cards within the ArrayList in accordance with their
	 * values from the Card.java class and the CardType.java enum.
	 */
	private int total(ArrayList<Card> hand){
		int total = 0;
		boolean containsAce = false;
		
		for(Card card : hand){ //checks if there are any aces in the ArrayList
			total += card.getCardValue().getValue();
			if(card.getCardValue() == CardType.ACE){
				containsAce = true;
			}
		}
		
		if(total > MAX_RANGE && containsAce)
		{
			int numberOfAces = 0; //counts how many Aces
			
			for(Card card : hand){
				if(card.getCardValue() == CardType.ACE){
					numberOfAces++; //Two Aces can equal a bust
									//5 cards without busting is still a win condition
				}
			}
			total -= (CardType.ACE.getValue()* numberOfAces); //no magic numbers
			total += numberOfAces;
		}
		
		return total;
	} //total()
	
	/*
	 * Asks for user input and allows a message to be passed for both the prompt
	 * and the case of incorrect input entered for a Yes or No question.
	 */
	private boolean getUserInput(String message, String errorMessage)
	{
		String userInput;
		boolean again = false;
		
		try{
			System.out.println(message);
			userInput = console.nextLine();
			
			if(userInput.equalsIgnoreCase("y") || userInput.equalsIgnoreCase("n")){
				if(userInput.equalsIgnoreCase("y")) again = true;
			}
		}
		catch(InputMismatchException exc){
			System.out.println(errorMessage);
			userInput = console.nextLine();
		}
		catch(Exception exc){
			System.out.println("Something went wrong. " + exc);
		}
		
		return again;
	} //getUserInput()
	
	/*
	 * Prints what card was delt to the console. Differentiates between a player
	 * or the dealer for proper grammatical syntax.
	 */
	private void printToConsole(boolean isDealer, Card card){
		if(isDealer){
			if(card.getCardValue() == CardType.ACE || card.getCardValue() == CardType.EIGHT){
				System.out.println("Dealer delt an " + card.toString());
			}
			else System.out.println("Dealer delt a " + card.toString());
		}
		else{
			if(card.getCardValue() == CardType.ACE || card.getCardValue() == CardType.EIGHT){
				System.out.println("You are delt an " + card.toString());
			}
			else System.out.println("You are delt a " + card.toString());
		}
	} //printToConsole()
	
	/**
	 * Score tracker for how many times the player and the dealer have won.
	 * 
	 * @param playerWins - Compares if a win is 1 or more and displays the proper
	 * grammatical syntax.
	 * 
	 * @param dealerWins- Compares if a win is 1 or more and displays the proper
	 * grammatical syntax.
	 */
	public void winner(int playerWins, int dealerWins){
		System.out.println();
		if(playerWins == 1){
			System.out.println("You have won " + playerWins + " time.");
		}
		else{
			System.out.println("You have won " + playerWins + " times.");
		}
		
		if(dealerWins == 1){
			System.out.println("The dealer has won " + dealerWins + " time.");
		}
		else{
			System.out.println("The dealer has won " + dealerWins + " times.");
		}
		
		System.out.println();
	} //winner()
	
	/**
	 * Getter for the deck.
	 * 
	 * @return - Current ArrayList deck.
	 */
	public ArrayList<Card> getDeck(){
		return deck;
	} //getDeck()
	
	/**
	 * Setter for the deck.
	 * 
	 * @param deck - ArrayList to overwrite the current deck.
	 */
	public void setDeck(ArrayList<Card> deck){
		this.deck = deck;
	} //setDeck()

	/**
	 * String representation of the BlackjackGame Object.
	 * 
	 * @return - String representation of the BlackjackGame Object.
	 */
	public String toString() {
		return "BlackjackGame [DECK_SIZE=" + DECK_SIZE + ", MAX_RANGE=" + MAX_RANGE + ", MIN_SHUFFLE=" + MIN_SHUFFLE
				+ ", MAX_SHUFFLE=" + MAX_SHUFFLE + ", deck=" + deck + ", rand=" + rand + ", console=" + console + "]";
	} //setDeck()
} //BlackjackGame.java
