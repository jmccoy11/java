/*
 * Jonnathon McCoy
 * February 2, 2017
 * 
 * Package: edu.greenriver.it.main
 * Class: MainDriver.java
 * 
 * Driver class to test BitWise operators and solve the Lockers
 * problem using a BitArray.
 */

package edu.greenriver.it.main;

import java.util.Scanner;
import edu.greenriver.it.arrays.*;

/**
 * Driver class to test BitWise operators and solve the Lockers
 * problem using a BitArray.
 * 
 * @author Jonnathon McCoy
 * @version 1.0
 */
public class MainDriver 
{
	private static final byte LOCKERS = 30;
	private static final byte STUDENTS = 30;
	private static final byte BASETEN = 10;
	private static final byte FORMAT = 4;
		
	private static Scanner console = new Scanner(System.in);

	/**
	 * This is the entry point for the program.
	 * 
	 * @param args - Command-line arguments
	 */
	public static void main(String[] args) 
	{
		BitArray bitArray = new BitArray();
		printCodingExercise(bitArray);
		
		System.out.println();
		System.out.println("Press Enter to continue to the Lockers exercise.");
		console.nextLine();
		System.out.println();
		
		BitArray lockerArray = new BitArray();
		solve(lockerArray);
	}//main()
	
	/* Solve the lockers problem:
	 * 
	 * A new high school has just opened!  There are 30 lockers in the school and 
	 * they have been numbered from 1 through 30. When the school opens the first 
	 * student to enter the school walks into the hallway and opens all the locker 
	 * doors! Afterwards, the second student closes each door whose number is a 
	 * multiple of 2. Similarly, the third student changes every door that is a 
	 * multiple of 3 (closing open doors, opening closed doors). The fourth student 
	 * changes each door that is a multiple of 4 and so on. After 30 students have 
	 * entered the hallway, which locker doors are open?
	 */
	private static void solve(BitArray lockerArray)
	{
		for(int student = 1; student <= STUDENTS; student++)
		{
			for(int locker = 1; locker <= LOCKERS; locker++)
			{
				if(locker%student == 0)
				{
					if(lockerArray.getBit(locker-1))
					{
						lockerArray.setBit(locker-1, false);;
					}
					else lockerArray.setBit(locker-1, true);
					
					printToConsole(lockerArray, locker, student);
				}
			}
		}
	}//solve()
	
	/*
	 * Retrieve the boolean value of the bit at index "index".
	 * 
	 * Create the mask to be a power of 2 at index "index" and AND it to number.
	 * If the result equals the mask, the bit is set.
	 * 
	 * @param number - integer to compare the mask to
	 * @param index - binary index of number
	 * @return
	 */
	private static boolean isSet(int number, int index)
	{
		int mask = (int)Math.pow(2, index);
		
		return ((number & mask) == mask);
	} //isSet()
	
	/*
	 * Using a binary mask, create a word by selecting only the characters
	 * that correspond at the same index that the bit mask is set to 1.
	 * 
	 * The steps of this method are as follows:
	 * 
	 * Convert the binaryMask from a base10 to a base2 number
	 * Flip the word to read backward so the indexes of the base2 will
	 *   line with the indexes of the String.
	 * Check if the bit at index is set and build a new word from the 
	 *   indexes that the bitMask have set to a 1.
	 * Return new word.
	 * 
	 * @param word - String - the word you wish to apply the mask
	 * @param binaryMask - int - the binary mask you would like to apply 
	 * @return - String - the new word created by combining the word with the
	 *   binary mask.
	 */
//	private static String wordMask(String word, int binaryMask){
//		
//		/* 
//		 * Convert the mask by taking the base 10 and multiplying it
//		 * by 2 to the corresponding power (starting at 0: 2^0=1, 2^1=2, 2^2=4, etc.)
//		 * and add it to the maskNum.
//		 * 
//		 * if mask%10 is 0, the maskNum += 0 (0*2^power = 0);
//		 * if the mask%10 is 1, the maskNum is += (1)*2^power (1*1=1, 1*2=2, 1*4=4, etc.)
//		 * 
//		 * divide by 10 to reduce the number by one digit.
//		 * 
//		 * repeat until the mask == 0;
//		 * 
//		 * now maskNum will equal a non-base10 number that can be used
//		 * with comparison to another number to see if bits are set.
//		 */
//		int numMask = 0;
//		int power = 0;
//		
//		
//		while(binaryMask != 0){
//			int temp = binaryMask%BASETEN;
//			numMask += temp*(int)Math.pow(2, power);
//			binaryMask = binaryMask/BASETEN;
//			power++;
//		}
//		
//		/* Since a binary number is read from right to left and a word is read
//		 * left to right, the indexes for both the binary mask and the number
//		 * will be inverted.
//		 * 
//		 * This data will need to be flipped around to line up the indexes between
//		 * the string and the mask.
//		 * 
//		 * ie: 1000 1011  index 7 of is a 1
//		 *     |
//		 *     V
//		 *     Vert icle  matches index 0 which is a V
//		 *     
//		 * 		by reversing verticle (which seems easier than reversing a mask 
//		 * 			at the moment)
//		 * 
//		 *     1000 1011   index 7 is a 1
//		 *     |_______
//		 *             |         
//		 *     elci treV   will match with index 7 of elcitreV which is a V
//		 */
//		String flipWord = "";
//		for(int i = word.length()-1; i >= 0; i--){
//			flipWord += word.charAt(i);
//		}
//		
//		/*
//		 * For each bit that isSet() take the character at the current iteration
//		 * of the 'for' loop from flipWord[i].
//		 */
//		String maskWord = "";
//		for(int i = word.length()-1; i >= 0; i--){
//			if(isSet(numMask, i)){
//				maskWord += flipWord.charAt(i);
//			}
//		}
//		
//		return maskWord;
//	}//wordMask()
	
	private static String wordMask(String word, int binaryMask)
	{
		String maskedWord = "";
		String flipWord = "";
		
		//flip the word to line up with the mask
		for(int i = word.length()-1; i >= 0; i--)
		{
			flipWord += word.charAt(i);
		}
		
		//apply the mask
		for(int i = 0; i < flipWord.length(); i++)
		{
			if(isSet(binaryMask, i))
			{
				maskedWord += flipWord.charAt(i);
			}
		}
		
		//flip the word back around
		word = "";
		for(int i = maskedWord.length()-1; i >=0; i--)
		{
			word += maskedWord.charAt(i);
		}
		
		return word;
	}
	

	/*
	 * Takes the base2 of a number, adds zeroes equal to the intended
	 * array length (LOCKERS), formats the number to put a space every
	 * 4th number, and flips the number to read back as binary is
	 * more traditionally read.
	 */
	private static void printBinary(int data)
	{
		String rawBinary = "";
		
		while(data != 0)
		{
			if(data%2 == 0)//data mod 2 will tell you whether there is a zero at that location
			{
				rawBinary += "0";
			}
			else 
			{
				rawBinary += "1";
			}
			
			data = data/2; //divide by 2 to get the next number until data is finally zero
		}
		
		String addZeroes = "";
		if(rawBinary.length() != LOCKERS)
		{
			for(int i = 0; i < (LOCKERS - rawBinary.length()); i++)
			{
				addZeroes += "0";
			}
			
			rawBinary += addZeroes;
		}
		
		String binary = "";
		for(int i = 0; i < rawBinary.length(); i++)
		{
			if(i%FORMAT == 0) //format the number to include spaces every fourth index
			{
				binary += " " + rawBinary.charAt(i);
			}
			else
			{
				binary += rawBinary.charAt(i);
			}
		}
		
		//flip the binary
		String binaryFlip = "";
		for(int i = binary.length()-1; i >= 0; i--)
		{
			binaryFlip += binary.charAt(i);
		}
		
		System.out.println(binaryFlip);
	}//printBinary()
	
	/*
	 * Print the locker problem to the console. Uses a switch:case to determine if
	 * the student is the 1st, 2nd, or 3rd. Every student after is an nth student.
	 */
	private static void printToConsole(BitArray lockerArray, int locker, int student)
	{
		switch (student)
		{
			case 1: System.out.println("Bit set on Locker " + locker + " by the " + student + "st student.");
					break;
			case 2: System.out.println("Bit set on Locker " + locker + " by the " + student + "nd student.");
					break;
			case 3: System.out.println("Bit set on Locker " + locker + " by the " + student + "rd student.");
					break;
			default: System.out.println("Bit set on Locker " + locker + " by the " + student + "th student.");
					 break;
		}
		
		System.out.print("Binary representation: ");
		printBinary(lockerArray.getData());
		System.out.println("Current value of lockerArray.data: " + lockerArray.getData());
		System.out.println();
	}//printToConsole()
	
	/*
	 * Print the coding Exercise problems 1 and 2 to the console.
	 */
	private static void printCodingExercise(BitArray bitArray)
	{
		System.out.println("Testing isSet()");
		System.out.println("For the number 3, is bit 0 set?");
		System.out.println(isSet(3,0));
		
		System.out.println();
		System.out.println("For the number 64, is bit 6 set?");
		System.out.println(isSet(64,6));
		
		System.out.println();
		System.out.println("For the number 64, is bit 2 set?");
		System.out.println(isSet(64,2));
		
		System.out.println();
		System.out.println("Testing wordMask()");
		System.out.println("Vertical with the word Mask of 1100 0011 is: ");
		System.out.println(wordMask("vertical", 195)); //(1100 0011)

		System.out.println();
		System.out.println("Vertical with the word Mask of 1000 1011 is: ");
		System.out.println(wordMask("vertical", 139)); // (1000 1011)
	}//printCodingExercise()
}//MainDriver.java
